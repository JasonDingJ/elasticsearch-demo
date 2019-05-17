package com.example.elasticsearchdemo.web;

import com.example.elasticsearchdemo.dao.entity.User;
import com.example.elasticsearchdemo.pojo.UserQueryParams;
import com.example.elasticsearchdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingjian
 * @version V1.0.1
 * @create 2019-05-17-13:46
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据参数查询用户
     *
     * @return
     */
    @RequestMapping(value = "/queryByParams", method = RequestMethod.POST)
    public Page<User> queryByParams(@RequestBody UserQueryParams queryParams) {
        return userService.queryByParams(queryParams);
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestBody String id) {
        userService.deleteUser(id);

        return "删除成功";
    }


}
