package com.itheima.controller;
import com.itheima.domain.Stationery;
import com.itheima.domain.User;
import com.itheima.service.StationeryService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
// import javax.validation.Valid;
import com.itheima.exception.CustomException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*
文具领用Controller
 */
@Controller
@RequestMapping("/stationeryRequisition")
public class StationeryController {
    //注入StationeryService对象
    @Autowired
    private StationeryService stationeryService;

/**
 * 根据文件id查询文具信息
 * @param id 查询的文具id
 */
@ResponseBody
@RequestMapping("/findById")
public Result<Stationery> findById(Integer id) {
    try {
        if (id < 0){
            throw new CustomException("查询不合法");
        }
        Stationery stationery=stationeryService.findById(Integer.valueOf(id));
        if(stationery==null){
            throw new CustomException("查询失败！");
        }
        return new Result(true,"查询成功",stationery);
    }catch (CustomException c){
        return new Result(false,c.getMsg());
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false,"查询失败！");
    }
}
/**
 * 领用文具
 * @param stationery 领用的文具
 * @return
 */
@ResponseBody
@RequestMapping("/borrowStationery")
public Result borrowStationery(Stationery stationery, HttpSession session) {
    //获取当前登录的用户姓名
    String pname = ((User) session.getAttribute("USER_SESSION")).getName();
    Integer pUserId = ((User) session.getAttribute("USER_SESSION")).getId();
    stationery.setBorrower(pname);;
    stationery.setBorrowerId(pUserId);
    try {
        //根据文具的id和用户进行文具领领用
        Integer count = stationeryService.borrowStationery(stationery);
        if (count != 1) {
            throw new CustomException("领用失败");
        }
        return new Result(true, "领用成功");
    }catch (CustomException c){
        return new Result(false, c.getMsg());
    } catch (Exception e) {
        e.printStackTrace();
        return new Result(false, "领用失败!");
    }
}

/**
 * 分页查询符合条件且未下架文具信息
 * @param stationery 查询的条件封装到stationery中
 * @param pageNum  数据列表的当前页码
 * @param pageSize 数据列表1页展示多少条数据
 */
@RequestMapping("/search")
public ModelAndView search(Stationery stationery, Integer pageNum, Integer pageSize, HttpServletRequest request) {
    if (pageNum == null) {
        pageNum = 1;
    }
    if (pageSize == null) {
        pageSize = 10;
    }
    //查询到的文具信息
    PageResult pageResult = stationeryService.search(stationery, pageNum, pageSize);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("stationery");
    //将查询到的数据存放在 ModelAndView的对象中
    modelAndView.addObject("pageResult", pageResult);
    //将查询的参数返回到页面，用于回显到查询的输入框中
    modelAndView.addObject("search", stationery);
    //将当前页码返回到页面，用于分页插件的分页显示
    modelAndView.addObject("pageNum", pageNum);
    //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
    modelAndView.addObject("gourl", request.getRequestURI());
    return modelAndView;
}

/**
 * 新增文具
 * @param stationery 页面表单提交的文具信息
 * 将新增的结果和向页面传递信息封装到Result对象中返回
 */
@ResponseBody
@RequestMapping("/addStationery")
// public Result addStationery(@Valid @RequestBody Stationery stationery) {
public Result addStationery(Stationery stationery) {
    try {
        Integer count=stationeryService.addStationery(stationery);
        if(count!=1){
            throw new CustomException("操作失败！");
        }
        
      return new Result(true, "操作成功!");
    
    }catch (CustomException c){
        return new Result(false, c.getMsg());
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "操作失败!");
    }
}

/**
 * 编辑文具信息
 * @param stationery 编辑的文具信息
 */
@ResponseBody
@RequestMapping("/editStationery")
public Result editStationery(Stationery stationery) {
    try {
        Integer count= stationeryService.editStationery(stationery);
        if(count!=1){
            throw new CustomException("编辑失败！");
        }
        return new Result(true, "编辑成功!");
    }catch (CustomException c){
        return new Result(false, c.getMsg());
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "编辑失败!");
    }
}

/**
 *分页查询当前被领用且未归还的文具信息
 * @param pageNum  数据列表的当前页码
 * @param pageSize 数据列表1页展示多少条数据
 */
@RequestMapping("/searchBorrowed")
public ModelAndView searchBorrowed(Stationery stationery,Integer pageNum, Integer pageSize, HttpServletRequest request) {
    if (pageNum == null) {
        pageNum = 1;
    }
    if (pageSize == null) {
        pageSize = 10;
    }
    //获取当前登录的用户
    User user = (User) request.getSession().getAttribute("USER_SESSION");
    PageResult pageResult = stationeryService.searchBorrowed(stationery,user, pageNum, pageSize);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("stationery_borrowed");
    //将查询到的数据存放在 ModelAndView的对象中
    modelAndView.addObject("pageResult", pageResult);
    //将查询的参数返回到页面，用于回显到查询的输入框中
    modelAndView.addObject("search", stationery);
    //将当前页码返回到页面，用于分页插件的分页显示
    modelAndView.addObject("pageNum", pageNum);
    //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
    modelAndView.addObject("gourl", request.getRequestURI());
    return modelAndView;
}
/**
 * 归还文具
 * @param id 归还的文具的id
 */
@ResponseBody
@RequestMapping("/returnStationery")
public Result returnStationery(Integer id, HttpSession session) {
    //获取当前登录的用户信息
    User user = (User) session.getAttribute("USER_SESSION");
    try {
        boolean flag = stationeryService.returnStationery(id, user);
        if (!flag) {
            throw new CustomException("归还失败");
        }
        return new Result(true, "归还确认中，请先到10290工位归还!");
    }catch (CustomException c){
        return new Result(false, c.getMsg());
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "归还失败!");
    }
}

/**
 * 确认文具归还
 * @param id 确认归还的文具的id
 */
@ResponseBody
@RequestMapping("/returnConfirm")
public Result returnConfirm(Integer id) {
    try {
        Integer count=stationeryService.returnConfirm(id);
        if(count!=1){
            throw new CustomException("确认失败！");
        }
        return new Result(true, "确认成功!");
    }catch (CustomException c){
        return new Result(false, c.getMsg());
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false, "确认失败!");
    }
}

}

