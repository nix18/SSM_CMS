package com.moecola.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class SubComment implements Serializable {
    private Long scId;
    private String scName;
    private String scContent;
    private Date scTime;
    private Long scByCid;

    @Override
    public String toString() {
        return "SubComment{" +
                "scID=" + scId +
                ", scName='" + scName + '\'' +
                ", scContent='" + scContent + '\'' +
                ", scTime=" + scTime +
                ", scByCID=" + scByCid +
                '}';
    }

    public SubComment(Long scID, String scName, String scContent, Date scTime, Long scByCID) {
        this.scId = scID;
        this.scName = scName;
        this.scContent = scContent;
        this.scTime = scTime;
        this.scByCid = scByCID;
    }

    public SubComment() {
    }

    public Long getScId() {
        return scId;
    }

    public void setScId(Long scId) {
        this.scId = scId;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getScContent() {
        return scContent;
    }

    public void setScContent(String scContent) {
        this.scContent = scContent;
    }

    public Date getScTime() {
        return scTime;
    }

    public void setScTime(Date scTime) {
        this.scTime = scTime;
    }

    public Long getScByCid() {
        return scByCid;
    }

    public void setScByCid(Long scByCid) {
        this.scByCid = scByCid;
    }
}
