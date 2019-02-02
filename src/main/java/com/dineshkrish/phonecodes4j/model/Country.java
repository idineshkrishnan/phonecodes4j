package com.dineshkrish.phonecodes4j.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("phone_code")
    private int phoneCode;

    @SerializedName("country_alpha_code_2")
    private String countryAlphaCode2;

    @SerializedName("country_alpha_code_3")
    private String countryAlphaCode3;

    @SerializedName("country_name")
    private String countryName;

    public int getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(int phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCountryAlphaCode2() {
        return countryAlphaCode2;
    }

    public void setCountryAlphaCode2(String countryAlphaCode2) {
        this.countryAlphaCode2 = countryAlphaCode2;
    }

    public String getCountryAlphaCode3() {
        return countryAlphaCode3;
    }

    public void setCountryAlphaCode3(String countryAlphaCode3) {
        this.countryAlphaCode3 = countryAlphaCode3;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "phoneCode=" + phoneCode +
                ", countryAlphaCode2='" + countryAlphaCode2 + '\'' +
                ", countryAlphaCode3='" + countryAlphaCode3 + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
