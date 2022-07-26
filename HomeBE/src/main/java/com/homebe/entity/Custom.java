package com.homebe.entity;

import java.io.Serializable;

/**
 * (Custom)实体类
 *
 * @author makejava
 * @since 2022-02-26 21:12:25
 */
public class Custom implements Serializable {
    private static final long serialVersionUID = 919051949865698872L;
    /**
     * 客户编号
     */
    private String customId;
    /**
     * 客户姓名
     */
    private String customName;
    /**
     * 客户性别
     */
    private String customSex;
    /**
     * 客户电话
     */
    private String customPhone;
    /**
     * 客户备注
     */
    private String customRemarks;


    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomSex() {
        return customSex;
    }

    public void setCustomSex(String customSex) {
        this.customSex = customSex;
    }

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    public String getCustomRemarks() {
        return customRemarks;
    }

    public void setCustomRemarks(String customRemarks) {
        this.customRemarks = customRemarks;
    }

}

