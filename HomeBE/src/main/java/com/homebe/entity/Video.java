package com.homebe.entity;

import java.io.Serializable;

/**
 * (Video)实体类
 *
 * @author makejava
 * @since 2022-04-22 12:29:51
 */
public class Video implements Serializable {
    private static final long serialVersionUID = 984818041555139720L;
    /**
     * 视屛编号
     */
    private String id;
    /**
     * 视屏地址
     */
    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

