package com.junya.spring.auth.oauth2;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author Junya
 * Date 2020-08-14 17:09
 * Description:手机验证码登陆, 用户相关获取
 */

@Slf4j
@Service("mobileUserDetailsService")
public class MobileUserDetailsService extends CustomUserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        log.info("load user by mobile:{}", uniqueId);
        return new User(uniqueId
                ,"1233"
                ,true
                ,true
                ,true
                ,true
                , super.obtainGrantedAuthorities(uniqueId));
    }
}
