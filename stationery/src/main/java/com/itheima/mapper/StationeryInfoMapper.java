package com.itheima.mapper;
import com.github.pagehelper.Page;
import com.itheima.domain.StationeryInfo;
import org.apache.ibatis.annotations.*;

/**
 * 文具接口
 */
public interface StationeryInfoMapper {
@Select("SELECT * FROM stationerys where id=#{id}")
@Results(id = "stationeryMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "specification",property = "specification"),
            @Result(column = "supplier",property = "supplier"),
            @Result(column = "purchaser",property = "purchaser"),
            @Result(column = "price",property = "price"),
            @Result(column = "upload_time",property = "upload_time"),
    })
//根据id查询文具信息
StationeryInfo findById(Integer id);


@Select({"<script>" +
        "SELECT * FROM stationerys " +
        "where 1=1" +
        "<if test=\"name != null\"> AND  name  like  CONCAT('%',#{name},'%')</if>" +
        "<if test=\"supplier != null\"> AND supplier like  CONCAT('%', #{supplier},'%') </if>" +
        "<if test=\"purchaser != null\"> AND purchaser like  CONCAT('%', #{purchaser},'%')</if>" +
        // "order by upload_time" +
        "</script>"
})
@ResultMap("stationeryMap")
//分页查询文具信息
Page<StationeryInfo> searchStationery(StationeryInfo stationeryInfo);


}
