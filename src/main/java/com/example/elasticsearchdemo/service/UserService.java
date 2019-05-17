package com.example.elasticsearchdemo.service;

import com.example.elasticsearchdemo.dao.entity.User;
import com.example.elasticsearchdemo.pojo.UserQueryParams;
import org.springframework.data.domain.Page;

/**
 * @author dingjian
 * @version V1.0.1
 * @create 2019-05-17-13:32
 */
public interface UserService {

    /**
     * 根据参数进行分页查询
     *
     * @param queryParams
     * @return
     */
    Page<User> queryByParams(UserQueryParams queryParams);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(String id);

}
