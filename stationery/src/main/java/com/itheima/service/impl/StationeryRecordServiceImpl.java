package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.StationeryRecord;
import com.itheima.domain.User;
import com.itheima.mapper.StationeryRecordMapper;
import com.itheima.service.StationeryRecordService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StationeryRecordServiceImpl implements StationeryRecordService {
    @Autowired
    private StationeryRecordMapper stationeryRecordMapper;

/**
 * 新增领用记录
 * @param stationeryRecord 新增的领用记录
 */
@Override
public Integer addRecord(StationeryRecord stationeryRecord) {
    return stationeryRecordMapper.addRecord(stationeryRecord);
}

/**
 * 查询领用记录
 * @param stationeryRecord 领用记录的查询条件
 * @param user 当前的登录用户
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult searchRecords(StationeryRecord stationeryRecord, User user, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<StationeryRecord> page;
    //如果不是管理员，则查询条件中的领用人设置为当前登录用户
    if(!"ADMIN".equals(user.getRole())){
        stationeryRecord.setRecordBorrower(user.getName());
        stationeryRecord.setRecordBorrowerId(user.getId());
        page = stationeryRecordMapper.searchMyRecords(stationeryRecord);
    } else {
        page = stationeryRecordMapper.searchRecords(stationeryRecord);
    }
    
    return new PageResult(page.getTotal(),page.getResult());
}

/**
 * 根据id查询某个用户领用过的文具信息
 * @param id 用户id
 */
public List<String> findById(Integer userId) {
    return stationeryRecordMapper.findById(userId);
}
}
