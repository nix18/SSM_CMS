package com.moecola.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class MainComment implements Serializable {
    private Long cId;
    private String cName;
    private String cContent;
    private Date cTime;
    private Long cByAid;

    @Override
    public String toString() {
        return "MainComment{" +
                "cID=" + cId +
                ", cName='" + cName + '\'' +
                ", cContent='" + cContent + '\'' +
                ", cTime=" + cTime +
                ", cByAId=" + cByAid +
                '}';
    }

    public MainComment(Long cID, String cName, String cContent, Date cTime, Long cByAId) {
        this.cId = cID;
        this.cName = cName;
        this.cContent = cContent;
        this.cTime = cTime;
        this.cByAid = cByAId;
    }

    public MainComment() {
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Long getcByAid() {
        return cByAid;
    }

    public void setcByAid(Long cByAid) {
        this.cByAid = cByAid;
    }

}
