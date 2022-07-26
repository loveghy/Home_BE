package com.homebe.entity;

import java.io.Serializable;

/**
 * (Housetype)实体类
 *
 * @author makejava
 * @since 2022-03-14 22:18:56
 */
public class Housetype implements Serializable {
    private static final long serialVersionUID = -10020383133515224L;

    private Integer id;
    /**
     * 房屋类型
     */
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

