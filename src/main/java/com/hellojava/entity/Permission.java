package com.hellojava.entity;

import java.io.Serializable;

public class Permission implements Serializable {
    private int id;
    private int pId;
    private String pname;
    private String url;
    private String icon;
    private String checked = "false";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", pId=" + pId +
                ", pname='" + pname + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", checked='" + checked + '\'' +
                '}';
    }
}
