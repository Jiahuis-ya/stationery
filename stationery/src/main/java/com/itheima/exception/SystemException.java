package com.itheima.exception;

/**
 * 系统异常
 */

public class SystemException extends RuntimeException {
     private String msg;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    
     public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SystemException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public SystemException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }
}
