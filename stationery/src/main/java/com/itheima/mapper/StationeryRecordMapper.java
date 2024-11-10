package com.itheima.mapper;
import com.github.pagehelper.Page;
import com.itheima.domain.StationeryRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StationeryRecordMapper {
//新增领用记录
Integer addRecord(StationeryRecord stationeryRecord);
@Select({"<script>" +
        "SELECT * FROM stationery_record " +
        "where 1=1" +
        "<if test=\"record_borrower != null\">AND record_borrower like  CONCAT('%',#{record_borrower},'%')</if>" +
        "<if test=\"record_name != null\">AND record_name  like  CONCAT('%',#{record_name},'%') </if>" +
        "order by record_return_time DESC" +
        "</script>"
})
@Results(id = "stationeryRecordMap",value = {
        //id字段默认为false，表示不是主键
        //column表示数据库表字段，property表示实体类属性名。
        @Result(id = true,column = "record_id",property = "record_id"),
        @Result(column = "record_ids",property = "record_ids"),
        @Result(column = "record_name",property = "record_name"),
        @Result(column = "record_borrower",property = "record_borrower"),
        @Result(column = "record_borrower_id",property = "record_borrower_id"),
        @Result(column = "record_nums",property = "record_nums"),
        @Result(column = "record_borrow_time",property = "record_borrow_time"),
        @Result(column = "record_return_time",property = "record_return_time")
})
//查询领用记录
Page<StationeryRecord> searchRecords(StationeryRecord stationeryRecord);

@Select({"<script>" +
        "SELECT stationery_record.* " +
        "FROM stationery_record " +
        "JOIN user ON user.user_id = stationery_record.record_borrower_id " +
        "JOIN stationerys ON stationery_record.record_ids = stationerys.id " +
        "WHERE user.user_id=record_borrower_id " +
        "<if test=\"record_borrower != null\">AND record_borrower like  CONCAT('%',#{record_borrower},'%')</if>" +
        "<if test=\"record_name != null\">AND record_name  like  CONCAT('%',#{record_name},'%') </if>" +
        "order by record_return_time DESC" +
        "</script>"
})

@ResultMap("stationeryRecordMap")
// 查询当前用户的领用记录--(多表查询)
Page<StationeryRecord> searchMyRecords(StationeryRecord stationeryRecord);

//@Select("SELECT record_name FROM stationery_record where record_borrower_id=#{userId}")
// 多表查询第1种
// @Select({"<script>" +
//         "SELECT stationery_record.* FROM stationery_record JOIN user ON user.user_id = stationery_record.record_borrower_id JOIN stationerys ON stationery_record.record_ids = stationerys.id where user.user_id=#{userId}" +
//         "</script>"
// })
// 多表查询第2种
@Select({"<script>" +
        "SELECT stationery_record.* " +
        "FROM stationery_record " +
        "JOIN user ON user.user_id = stationery_record.record_borrower_id " +
        "JOIN stationerys ON stationery_record.record_ids = stationerys.id " +
        "WHERE user.user_id=#{userId} " +
        "</script>"
})


@ResultMap("stationeryRecordMap")
//根据id查询文具信息--(多表查询)
List<String> findById(@Param("userId") Integer userId);
}
