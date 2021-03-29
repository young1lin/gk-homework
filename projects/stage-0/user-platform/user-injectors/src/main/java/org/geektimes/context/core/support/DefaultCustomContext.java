package org.geektimes.context.core.support;

import org.geektimes.context.core.ApplicationEventPublisher;
import org.geektimes.context.core.SingletonRegistry;

/**
 * TODO youngli1n: 完成事件发布功能，以便动态更新数据
 *
 * 这里把 DefaultListableBeanFactory 和 GenericApplicationContext 的内容放到了一个类里面了，👴 就是拽
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/10 下午9:19
 * @version 1.0
 */
public class DefaultCustomContext extends AbstractCustomContext implements ApplicationEventPublisher, SingletonRegistry {

	private volatile boolean isRunning;

	private final Object startupShutdownMonitor = new Object();


	public DefaultCustomContext() {
		super();
	}

	@Override
	public void start() {
		synchronized (startupShutdownMonitor) {
			isRunning = true;
			// TODO 之后实现系统启动事件广播功能
			System.out.println("系统启动了。。。。。。。。。。。");
		}
	}

	@Override
	public void registerShutdownHook() {
		// TODO 之后实现注册 ShutdownHook
	}

	@Override
	public void stop() {
		synchronized (startupShutdownMonitor) {
			// TODO 之后实现系统停止事件广播功能
			System.out.println("系统关闭了。。。。。。。。。。。");
			isRunning = false;
		}
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void publishEvent(Object event) {
		// TODO 事件发布功能之后实现
	}

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		componentsMap.put(beanName, singletonObject);
	}

	@Override
	public Object getSingleton(String beanName) {
		return componentsMap.get(beanName);
	}

	@Override
	public boolean containsSingleton(String beanName) {
		return componentsMap.get(beanName) != null;
	}

	@Override
	public String[] getSingletonNames() {
		return (String[]) componentsMap.keySet().toArray();
	}

	@Override
	public int getSingletonCount() {
		return componentsMap.keySet().size();
	}

}
