package com.baize.framework.domain;

import java.util.Date;

public class Cart {
    private Integer id;

    private String name;

    private Integer level;

    private String code;

    private Long parentid;

    private Date createDate;

    private Date modifyDate;

    public Cart(Integer id, String name, Integer level, String code, Long parentid, Date createDate, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.code = code;
        this.parentid = parentid;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Cart() {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
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