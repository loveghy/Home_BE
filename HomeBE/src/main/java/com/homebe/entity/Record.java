package com.homebe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Record)实体类
 *
 * @author makejava
 * @since 2022-04-01 11:58:51
 */
public class Record implements Serializable {
    private static final long serialVersionUID = 933898903336423106L;
    /**
     * 记录编号
     */
    private String recordId;
    /**
     * 房源编号
     */
    private String houseId;
    /**
     * 交易状态/已支付/未支付
     */
    private String recordState;
    /**
     * 交易金额
     */
    private Double recordPay;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date recordStart;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date recordEnd;
    /**
     * 交易人
     */
    private String recordContact;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 中介费
     */
    private Double agencyFee;
    /**
     * 员工编号
     */
    private String staffId;
    /**
     * 售房/租房
     */
    private String recordType;


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getRecordState() {
        return recordState;
    }

    public void setRecordState(String recordState) {
        this.recordState = recordState;
    }

    public Double getRecordPay() {
        return recordPay;
    }

    public void setRecordPay(Double recordPay) {
        this.recordPay = recordPay;
    }

    public Date getRecordStart() {
        return recordStart;
    }

    public void setRecordStart(Date recordStart) {
        this.recordStart = recordStart;
    }

    public Date getRecordEnd() {
        return recordEnd;
    }

    public void setRecordEnd(Date recordEnd) {
        this.recordEnd = recordEnd;
    }

    public String getRecordContact() {
        return recordContact;
    }

    public void setRecordContact(String recordContact) {
        this.recordContact = recordContact;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(Double agencyFee) {
        this.agencyFee = agencyFee;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

}

