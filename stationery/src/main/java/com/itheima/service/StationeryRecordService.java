package com.itheima.service;
import com.github.pagehelper.Page;
import com.itheima.domain.StationeryRecord;
import com.itheima.domain.User;
import entity.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 领用记录接口
 */
public interface StationeryRecordService {
//新增领用记录
Integer addRecord(StationeryRecord stationeryRecord);
//查询领用记录
PageResult searchRecords(StationeryRecord stationeryRecord, User user, Integer pageNum, Integer pageSize);

//根据id查询某个用户领用过的文具信息
List<String> findById(@Param("userId") Integer userId);

}
