package com.itheima.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Stationery;
import com.itheima.domain.StationeryRecord;
import com.itheima.domain.User;
import com.itheima.mapper.StationeryMapper;
import com.itheima.service.StationeryService;
import com.itheima.service.StationeryRecordService;
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
public class StationeryServiceImpl implements StationeryService {
    @Autowired
    private StationeryMapper stationeryMapper;

/**
 * 根据id查询文具信息
 * @param id 文具id
 */
public Stationery findById(Integer id){
    return stationeryMapper.findById(id);
}

/**
 * 领用文具
 * @param stationery
 * @return
 */
@Override
public Integer borrowStationery(Stationery stationery) {
    //根据id查询出需要领用的完整文具信息
    Stationery b = this.findById(stationery.getId());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //设置当天为领用时间
   stationery.setBorrowTime(dateFormat.format(new Date()));
    //设置所领用的文具状态为领用中
    stationery.setStatus("1");
    //将文具的上架设置在stationery对象中
     stationery.setUploadTime(dateFormat.format(new Date()));

    return stationeryMapper.editStationery(stationery);
}

/**
 * @param stationery 封装查询条件的对象
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult search(Stationery stationery, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<Stationery> page=stationeryMapper.searchStationery(stationery);
    return new PageResult(page.getTotal(),page.getResult());
}

/**
 * 新增文具
 * @param stationery 页面提交的新增文具信息
 */
public Integer addStationery(Stationery stationery) {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //设置新增文具的最近操作时间
    stationery.setUploadTime(dateFormat.format(new Date()));
    stationery.setStatus("0");
    stationery.setNums("1");
    return  stationeryMapper.addStationery(stationery);
}

/**
 * 编辑文具信息
 * @param stationery 文具信息
 */
public Integer editStationery(Stationery stationery) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    stationery.setUploadTime(dateFormat.format(new Date()));
    return stationeryMapper.editStationery(stationery);
}

/**
 * 查询用户当前领用的文具
 * @param stationery 封装查询条件的对象
 * @param user 当前登录用户
 * @param pageNum 当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult searchBorrowed(Stationery stationery, User user, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<Stationery> page;
    //如果是管理员，查询自己领用但未归还的文具和所有待确认归还的文具
    if("ADMIN".equals(user.getRole())){
        page= stationeryMapper.selectBorrowed(stationery);
    }else {
        //如果是普通用户，查询自己领用但未归还的文具
        //将当前登录的用户放入查询条件中
        stationery.setBorrower(user.getName());
        page= stationeryMapper.selectMyBorrowed(stationery);
    }
    return new PageResult(page.getTotal(),page.getResult());
}

/**
 * 归还文具
 * @param id 归还的文具的id
 * @param user 归还的人员，也就是当前文具的领用者
 */
@Override
public boolean returnStationery(Integer id,User user) {
    //根据文具id查询出文具的完整信息
    Stationery stationery = this.findById(id);
    //再次核验当前登录人员和文具领用者是不是同一个人
    boolean rb=stationery.getBorrower().equals(user.getName());
    //如果是同一个人，允许归还
    if(rb){
        //将文具领用状态修改为归还中
        stationery.setStatus("2");
        stationeryMapper.editStationery(stationery);
    }
    return rb;
}
@Autowired
//注入RecordService对象
private StationeryRecordService stationeryRecordService;
/**
 * 归还确认
 * @param id 待归还确认的文具id
 */
@Override
public Integer returnConfirm(Integer id) {
    //根据文具id查询文具的完整信息
    Stationery stationery = this.findById(id);
    //根据归还确认的文具信息，设置领用记录
    StationeryRecord stationeryRecord = this.setRecord(stationery);
    //将领用状态修改为可领用
    stationery.setStatus("0");
    //清除领用人
    stationery.setBorrower("");
    //清除领用时间
    stationery.setBorrowTime("");
    //清除预计归还时间
    stationery.setReturnTime("");
    Integer count= stationeryMapper.editStationery(stationery);
    //如果归还确认成功，则新增领用记录
    if(count==1){
        return  stationeryRecordService.addRecord(stationeryRecord);
    }
    return 0;
}
/**
 * 根据文具信息设置领用记录的信息
 * @param stationery 领用的文具信息
 */
private StationeryRecord setRecord(Stationery stationery){
    StationeryRecord stationeryRecord = new StationeryRecord();
    //文具名称
    stationeryRecord.setRecordName(stationery.getName());
    //领用人
    stationeryRecord.setRecordBorrower(stationery.getBorrower());
    //领用人id
    stationeryRecord.setRecordBorrowerId(stationery.getBorrowerId());
    //领用数量
    stationeryRecord.setRecordNums(stationery.getNums());
    //领用文具id
    stationeryRecord.setRecordIds(stationery.getIds());
    //领用时间
    stationeryRecord.setRecordBorrowTime(stationery.getBorrowTime());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //设置文具归还确认的当天为文具归还时间
    stationeryRecord.setRecordReturnTime(dateFormat.format(new Date()));
    return stationeryRecord;
}
}
