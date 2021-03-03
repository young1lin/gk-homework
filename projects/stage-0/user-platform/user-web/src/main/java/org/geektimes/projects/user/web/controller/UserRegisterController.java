package org.geektimes.projects.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/2/28 下午11:07
 * @version 1.0
 */
@Path("/register")
public class UserRegisterController implements PageController {

	private final UserService userService;

	public UserRegisterController() {
		this.userService = new UserServiceImpl();
	}

	@GET
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 偷个懒，这里就判断下名字是否存在了
		if (StringUtils.isEmpty(request.getParameter("name"))) {
			return "register.jsp";
		}
		System.out.println(UserRegisterController.class.getName() + ":" + userService.getAll());
		User user = new User(request.getParameter("name"), request.getParameter("password"),
				request.getParameter("email"), request.getParameter("phoneNumber"));
		userService.register(user);
		return "login-form.jsp";
	}
}
