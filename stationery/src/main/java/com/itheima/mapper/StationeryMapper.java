package com.itheima.mapper;
import com.github.pagehelper.Page;
import com.itheima.domain.Stationery;
import org.apache.ibatis.annotations.*;

/**
 * 文具接口
 */
public interface StationeryMapper {
@Select("SELECT * FROM stationery_requisition where id=#{id}")
    @Results(id = "stationeryRequisitionMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "ids",property = "ids"),
            @Result(column = "name",property = "name"),
            @Result(column = "nums",property = "nums"),
            @Result(column = "department",property = "department"),
            @Result(column = "specification",property = "specification"),
            @Result(column = "price",property = "price"),
            @Result(column = "upload_time",property = "upload_time"),
            @Result(column = "status",property = "status"),
            @Result(column = "borrower",property = "borrower"),
            @Result(column = "borrower_id",property = "borrower_id"),
            @Result(column = "borrow_time",property = "borrow_time"),
            @Result(column = "return_time",property = "return_time")
    })
//根据id查询文具信息
Stationery findById(Integer id);


    @Select({"<script>" +
            "SELECT * FROM stationery_requisition " +
            "where status !='3'" +
            "<if test=\"name != null\"> AND  name  like  CONCAT('%',#{name},'%')</if>" +
            "<if test=\"borrower != null\"> AND  borrower  like  CONCAT('%',#{borrower},'%')</if>" +
            "order by borrow_time" +
            "</script>"
    })
    @ResultMap("stationeryRequisitionMap")
    //分页查询文具
    Page<Stationery> searchStationery(Stationery stationery);
    //新增文具
    Integer addStationery(Stationery stationery);
    //编辑文具信息
    Integer editStationery(Stationery stationery);

        @Select({"<script>"  +
                "SELECT * FROM stationery_requisition " +
                "where status in('1','2')"+
                "<if test=\"name != null\"> AND  name  like  CONCAT('%',#{name},'%')</if>" +
                "<if test=\"borrower != null\"> AND  borrower  like  CONCAT('%',#{borrower},'%')</if>" +
                "order by borrow_time" +
                "</script>"})
        @ResultMap("stationeryRequisitionMap")

        //查询领用但未归还的文具和待归还确认的文具
        Page<Stationery> selectBorrowed(Stationery stationery);

        @Select(
                {"<script>" +
                "SELECT * FROM stationery_requisition " +
                "where borrower=#{borrower}" +
                "AND status ='1'"+
                "<if test=\"name != null\"> AND  name  like  CONCAT('%',#{name},'%')</if>" +
                "<if test=\"borrower != null\"> AND  borrower  like  CONCAT('%',#{borrower},'%')</if>" +
                "or status ='2'"+
                "<if test=\"name != null\"> AND  name  like  CONCAT('%',#{name},'%')</if>" +
                "<if test=\"borrower != null\"> AND  borrower  like  CONCAT('%',#{borrower},'%')</if>" +
                "order by borrow_time" +
                "</script>"})
        @ResultMap("stationeryRequisitionMap")
        //查询领用但未归还的文具
        Page<Stationery> selectMyBorrowed(Stationery stationery);

}
