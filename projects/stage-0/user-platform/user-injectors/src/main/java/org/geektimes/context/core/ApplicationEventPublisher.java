package org.geektimes.context.core;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @date 2021/3/22 上午12:31
 * @version 1.0
 */
public interface ApplicationEventPublisher {

	/**
	 * 发布事件
	 *
	 * @param event 事件
	 */
	default void publishEvent(ApplicationEvent event) {
		publishEvent((Object) event);
	}

	/**
	 * 发布 Object 对象的事件
	 *
	 * @param event Object 类型事件
	 */
	void publishEvent(Object event);

}
