package com.baize.framework.domain;

import java.util.Date;

public class Category {
    private Integer id;

    private Integer sectionId;

    private Integer subjectId;

    private Date createDate;

    private Date modifyDate;

    public Category(Integer id, Integer sectionId, Integer subjectId, Date createDate, Date modifyDate) {
        this.id = id;
        this.sectionId = sectionId;
        this.subjectId = subjectId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Category() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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