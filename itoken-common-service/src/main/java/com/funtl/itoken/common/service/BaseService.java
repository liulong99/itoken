package com.funtl.itoken.common.service;

import com.funtl.itoken.common.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/5 14:18
 * @Version 1.0
 **/
public interface BaseService<T extends BaseDomain> {

    int insert(T t,String createBy);

    int delete(T t);

    int update(T t,String updateBy);

    int count(T t);

    T selectOne(T t);

    PageInfo<T> page(int pageNum,int pageSize,T t);


}
