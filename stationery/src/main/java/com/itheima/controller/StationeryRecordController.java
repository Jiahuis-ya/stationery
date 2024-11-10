package com.itheima.controller;
import com.github.pagehelper.Page;
import com.itheima.domain.StationeryRecord;
import com.itheima.domain.User;
import com.itheima.service.StationeryRecordService;
import com.itheima.mapper.StationeryRecordMapper;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.itheima.exception.CustomException;

@Controller
@RequestMapping("/StationerySearchRecord")
public class StationeryRecordController {
    @Autowired
    private StationeryRecordService stationeryRecordService;
    private StationeryRecordMapper stationeryRecordMapper;
/**
 * 查询领用记录
 * @param record 文具记录的查询条件
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@RequestMapping("/searchRecords")
public ModelAndView searchRecords(StationeryRecord stationeryRecord, HttpServletRequest request, Integer pageNum, Integer pageSize){
    if(pageNum==null){
        pageNum=1;
    }
    if(pageSize==null){
        pageSize=10;
    }
    //获取当前登录用户的信息
    User user = ((User) request.getSession().getAttribute("USER_SESSION"));
    PageResult pageResult=stationeryRecordService.searchRecords(stationeryRecord,user,pageNum,pageSize);
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("stationery_record");
    //将查询到的数据存放在 ModelAndView的对象中
    modelAndView.addObject("pageResult",pageResult);
    //将查询的参数返回到页面，用于回显到查询的输入框中
    modelAndView.addObject("search",stationeryRecord);
    //将当前页码返回到页面，用于分页插件的分页显示
    modelAndView.addObject("pageNum",pageNum);
    //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
    modelAndView.addObject("gourl", request.getRequestURI());
    return modelAndView;
}


@ResponseBody
@RequestMapping("/findById/{userId}")
public Result findById(@PathVariable("userId") Integer userId) {
    List<String> itemNames = stationeryRecordService.findById(userId);

    try{
        if (itemNames == null || itemNames.isEmpty()) {
            throw new CustomException("没有找到相关的物品名称");
        }
        return new Result(true,"操作成功",itemNames);
    }catch (CustomException c){
        return new Result(false, c.getMsg());
    }catch(Exception e) {
        e.printStackTrace();
        return new Result(false,"查询失败！");
    }


}

}

