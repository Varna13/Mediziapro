package com.example.medizia;

public class Volunteerlist
{
    String vname;
    String vid;
    String vphn;
    String vpswd;

    public Volunteerlist()
    {
    }

    public Volunteerlist(String vname, String vid, String vphn,String vpswd) {
        this.vname = vname;
        this.vid = vid;
        this.vphn = vphn;
        this.vpswd = vpswd;

    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVphn() {
        return vphn;
    }

    public void setVphn(String vphn) {
        this.vphn = vphn;
    }

    public void setVpswd(String vpswd) { this.vpswd = vpswd; }
}

