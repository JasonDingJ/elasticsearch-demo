package com.example.elasticsearchdemo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.elasticsearchdemo.dao.entity.User;
import com.example.elasticsearchdemo.dao.repository.UserRepository;
import com.example.elasticsearchdemo.pojo.UserQueryParams;
import com.example.elasticsearchdemo.service.UserService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

/**
 * @author dingjian
 * @version V1.0.1
 * @create 2019-05-17-13:33
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> queryByParams(UserQueryParams queryParams) {
        //构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //对参数进行判断
        if (!StringUtils.isEmpty(queryParams.getParam1())) {
            //多匹配查询
            nativeSearchQueryBuilder.withQuery(
                    QueryBuilders.multiMatchQuery(queryParams.getParam1(), "fullName", "info"));
        }
        //匹配查询
        if (!StringUtils.isEmpty(queryParams.getParam2())) {
            nativeSearchQueryBuilder.withQuery(
                    QueryBuilders.matchQuery("name", queryParams.getParam2()));
        }
        /**
         * .must 一定为
         * .mustNot 一定不为
         * .should 可能为，可能不为
         */
        if (!StringUtils.isEmpty(queryParams.getParam3())) {
            BoolQueryBuilder must = QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("sex", queryParams.getParam3()));

            nativeSearchQueryBuilder.withQuery(must);
        }
        /**
         * gte 大于等于
         * gt 大于
         * lte 小于等于
         * it 小于
         */
        if (null != queryParams.getStartDate()) {
            RangeQueryBuilder createTime = QueryBuilders.rangeQuery("createTime").gte(queryParams.getStartDate().getTime());
            nativeSearchQueryBuilder.withQuery(createTime);
        }
        if (null != queryParams.getEndDate()) {
            RangeQueryBuilder createTime = QueryBuilders.rangeQuery("createTime").lte(queryParams.getEndDate().getTime());
            nativeSearchQueryBuilder.withQuery(createTime);
        }
        //排序
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
        //分页
        nativeSearchQueryBuilder.withPageable(
                PageRequest.of((queryParams.getPageNum() - 1) * queryParams.getPageSize(),
                        queryParams.getPageSize()));
        //查询
        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        Page<User> search = userRepository.search(searchQuery);
        return search;
    }

    @Override
    public User saveUser(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            //利用 hutool 工具类 生成id
            user.setId(IdUtil.objectId());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
        } else {
            user.setUpdateTime(new Date());
        }

        return userRepository.save(user);

    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
