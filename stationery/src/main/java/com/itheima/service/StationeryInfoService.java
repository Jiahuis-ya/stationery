package com.itheima.service;
import com.itheima.domain.StationeryInfo;
import com.itheima.domain.User;
import entity.PageResult;

/**
 * 文具接口
 */
public interface StationeryInfoService {
//分页查询文具
PageResult search(StationeryInfo stationeryInfo, Integer pageNum, Integer pageSize);
//根据id查询文具信息
StationeryInfo findById(Integer id);
}
