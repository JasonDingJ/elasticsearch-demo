package com.example.elasticsearchdemo.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dingjian
 * @version V1.0.1
 * @create 2019-05-17-11:53
 */
@Data
@Document(indexName = "test", type = "user", shards = 1)
public class User implements Serializable {

    /**
     * 主键@id 一定要有
     */
    @Id
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 个人信息
     */
    private String info;

    /**
     * 身份证
     */
    private String idNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    private Date createTime;

    private Date updateTime;
}
