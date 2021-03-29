package org.geektimes.context.core;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/21 下午6:47
 * @version 1.0
 */
public interface LifeCycle {

	/**
	 * context 启动
	 */
	void start();

	/**
	 * Register a shutdown hook with the JVM runtime, closing this context
	 * on JVM shutdown unless it has already been closed at that time.
	 * <p>This method can be called multiple times. Only one shutdown hook
	 * (at max) will be registered for each context instance.
	 */
	void registerShutdownHook();

	/**
	 * context 关闭时执行该方法
	 */
	void stop();

	/**
	 * 检查 context 是否正在运行
	 *
	 * @return isRunning states
	 */
	boolean isRunning();

}
