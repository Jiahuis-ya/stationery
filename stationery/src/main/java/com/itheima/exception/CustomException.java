package com.itheima.exception;

/**
 * 自定义编译时异常
 * 
 * @author apple
 *
 */
public class CustomException extends Exception {
    private String msg;
    private String code;
    public CustomException(String msg) {
        super();
        this.msg = msg;
    }

    public CustomException(String code, String msg) {  
        super(msg);  
        this.code = code;  
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {  
        return code;  
    } 

}