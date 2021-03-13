package org.geektimes.projects.user.validator.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidAnnotationValidator.class)
/**
 * @author young1lin
 */
public @interface UserValid {

    String message()default "{message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int idRange() default 0;

}
