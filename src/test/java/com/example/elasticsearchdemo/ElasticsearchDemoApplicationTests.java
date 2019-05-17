package com.example.elasticsearchdemo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.elasticsearchdemo.dao.entity.User;
import com.example.elasticsearchdemo.pojo.UserQueryParams;
import com.example.elasticsearchdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ElasticsearchDemoApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
	}

	@Test
	public void queryUser(){
		UserQueryParams userQueryParams =new  UserQueryParams();
		Date yesterday = DateUtil.yesterday();
		Date tomorrow = DateUtil.tomorrow();
//		userQueryParams.setStartDate(yesterday);
		userQueryParams.setEndDate(DateUtil.date());
		Page<User> users = userService.queryByParams(userQueryParams);
		List<User> content = users.getContent();

		log.info("条数为：{}查询信息结果为：{}", content.size(),JSONObject.toJSONString(content));

	}
	@Test
	public void saveUser(){
		User user = new User();
		user.setName("张三");
		user.setFullName("最帅张三丰");
		user.setSex("男");
		user.setInfo("人帅，实力强，功夫好，为人善良,这是个更新张三丰");
		user.setPhone("18888888888");
		user.setIdNo("310115200101012297");
		user.setId("5cde68ab085438ba02fb1011");
		User user1 = userService.saveUser(user);
		log.info("保存返回信息为：{}", JSONObject.toJSONString(user1));
	}
	@Test
	public void deleteUser(){
		userService.deleteUser("5cde68ab085438ba02fb1011");
	}
}
