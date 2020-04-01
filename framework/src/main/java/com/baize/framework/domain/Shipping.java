package com.baize.framework.domain;

import java.util.Date;

public class Shipping {
    private Integer id;

    private String name;

    private Long sectionId;

    private Long subjectId;

    private Long textbookId;

    private Date createDate;

    private Date modifyDate;

    public Shipping(Integer id, String name, Long sectionId, Long subjectId, Long textbookId, Date createDate, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.sectionId = sectionId;
        this.subjectId = subjectId;
        this.textbookId = textbookId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Shipping() {
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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(Long textbookId) {
        this.textbookId = textbookId;
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