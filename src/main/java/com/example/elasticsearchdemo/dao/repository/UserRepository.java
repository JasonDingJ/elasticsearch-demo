package com.example.elasticsearchdemo.dao.repository;

import com.example.elasticsearchdemo.dao.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author dingjian
 * @version V1.0.1
 * @create 2019-05-17-12:02
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
