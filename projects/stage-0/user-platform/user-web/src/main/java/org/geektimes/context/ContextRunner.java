package org.geektimes.context;

/**
 * 模仿 Spring Boot，搞个 ApplicationRunner/CommandRunner 对应的 ContextRunner
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午10:56
 * @version 1.0
 */
@FunctionalInterface
public interface ContextRunner {

	/**
	 * after components ready, run this method
	 *
	 * @param context Application context {@link CustomContext}
	 */
	void run(Context context);

}
