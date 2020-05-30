package com.example.medizia;

public class Admindetail {
    String auname;
    String apswd;

    public Admindetail() {

    }

    public Admindetail(String auname, String apswd) {
        this.auname = auname;
        this.apswd = apswd;
    }

    public String getAuname() {
        return auname;
    }

    public void setAuname(String auname) {
        this.auname = auname;
    }

    public String getApswd() {
        return apswd;
    }

    public void setApswd(String apswd) {
        this.apswd = apswd;
    }
}

