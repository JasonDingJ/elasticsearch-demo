package com.example.elasticsearchdemo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dingjian
 * @version V1.0.1
 * @create 2019-05-17-13:48
 */
@Data
public class UserQueryParams implements Serializable {
    private static final long serialVersionUID = 4907403535414577130L;


    /**
     * 参数1
     */
    private String param1;

    /**
     * 参数2
     */
    private String param2;

    /**
     * 参数3
     */
    private String param3;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    public UserQueryParams() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
