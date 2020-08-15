package com.junya.spring.auth.service.impl;

import com.junya.spring.repository.bean.JunyaUserBean;
import com.junya.spring.repository.dao.JunyaUserMapper;
import com.junya.spring.auth.service.IJunyaUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Junya
 * @since 2020-08-14
 */
@Service
public class JunyaUserServiceImpl extends ServiceImpl<JunyaUserMapper, JunyaUserBean> implements IJunyaUserService {

}
