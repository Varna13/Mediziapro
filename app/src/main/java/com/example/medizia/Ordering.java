package com.example.medizia;

public class Ordering {
    String prescribtion;
    String no_of_medicine;
    String dldate;
    Boolean status;

    public Ordering()
    {
        status=false;
    }

    public Ordering(String prescribtion, String no_of_medicine, String dldate, String location) {
        this.prescribtion = prescribtion;
        this.no_of_medicine = no_of_medicine;
        this.dldate = dldate;

    }

    public String getPrescribtion() {
        return prescribtion;
    }

    public void setPrescribtion(String prescribtion) {
        this.prescribtion = prescribtion;
    }

    public String getNo_of_medicine() {
        return no_of_medicine;
    }

    public void setNo_of_medicine(String no_of_medicine) {
        this.no_of_medicine = no_of_medicine;
    }

    public String getDldate() {
        return dldate;
    }

    public void setDldate(String dldate) {
        this.dldate = dldate;
    }


}
