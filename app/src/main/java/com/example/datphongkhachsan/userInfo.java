package com.example.datphongkhachsan;

public class userInfo {
    String fullName;
    String phone;
    String address;
    String email;

    public userInfo(String fullName, String phone, String address, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
