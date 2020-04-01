package com.baize.framework.domain;

import java.util.Date;

public class CartItem {
    private Integer id;

    private String name;

    private Integer sequence;

    private Date createDate;

    private Date modifyDate;

    public CartItem(Integer id, String name, Integer sequence, Date createDate, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public CartItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}