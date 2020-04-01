package com.baize.framework.domain;

import java.util.Date;

public class OrderItem {
    private Integer id;

    private String name;

    private Integer sectionId;

    private Integer subjectId;

    private Date createDate;

    private Date modifyDate;

    public OrderItem(Integer id, String name, Integer sectionId, Integer subjectId, Date createDate, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.sectionId = sectionId;
        this.subjectId = subjectId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public OrderItem() {
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

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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