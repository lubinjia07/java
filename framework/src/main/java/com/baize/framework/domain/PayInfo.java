package com.baize.framework.domain;

import java.util.Date;

public class PayInfo {
    private Integer id;

    private Integer subjectId;

    private Integer exerciseCategoryId;

    private Date createDate;

    private Date modifyDate;

    public PayInfo(Integer id, Integer subjectId, Integer exerciseCategoryId, Date createDate, Date modifyDate) {
        this.id = id;
        this.subjectId = subjectId;
        this.exerciseCategoryId = exerciseCategoryId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public PayInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getExerciseCategoryId() {
        return exerciseCategoryId;
    }

    public void setExerciseCategoryId(Integer exerciseCategoryId) {
        this.exerciseCategoryId = exerciseCategoryId;
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