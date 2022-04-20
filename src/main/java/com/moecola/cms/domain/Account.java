package com.moecola.cms.domain;

import java.io.Serializable;

public class Account implements Serializable {
    private long uid;
    private String username;
    private String profile;
    private String password;
    private int isAdmin;
    public Account() {
    }

    public Account(String username, String profile, String password) {
        this.username = username;
        this.profile = profile;
        this.password = password;
    }

    public Account(long uid, String username, String profile, String password) {
        this.uid = uid;
        this.username = username;
        this.profile = profile;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", profile='" + profile + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
