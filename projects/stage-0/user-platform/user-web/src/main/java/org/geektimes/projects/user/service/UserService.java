package org.geektimes.projects.user.service;

import java.util.Collection;
import java.util.List;

import org.geektimes.projects.user.domain.User;

/**
 * 用户服务
 * @author m
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return 成功返回<code>true</code>
     */
    boolean register(User user);

    /**
     * 注销用户
     *
     * @param user 用户对象
     * @return 成功返回<code>true</code>
     */
    boolean deRegister(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return
     */
    boolean update(User user);

    User queryUserById(Long id);

    User queryUserByNameAndPassword(String name, String password);

    Collection<User> getAll();
}
