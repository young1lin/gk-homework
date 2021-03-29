package org.geektimes.context;

import org.geektimes.context.core.Context;

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
	 * @param context Application context {@link org.geektimes.context.core.support.DefaultCustomContext}
	 */
	void run(Context context);

}
