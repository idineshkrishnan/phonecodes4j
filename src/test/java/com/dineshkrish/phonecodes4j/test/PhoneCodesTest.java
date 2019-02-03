package com.dineshkrish.phonecodes4j.test;

import com.dineshkrish.phonecodes4j.PhoneCodes;
import com.dineshkrish.phonecodes4j.error.PhoneCodesException;
import com.dineshkrish.phonecodes4j.model.Type;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class PhoneCodesTest {

    PhoneCodes phoneCodes = PhoneCodes.getInstance();

    @Test
    public void getCountryNameByPhoneCodeTest() {
        String result1 = phoneCodes.getCountryNameByPhoneCode(1);
        String result2 = phoneCodes.getCountryNameByPhoneCode("1");
        Assert.assertNotNull(result1);
        Assert.assertNotNull(result2);
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void getCountryNamesByPhoneCodeTest() {
        Map<Integer, String> result1 = phoneCodes.getCountryNamesByPhoneCode(1, 91);
        Map<Integer, String> result2 = phoneCodes.getCountryNamesByPhoneCode("1", "91");
        Assert.assertNotNull(result1);
        Assert.assertNotNull(result2);
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void getCountryCodeByPhoneCodeTest() {
        String result1 = phoneCodes.getCountryCodeByPhoneCode(1, Type.ALPHA_CODE_3);
        String result2 = phoneCodes.getCountryCodeByPhoneCode("1", Type.ALPHA_CODE_3);
        Assert.assertNotNull(result1);
        Assert.assertNotNull(result2);
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void getPhoneCodeByCountryCodeTest() {
        Integer result1 = phoneCodes.getPhoneCodeByCountryCode("IN");
        Integer result2 = phoneCodes.getPhoneCodeByCountryCode("IND");
        Assert.assertNotNull(result1);
        Assert.assertNotNull(result2);
        Assert.assertEquals(result1, result2);
    }

    @Test(expected = PhoneCodesException.class)
    public void invalidCountryCodeTest() {
        phoneCodes.getPhoneCodeByCountryCode("DUMMY");
    }

    @Test(expected = PhoneCodesException.class)
    public void wrongCountryCodeTest() {
        phoneCodes.getPhoneCodeByCountryCode("KD");
    }

    @Test(expected = PhoneCodesException.class)
    public void invalidPhoneCodeTest() {
        phoneCodes.getCountryNameByPhoneCode("-1");
    }

    @Test(expected = PhoneCodesException.class)
    public void wrongPhoneCodeTest() {
        phoneCodes.getCountryNameByPhoneCode("2");
    }

    @Test
    public void getPhoneCodesByCountryCodesTest() {
        List<Integer> actual = phoneCodes.getPhoneCodesByCountryCodes("IN", "US", "DE", "CA");
        List<Integer> expected = Arrays.asList(91, 1, 49, 1);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }
}
