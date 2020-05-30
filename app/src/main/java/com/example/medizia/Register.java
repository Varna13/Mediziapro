package com.example.medizia;

public class Register {
    String name;
    String address;
    String email;
    String phone;
    String place;
    String password;

    public Register() {
    }

    public Register(String name, String address, String email, String phone, String place, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.place = place;
        this.password = password;
    }

    public String getName()
    {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
