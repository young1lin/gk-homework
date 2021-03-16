package org.geektimes.context;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/10 下午9:19
 * @version 1.0
 */
public class CustomContext implements Context {

	public static final String CONTEXT_NAME = CustomContext.class.getName();

	private static ServletContext servletContext;

	private ClassLoader classLoader;

	private final Map<String, Object> componentsMap = new LinkedHashMap<>();

	/** runner 类们 */
	private List<ContextRunner> contextRunners;

	public static CustomContext getInstance() {
		return (CustomContext) servletContext.getAttribute(CONTEXT_NAME);
	}

	public void init(ServletContext servletContext) {
		CustomContext.servletContext = servletContext;
		servletContext.setAttribute(CONTEXT_NAME, this);
		this.classLoader = servletContext.getClassLoader();
		instantiateComponents();
		initializeComponents();
		initializeBeanPostProcessors();
		run(this);
	}

	private void instantiateComponents() {
		String beanJsonPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("beans.json")).getPath();
		File jsonFile = new File(beanJsonPath);
		ObjectMapper mapper = new ObjectMapper();
		List<BeanDefinition> beanDefinitionList = null;
		try {
			beanDefinitionList = mapper.readValue(jsonFile, new TypeReference<List<BeanDefinition>>() {});
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		assert beanDefinitionList != null;
		beanDefinitionList.forEach(e -> componentsMap.put(e.getName(), lookupComponent(e.getClassName())));
		// 打印一下
		beanDefinitionList.forEach(System.out::println);
	}

	private Object lookupComponent(String className) {
		// 反射生成对象实例
		Object result;
		result = componentsMap.get(className);
		if (result != null) {
			return result;
		}
		try {
			Class<?> clazz = Class.forName(className, true, classLoader);
			result = clazz.newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 注入 @{@link @Resource}
	 * 调用 {@link @PostConstruct}
	 */
	private void initializeComponents() {
		componentsMap.values().forEach(component -> {
			Class<?> componentClass = component.getClass();
			// 注入阶段 - {@link Resource}
			injectComponents(component, componentClass);
			// 初始阶段 - {@link PostConstruct}
			processPostConstruct(component, componentClass);
		});
	}

	private void injectComponents(Object component, Class<?> componentClass) {
		Stream.of(componentClass.getDeclaredFields())
				.filter(field -> {
					int mods = field.getModifiers();
					return !Modifier.isStatic(mods) &&
							field.isAnnotationPresent(Resource.class);
				}).forEach(field -> {
			Resource resource = field.getAnnotation(Resource.class);
			String resourceName = resource.name();
			Object injectedObject = lookupComponent(resourceName);
			field.setAccessible(true);
			try {
				// 注入目标对象
				field.set(component, injectedObject);
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	private void processPostConstruct(Object component, Class<?> componentClass) {
		List<Method> methods = Stream.of(componentClass.getMethods())
				.filter(method ->
						!Modifier.isStatic(method.getModifiers()) &&
								method.getParameterCount() == 0 &&
								method.isAnnotationPresent(PostConstruct.class)
				).collect(Collectors.toList());
		methods.forEach(method -> {
			// 执行目标方法
			try {
				method.invoke(component);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void initializeBeanPostProcessors() {
		contextRunners = new ArrayList<>();
		componentsMap.keySet().forEach(e -> {
			Object object = componentsMap.get(e);
			if (object instanceof ContextRunner) {
				contextRunners.add((ContextRunner) object);
			}
		});
	}

	private void run(CustomContext customContext) {
		contextRunners.forEach(e -> e.run(customContext));
	}

	/**
	 * 根据组件名称，获得对应的组件
	 * @param componentName 组件名称
	 * @return 组件实例
	 */
	@Override
	public Object getComponent(String componentName) {
		return componentsMap.get(componentName);
	}

}
