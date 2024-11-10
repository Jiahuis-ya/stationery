package com.itheima.domain;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class StationeryRecord implements Serializable {
    private Integer record_id; //领用记录id
    private Integer record_ids; //领用文具id
    private String record_name; //领用文具名称
    private String record_borrower; //领用人
    private Integer record_borrower_id; //领用人id
    private String record_nums; //领用数量
    private String record_borrow_time; //领用时间
    private String record_return_time; //文具归还时间

    public Integer getRecordId() {
        return record_id;
    }

    public void setRecordId(Integer recordId) {
        this.record_id = recordId;
    }

        public Integer getRecordIds() {
        return record_ids;
    }

    public void setRecordIds(Integer recordIds) {
        this.record_ids = recordIds;
    }


    public String getRecordName() {
        return record_name;
    }
    
    public void setRecordName(String recordName) {
        this.record_name = recordName;
    }


    public String getRecordBorrower() {
        return record_borrower;
    }

    public void setRecordBorrower(String recordBorrower) {
        this.record_borrower = recordBorrower;
    }


    public Integer getRecordBorrowerId() {
        return record_borrower_id;
    }

    public void setRecordBorrowerId(Integer recordBorrowerId) {
        this.record_borrower_id = recordBorrowerId;
    }
    public String getRecordNums() {
        return record_nums;
    }

    public void setRecordNums(String recordNums) {
        this.record_nums = recordNums;
    }

    public String getRecordBorrowTime() {
        return record_borrow_time;
    }

    public void setRecordBorrowTime(String recordBorrowTime) {
        this.record_borrow_time = recordBorrowTime;
    }

    public String getRecordReturnTime() {
        return record_return_time;
    }

    public void setRecordReturnTime(String recordReturnTime) {
        this.record_return_time = recordReturnTime;
    }
}
