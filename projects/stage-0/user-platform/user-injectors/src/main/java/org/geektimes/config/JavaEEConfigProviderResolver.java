package org.geektimes.config;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

public class JavaEEConfigProviderResolver extends ConfigProviderResolver {

    @Override
    public Config getConfig() {
        return getConfig(null);
    }

    @Override
    public Config getConfig(ClassLoader loader) {
        ClassLoader classLoader = loader;
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        ServiceLoader<Config> serviceLoader = ServiceLoader.load(Config.class, classLoader);
        Iterator<Config> iterator = serviceLoader.iterator();
        // 获取 Config SPI 第一个实现
        return iterator.hasNext() ? iterator.next() : null;
    }

    @Override
    public ConfigBuilder getBuilder() {
        return null;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {

    }

    @Override
    public void releaseConfig(Config config) {

    }
}
