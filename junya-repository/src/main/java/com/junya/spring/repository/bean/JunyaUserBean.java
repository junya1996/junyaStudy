package com.junya.spring.repository.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Junya
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("junya_user")
public class JunyaUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.NONE)
    private Long userId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录密码(base64加密)
     */
    private String loginPassword;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 用户状态：1 可用 0 不可用
     */
    private Integer userZt;
    /**
     * 创建日期
     */
    private LocalDateTime creDate;

    /**
     * 创建用户
     */
    private Long creUser;

    /**
     * 修改日期
     */
    private LocalDateTime updDate;

    /**
     * 修改用户
     */
    private Long updUser;


}
