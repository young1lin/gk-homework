package org.geektimes.projects.user.validator.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

/**
 * 用户校验
 *
 * @author young1lin
 * @author mercyblitz
 * @since 2021/3/10
 * @version 1.0
 */
public class UserValidAnnotationValidator implements ConstraintValidator<UserValid, User> {

	private int idRange;

	private static final String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

	private static final Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

	private UserValid userValid;

	@Override
	public void initialize(UserValid annotation) {
		this.idRange = annotation.idRange();
		this.userValid = annotation;
	}

	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {
		ConstraintValidatorContextImpl context1 = (ConstraintValidatorContextImpl) context;
		String phoneNumber = value.getPhoneNumber();
		Long id = value.getId();
		String password = value.getPassword();
		List<String> messages = new ArrayList<>(3);
		if (isIllegalPhoneNumber(phoneNumber)) {
			messages.add("电话号码是非法的");
		}
		if (isIllegalId(id)) {
			messages.add("id 不能小于等于 0");
		}
		if (isIllegalPassword(password)) {
			messages.add("密码不能小于 6 位或大于 32 位");
		}
		if (CollectionUtils.isNotEmpty(messages)) {
			context1.addMessageParameter("message", Arrays.toString(messages.toArray()));
			return false;
		}
		System.out.println("很棒棒，都通过了");
		return true;
	}

	private boolean isIllegalPassword(String password) {
		return password == null || password.length() < 6 || password.length() > 32;
	}

	private boolean isIllegalId(Long id) {
		return id == null || id <= 0;
	}

	private boolean isIllegalPhoneNumber(String phoneNumber) {
		if (StringUtils.isEmpty(phoneNumber)) {
			return true;
		}
		Matcher m = p.matcher(phoneNumber);
		return !m.matches();
	}

}
