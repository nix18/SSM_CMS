package com.moecola.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Long aId;
    private Integer aIsPosted;
    private String aTitle;
    private String aIntro;
    private String aContent;
    private String aUrl;
    private Long aByUid;
    private Date aTime;
    private String aTag;
    private Long readCount;


    @Override
    public String toString() {
        return "Article{" +
                "aId=" + aId +
                ", aIsPosted=" + aIsPosted +
                ", aTitle='" + aTitle + '\'' +
                ", aIntro='" + aIntro + '\'' +
                ", aContent='" + aContent + '\'' +
                ", aUrl='" + aUrl + '\'' +
                ", aByUid=" + aByUid +
                ", aTime=" + aTime +
                ", aTag='" + aTag + '\'' +
                ", readCount=" + readCount +
                '}';
    }

    public Article(Long aId, Integer aIsPosted, String aTitle, String aIntro, String aContent, String aUrl, Long aByUid, Date aTime, String aTag, Long readCount) {
        this.aId = aId;
        this.aIsPosted = aIsPosted;
        this.aTitle = aTitle;
        this.aIntro = aIntro;
        this.aContent = aContent;
        this.aUrl = aUrl;
        this.aByUid = aByUid;
        this.aTime = aTime;
        this.aTag = aTag;
        this.readCount = readCount;
    }

    public Article() {
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Integer getaIsPosted() {
        return aIsPosted;
    }

    public void setaIsPosted(Integer aIsPosted) {
        this.aIsPosted = aIsPosted;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getaIntro() {
        return aIntro;
    }

    public void setaIntro(String aIntro) {
        this.aIntro = aIntro;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public String getaUrl() {
        return aUrl;
    }

    public void setaUrl(String aUrl) {
        this.aUrl = aUrl;
    }

    public Long getaByUid() {
        return aByUid;
    }

    public void setaByUid(Long aByUid) {
        this.aByUid = aByUid;
    }

    public Date getaTime() {
        return aTime;
    }

    public void setaTime(Date aTime) {
        this.aTime = aTime;
    }

    public String getaTag() {
        return aTag;
    }

    public void setaTag(String aTag) {
        this.aTag = aTag;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }
}
