package com.moecola.cms.domain;

import java.io.Serializable;

public class Resource implements Serializable {
    private Long rId;
    private String rName;
    private String rUrl;
    private String rCat;
    private Long rByUid;
    private Long rByAid;
    private String realPath;

    @Override
    public String toString() {
        return "Resource{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                ", rUrl='" + rUrl + '\'' +
                ", rCat='" + rCat + '\'' +
                ", rByUid=" + rByUid +
                ", rByAid=" + rByAid +
                ", realPath='" + realPath + '\'' +
                '}';
    }

    public Resource() {
    }

    public Resource(String rName, String rUrl, String rCat, Long rByUid, Long rByAid, String realPath) {
        this.rName = rName;
        this.rUrl = rUrl;
        this.rCat = rCat;
        this.rByUid = rByUid;
        this.rByAid = rByAid;
        this.realPath = realPath;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrUrl() {
        return rUrl;
    }

    public void setrUrl(String rUrl) {
        this.rUrl = rUrl;
    }

    public String getrCat() {
        return rCat;
    }

    public void setrCat(String rCat) {
        this.rCat = rCat;
    }

    public Long getrByUid() {
        return rByUid;
    }

    public void setrByUid(Long rByUid) {
        this.rByUid = rByUid;
    }

    public Long getrByAid() {
        return rByAid;
    }

    public void setrByAid(Long rByAid) {
        this.rByAid = rByAid;
    }
}
