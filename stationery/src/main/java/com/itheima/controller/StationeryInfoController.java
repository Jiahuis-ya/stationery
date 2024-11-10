package com.itheima.controller;
import com.itheima.domain.StationeryInfo;
import com.itheima.domain.Stationery;
import com.itheima.domain.User;
import com.itheima.service.StationeryInfoService;
import com.itheima.service.StationeryService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.itheima.exception.CustomException;;
/*
文具信息Controller
 */
@Controller
@RequestMapping("/stationeryInfo")
public class StationeryInfoController {
    //注入StationeryService对象
    @Autowired
    private StationeryInfoService stationeryInfoService;
    private StationeryService stationeryService;
    

/**
 * 分页查询符合条件且未下架文具信息
 * @param stationeryInfo 查询的条件封装到stationery中
 * @param pageNum  数据列表的当前页码
 * @param pageSize 数据列表1页展示多少条数据
 */
@RequestMapping("/search")
public ModelAndView search(StationeryInfo stationeryInfo, Integer pageNum, Integer pageSize, HttpServletRequest request) {
    if (pageNum == null) {
        pageNum = 1;
    }
    if (pageSize == null) {
        pageSize = 10;
    }
    //查询到的文具信息
    PageResult pageResult = stationeryInfoService.search(stationeryInfo, pageNum, pageSize);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("stationerys");
    //将查询到的数据存放在 ModelAndView的对象中
    modelAndView.addObject("pageResult", pageResult);
    //将查询的参数返回到页面，用于回显到查询的输入框中
    modelAndView.addObject("search", stationeryInfo);
    //将当前页码返回到页面，用于分页插件的分页显示
    modelAndView.addObject("pageNum", pageNum);
    //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
    modelAndView.addObject("gourl", request.getRequestURI());
    return modelAndView;
}


/**
 * 根据文具id查询文具信息
 * @param id 查询的文具id
 */
@ResponseBody
@RequestMapping("/findById")
public Result<StationeryInfo> findById(Integer id) {
    try {

        if (id < 0){
            throw new CustomException("查询不合法");
        }
        StationeryInfo stationeryInfo = stationeryInfoService.findById(id);
        if(stationeryInfo==null){
            throw new CustomException("查询失败！");
        }

        return new Result(true,"操作成功",stationeryInfo);
    }catch (CustomException c){
        return new Result(false,c.getMsg());
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false,"查询失败！");
    }
}

}

