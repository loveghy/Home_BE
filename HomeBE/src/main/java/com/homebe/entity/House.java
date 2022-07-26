package com.homebe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (House)实体类
 *
 * @author makejava
 * @since 2022-03-14 20:25:58
 */
public class House implements Serializable {
    private static final long serialVersionUID = 779134439715249571L;
    /**
     * 编号
     */
    private String houseId;
    /**
     * 标题(房屋出租/房屋出售)
     */
    private String houseTitle;
    /**
     * 地址
     */
    private String houseArea;
    /**
     * 租金/成交价（价格）
     */
    private Double housePay;
    /**
     * 详情描述
     */
    private String houseContent;
    /**
     * 图片
     */
    private String housePicture;
    /**
     * 房源状态（已租/未租,已售/未售）
     */
    private String houseState;
    /**
     * 联系人（员工填写个人账号）
     */
    private String houseContact;
    /**
     * 电话
     */
    private String housePhone;
    /**
     * 房主
     */
    private String houserName;
    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date starttime;


    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public Double getHousePay() {
        return housePay;
    }

    public void setHousePay(Double housePay) {
        this.housePay = housePay;
    }

    public String getHouseContent() {
        return houseContent;
    }

    public void setHouseContent(String houseContent) {
        this.houseContent = houseContent;
    }

    public String getHousePicture() {
        return housePicture;
    }

    public void setHousePicture(String housePicture) {
        this.housePicture = housePicture;
    }

    public String getHouseState() {
        return houseState;
    }

    public void setHouseState(String houseState) {
        this.houseState = houseState;
    }

    public String getHouseContact() {
        return houseContact;
    }

    public void setHouseContact(String houseContact) {
        this.houseContact = houseContact;
    }

    public String getHousePhone() {
        return housePhone;
    }

    public void setHousePhone(String housePhone) {
        this.housePhone = housePhone;
    }

    public String getHouserName() {
        return houserName;
    }

    public void setHouserName(String houserName) {
        this.houserName = houserName;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

}

