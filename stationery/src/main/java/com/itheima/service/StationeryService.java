package com.itheima.service;
import com.itheima.domain.Stationery;
import com.itheima.domain.User;
import entity.PageResult;

/**
 * 文具接口
 */
public interface StationeryService {
//根据id查询文具信息
Stationery findById(Integer id);
//领用文具
Integer borrowStationery(Stationery stationery);
//分页查询文具
PageResult search(Stationery stationery, Integer pageNum, Integer pageSize);
//新增文具
Integer addStationery(Stationery stationery);
//编辑文具信息
Integer editStationery(Stationery stationery);
//查询当前领用的文具
PageResult searchBorrowed(Stationery stationery, User user, Integer pageNum, Integer pageSize);
//归还文具
boolean returnStationery(Integer id,User user);
//归还确认
Integer returnConfirm(Integer id);

}
