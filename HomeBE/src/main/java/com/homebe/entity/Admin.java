package com.homebe.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2022-02-24 12:44:45
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = -97475698035469466L;
    /**
     * 账号
     */
    private String adminName;
    /**
     * 密码
     */
    private String adminPassword;


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}

