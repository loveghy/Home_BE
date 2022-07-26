package com.homebe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Staff)实体类
 *
 * @author makejava
 * @since 2022-03-10 21:03:43
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 532558286811170979L;
    /**
     * 员工编号/账号
     */
    private String staffId;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 性别
     */
    private String staffSex;
    /**
     * 电话号码
     */
    private String staffPhone;
    /**
     * 工资
     */
    private Double staffWages;
    /**
     * 备注
     */
    private String staffRemarks;
    /**
     * 在职状态（离职,在职）
     */
    private String staffState;
    /**
     * 员工角色(0经理,1普通员工)
     */
    private String staffRoles;
    /**
     * 员工入职时间
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date staffTime;
    /**
     * 登录密码
     */
    private String password;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public Double getStaffWages() {
        return staffWages;
    }

    public void setStaffWages(Double staffWages) {
        this.staffWages = staffWages;
    }

    public String getStaffRemarks() {
        return staffRemarks;
    }

    public void setStaffRemarks(String staffRemarks) {
        this.staffRemarks = staffRemarks;
    }

    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    public String getStaffRoles() {
        return staffRoles;
    }

    public void setStaffRoles(String staffRoles) {
        this.staffRoles = staffRoles;
    }

    public Date getStaffTime() {
        return staffTime;
    }

    public void setStaffTime(Date staffTime) {
        this.staffTime = staffTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffSex='" + staffSex + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                ", staffWages=" + staffWages +
                ", staffRemarks='" + staffRemarks + '\'' +
                ", staffState='" + staffState + '\'' +
                ", staffRoles='" + staffRoles + '\'' +
                ", staffTime=" + staffTime +
                ", password='" + password + '\'' +
                '}';
    }
}

