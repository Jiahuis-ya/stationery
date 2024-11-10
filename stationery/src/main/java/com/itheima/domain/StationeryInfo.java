package com.itheima.domain;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class StationeryInfo implements Serializable {
    private Integer id;        //文具id
    private String name;       //文具名称
    private String specification; //规格/型号
    private String supplier; //文具供应商
    private String purchaser; //文具采购人
    private Double price;   //文具价格
    private String upload_time; //文具上架时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPurchaser() {
        return purchaser;
    }
    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
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
}
