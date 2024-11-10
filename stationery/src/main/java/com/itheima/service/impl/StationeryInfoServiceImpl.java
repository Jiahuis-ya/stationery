package com.itheima.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.StationeryInfo;
import com.itheima.mapper.StationeryInfoMapper;
import com.itheima.service.StationeryInfoService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StationeryInfoServiceImpl implements StationeryInfoService {
    @Autowired
    private StationeryInfoMapper stationeryInfoMapper;

/**
 * @param stationeryInfo 封装查询条件的对象
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult search(StationeryInfo stationeryInfo, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<StationeryInfo> page = stationeryInfoMapper.searchStationery(stationeryInfo);
    return new PageResult(page.getTotal(),page.getResult());
}

/**
 * 根据id查询文具信息
 * @param id 文具id
 */
public StationeryInfo findById(Integer id) {
    return stationeryInfoMapper.findById(id);
}

}