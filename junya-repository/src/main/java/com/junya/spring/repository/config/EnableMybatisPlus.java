package com.junya.spring.repository.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author Junya
 * Date 2020-08-14 11:09
 * Description: mybatisPlus
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisPlusConfig.class)
public @interface EnableMybatisPlus {
}
