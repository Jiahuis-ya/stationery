package com.itheima.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itheima.exception.CustomException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*
 *异常Controller
 */
@Controller
@RequestMapping("/Exception")
public class ExceptionController {
    @Autowired

    @RequestMapping("/demo2")
    @ResponseBody
    public Integer demo2(){
        return 1;
    }

    //抛出空指针异常
    @RequestMapping("showNullPointer")
    public void showNullPointer() {
        ArrayList<Object> list = new ArrayList<>();
        System.out.println(list.get(2));
    }

    //抛出IO异常
    @RequestMapping("showIOException")
    public void showIOException() throws IOException {
        FileInputStream in = new FileInputStream("JavaWeb.xml");
    }

    //抛出算术异常
    @RequestMapping("showArithmetic")
    public void showArithmetic() {
        int c = 1 / 0;
    }

    @RequestMapping("addData")
    public void addData() throws CustomException {
        throw new CustomException("新增数据异常！");
    }

    @RequestMapping("/demo1")
    @ResponseBody
    //测试空指针异常  http://localhost:8080/stationery/Exception/demo1
    public Object demo1(){

        int i = 1 / 0;

        return new Date();

    }
  //    @ExceptionHandler({RuntimeException.class})
  //
  //    public ModelAndView fix(Exception ex){
  //
  //        System.out.println("do This");
  //
  //        return new ModelAndView("error",new ModelMap("ex",ex.getMessage()));
  //
  //    }


}

