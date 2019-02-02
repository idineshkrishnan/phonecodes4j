package com.dineshkrish.phonecodes4j.test;

import com.dineshkrish.phonecodes4j.PhoneCodes;
import com.dineshkrish.phonecodes4j.error.PhoneCodesException;
import com.dineshkrish.phonecodes4j.model.Type;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class PhoneCodesTest {

    @Test
    public void getCountryNameByPhoneCodeTest() {
        PhoneCodes phoneCodes = PhoneCodes.getInstance();
        String result1 = phoneCodes.getCountryNameByPhoneCode(1);
        String result2 = phoneCodes.getCountryNameByPhoneCode("1");
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void getCountryNamesByPhoneCodeTest() {
        PhoneCodes phoneCodes = PhoneCodes.getInstance();
        Map<Integer, String> result1 = phoneCodes.getCountryNamesByPhoneCode(1, 91);
        Map<Integer, String> result2 = phoneCodes.getCountryNamesByPhoneCode("1", "91");
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void getCountryCodeByPhoneCodeTest() {
        PhoneCodes phoneCodes = PhoneCodes.getInstance();
        String result1 = phoneCodes.getCountryCodeByPhoneCode(1, Type.ALPHA_CODE_3);
        String result2 = phoneCodes.getCountryCodeByPhoneCode("1", Type.ALPHA_CODE_3);
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void getPhoneCodeByCountryCodeTest() {
        PhoneCodes phoneCodes = PhoneCodes.getInstance();
        Integer result1 = phoneCodes.getPhoneCodeByCountryCode("IN");
        Integer result2 = phoneCodes.getPhoneCodeByCountryCode("IND");
        Assert.assertEquals(result1, result2);
    }

    @Test(expected = PhoneCodesException.class)
    public void invalidCountryCodeTest() {
        PhoneCodes phoneCodes = PhoneCodes.getInstance();
        phoneCodes.getPhoneCodeByCountryCode("DUMMY");
    }

    @Test(expected = PhoneCodesException.class)
    public void invalidPhoneCodeTest() {
        PhoneCodes phoneCodes = PhoneCodes.getInstance();
        phoneCodes.getCountryNameByPhoneCode("-1");
    }
}
