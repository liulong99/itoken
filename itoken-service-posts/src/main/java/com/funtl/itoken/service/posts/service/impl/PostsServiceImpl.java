package com.funtl.itoken.service.posts.service.impl;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.mapper.TbPostsPostMapper;
import com.funtl.itoken.common.service.BaseService;
import com.funtl.itoken.common.service.impl.BaseServiceImpl;
import com.funtl.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/5 16:49
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements PostsService {
}
