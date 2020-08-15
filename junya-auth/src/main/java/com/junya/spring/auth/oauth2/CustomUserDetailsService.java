package com.junya.spring.auth.oauth2;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junya.spring.auth.service.IJunyaUserService;
import com.junya.spring.repository.bean.JunyaUserBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author Junya
 * Date 2020-08-13 15:17
 * Description:
 */
@Slf4j
@Component(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    //登入重试时间
    @Value("${security.loginAfterTime}")
    private Integer loginAfterTime;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IJunyaUserService junyaUserService;

    /**
     * 实现用户信息查询方法 让DaoAuthenticationProvider 获取到数据库获中用户数据
     * @param uniqueId 用户登录账号
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        //登录失败的key
        String flagKey = "loginFailFlag:"+uniqueId;
        String value = redisTemplate.opsForValue().get(flagKey);
        if(StringUtils.isNotBlank(value)){
            //超过限制次数
            throw new UsernameNotFoundException("登录错误次数超过限制，请"+loginAfterTime+"分钟后再试");
        }
        //查询数据库 当前用户是否存在
        JunyaUserBean junyaUserBean = junyaUserService.getOne(Wrappers.<JunyaUserBean>lambdaQuery()
                .eq(JunyaUserBean::getLoginName, uniqueId)
                .eq(JunyaUserBean::getUserZt, 1)
        );
        log.info("load user by username:{}", JSON.toJSONString(junyaUserBean));
        if (junyaUserBean == null){
            throw new UsernameNotFoundException("当前用户不存在");
        }
        return new User(uniqueId
                ,junyaUserBean.getLoginPassword()
                ,true
                ,true
                ,true
                ,true
                , this.obtainGrantedAuthorities(uniqueId));
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param uniqueId
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(String uniqueId) {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        log.info("uniqueId:{},roles:{}", uniqueId, JSON.toJSONString(roles));
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
