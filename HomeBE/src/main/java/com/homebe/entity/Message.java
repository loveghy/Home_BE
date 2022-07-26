package com.homebe.entity;

import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2022-04-27 20:40:19
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 105473897280622972L;
    /**
     * 留言号
     */
    private Integer messageId;
    /**
     * 留言详情
     */
    private String messageRemarks;
    /**
     * 房源编号
     */
    private String houseId;
    /**
     * 游客账号
     */
    private String userId;
    /**
     * 回复
     */
    private String remessage;
    /**
     * 回复人
     */
    private String manger;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageRemarks() {
        return messageRemarks;
    }

    public void setMessageRemarks(String messageRemarks) {
        this.messageRemarks = messageRemarks;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemessage() {
        return remessage;
    }

    public void setRemessage(String remessage) {
        this.remessage = remessage;
    }

    public String getManger() {
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }

}

