package me.young1lin.mybatis.spring.boot.starter;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/26 下午7:32
 * @version 1.0
 */
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {

	public static final String MYBATIS_PREFIX = "mybatis";

	private static final ResourcePatternResolver RESOURCE_RESOLVER = new PathMatchingResourcePatternResolver();


	private String[] mapperLocations;

	private String environment;

	private String configLocation;

	private String configurationPropertiesPath;

	private boolean failFast;

	private String typeAliasesPackage;

	private String typeHandlersPackage;

	private boolean isCheckConfigLocation;


	public String[] getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public String getConfigurationPropertiesPath() {
		return configurationPropertiesPath;
	}

	public void setConfigurationPropertiesPath(String configurationPropertiesPath) {
		this.configurationPropertiesPath = configurationPropertiesPath;
	}

	public boolean isFailFast() {
		return failFast;
	}

	public void setFailFast(boolean failFast) {
		this.failFast = failFast;
	}

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public String getTypeHandlersPackage() {
		return typeHandlersPackage;
	}

	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}

	public boolean isCheckConfigLocation() {
		return isCheckConfigLocation;
	}

	public void setCheckConfigLocation(boolean checkConfigLocation) {
		isCheckConfigLocation = checkConfigLocation;
	}

	public Resource[] resolveMapperLocations() {
		return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0]))
				.flatMap(location -> Stream.of(getResources(location))).toArray(Resource[]::new);
	}

	private Resource[] getResources(String location) {
		try {
			return RESOURCE_RESOLVER.getResources(location);
		} catch (IOException e) {
			return new Resource[0];
		}
	}

	@Override
	public String toString() {
		return "MybatisProperties{" +
				"mapperLocations='" + mapperLocations + '\'' +
				", environment='" + environment + '\'' +
				", configLocation='" + configLocation + '\'' +
				", configurationPropertiesPath='" + configurationPropertiesPath + '\'' +
				", failFast=" + failFast +
				", typeAliasesPackage='" + typeAliasesPackage + '\'' +
				", typeHandlersPackage='" + typeHandlersPackage + '\'' +
				", isCheckConfigLocation=" + isCheckConfigLocation +
				'}';
	}

}
