package com.homebe.entity;

import java.io.Serializable;

/**
 * (Houser)实体类
 *
 * @author makejava
 * @since 2022-02-26 21:11:59
 */
public class Houser implements Serializable {
    private static final long serialVersionUID = -59184544761722338L;
    /**
     * 房东编号
     */
    private String houserId;
    /**
     * 姓名
     */
    private String houserName;
    /**
     * 性别
     */
    private String houserSex;
    /**
     * 联系方式
     */
    private String houserPhone;
    /**
     * 地址
     */
    private String houserWages;
    /**
     * 备注
     */
    private String houserRemarks;


    public String getHouserId() {
        return houserId;
    }

    public void setHouserId(String houserId) {
        this.houserId = houserId;
    }

    public String getHouserName() {
        return houserName;
    }

    public void setHouserName(String houserName) {
        this.houserName = houserName;
    }

    public String getHouserSex() {
        return houserSex;
    }

    public void setHouserSex(String houserSex) {
        this.houserSex = houserSex;
    }

    public String getHouserPhone() {
        return houserPhone;
    }

    public void setHouserPhone(String houserPhone) {
        this.houserPhone = houserPhone;
    }

    public String getHouserWages() {
        return houserWages;
    }

    public void setHouserWages(String houserWages) {
        this.houserWages = houserWages;
    }

    public String getHouserRemarks() {
        return houserRemarks;
    }

    public void setHouserRemarks(String houserRemarks) {
        this.houserRemarks = houserRemarks;
    }

}

