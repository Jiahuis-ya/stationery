package com.itheima.domain;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

// import javax.validation.constraints.NotNull;

public class Stationery implements Serializable {
    private Integer id; //领用id
    private Integer ids; //文具id
    private String name; //文具名称
    private String department;//领用部门
    private String specification; //规格/型号
    private Double price; //文具价格
    private String upload_time; //文具上架时间
    private String status; //状态（0：可领用，1:已领用，2：归还中，3：已下架）
    private String borrower; //领用人
    private Integer borrower_id; //领用人id
    private String borrow_time; //领用时间
    // @NotNull
    private String nums;       //领用数量
    private String return_time; //预计归还时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

     public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUploadTime() {
        return upload_time;
    }

    public void setUploadTime(String uploadTime) {
        this.upload_time = uploadTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    public Integer getBorrowerId() {
        return borrower_id;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrower_id = borrowerId;
    }

    public String getBorrowTime() {
        return borrow_time;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrow_time = borrowTime;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getReturnTime() {
        return return_time;
    }

    public void setReturnTime(String returnTime) {
        this.return_time = returnTime;
    }
}
