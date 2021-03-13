package org.geektimes.projects.user.sql;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;

/**
 * 本地事务
 */
@Documented
@Target(ElementType.METHOD) // 仅支持方法级别
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalTransactional {

    int PROPAGATION_REQUIRED = 0;

    int PROPAGATION_REQUIRES_NEW = 3;

    int PROPAGATION_NESTED = 6;

    /**
     * 事务传播
     * @return
     */
    int propagation() default PROPAGATION_REQUIRED;

    /**
     * 事务隔离级别
     * @return
     * @see Connection#TRANSACTION_READ_COMMITTED
     */
    int isolation() default TRANSACTION_READ_COMMITTED;
}
