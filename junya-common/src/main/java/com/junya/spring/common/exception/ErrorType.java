package com.junya.spring.common.exception;

/**
 * Author Junya
 * Date 2020-08-14 17:48
 * Description:
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMesg();
}