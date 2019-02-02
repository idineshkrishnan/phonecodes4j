package com.dineshkrish.phonecodes4j;

import com.dineshkrish.phonecodes4j.error.PhoneCodesException;
import com.dineshkrish.phonecodes4j.model.Country;
import com.dineshkrish.phonecodes4j.model.Type;
import com.dineshkrish.phonecodes4j.util.Constants;
import com.dineshkrish.phonecodes4j.util.DataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class PhoneCodes {

    private static PhoneCodes instance;
    private static final Map<String, Country> countryMapAlphaCode2 = new HashMap<String, Country>();
    private static final Map<String, Country> countryMapAlphaCode3  = new HashMap<String, Country>();
    private static final Map<Integer, List<Country>> countryMapPhoneCode = new HashMap<Integer, List<Country>>();

    private PhoneCodes() {
        final List<Country> countryList = DataLoader.getCountryList();
        if(countryList != null) {
            for(Country country : countryList) {
                countryMapAlphaCode2.put(country.getCountryAlphaCode2(), country);
                countryMapAlphaCode3.put(country.getCountryAlphaCode3(), country);
                List<Country> countries;
                if(countryMapPhoneCode.containsKey(country.getPhoneCode())) {
                    countries = countryMapPhoneCode.get(country.getPhoneCode());
                    countries.add(country);
                } else {
                    countries = new ArrayList<Country>();
                    countries.add(country);
                    countryMapPhoneCode.put(country.getPhoneCode(), countries);
                }
            }
        }
    }

    public static PhoneCodes getInstance() {
        if(instance == null) {
            instance = new PhoneCodes();
        }
        return instance;
    }

    /**
     *
     * @param phoneCode
     * @return
     */
    public String getCountryNameByPhoneCode(final Integer phoneCode) {
        if(phoneCode > 0 && phoneCode < 1000) {
            String countries = countryMapPhoneCode
                    .get(phoneCode)
                    .stream()
                    .map(country -> country.getCountryName() + Constants.SEPARATOR)
                    .collect(Collectors.joining());
            return countries.substring(0, countries.length() -1);
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     *
     * @param phoneCode
     * @return
     */
    public String getCountryNameByPhoneCode(final String phoneCode) {
        if(phoneCode != null && phoneCode.matches(Constants.INTEGER_REGEX)) {
            return getCountryNameByPhoneCode(Integer.valueOf(phoneCode));
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     *
     * @param phoneCodes
     * @return
     */
    public Map<Integer, String> getCountryNamesByPhoneCode(final Integer...phoneCodes) {
        Map<Integer, String> names = new HashMap<Integer, String>();
        if(phoneCodes != null && phoneCodes.length > 0) {
            for(Integer phoneCode : phoneCodes) {
                names.put(phoneCode ,getCountryNameByPhoneCode(phoneCode));
            }
        }
        return names;
    }

    /**
     *
     * @param phoneCodes
     * @return
     */
    public Map<Integer, String> getCountryNamesByPhoneCode(final String...phoneCodes) {
        Map<Integer, String> names = new HashMap<Integer, String>();
        if(phoneCodes != null && phoneCodes.length > 0) {
            for(String phoneCode : phoneCodes) {
                Integer phoneCodeInt = Integer.valueOf(phoneCode);
                names.put(phoneCodeInt, getCountryNameByPhoneCode(phoneCodeInt));
            }
        }
        return names;
    }

    /**
     *
     * @param phoneCode
     * @param type
     * @return
     */
    public String getCountryCodeByPhoneCode(final Integer phoneCode, final Type type) {
        if(phoneCode > 0 && phoneCode < 1000) {
            String countries = countryMapPhoneCode
                    .get(phoneCode)
                    .stream()
                    .map(country ->
                            (type == Type.ALPHA_CODE_2) ?
                                    country.getCountryAlphaCode2() + Constants.SEPARATOR:
                                    country.getCountryAlphaCode3() + Constants.SEPARATOR)
                    .collect(Collectors.joining());
            return countries.substring(0, countries.length() -1);
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     *
     * @param phoneCode
     * @param type
     * @return
     */
    public String getCountryCodeByPhoneCode(final String phoneCode, final Type type)
    {
        if(phoneCode != null && phoneCode.matches(Constants.INTEGER_REGEX)) {
            return getCountryCodeByPhoneCode(Integer.valueOf(phoneCode), type);
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     *
     * @param countryCode
     * @return
     */
    public Integer getPhoneCodeByCountryCode(final String countryCode) {
        if(countryCode != null && countryCode.length() == 2) {
            return countryMapAlphaCode2.get(countryCode).getPhoneCode();
        } else if (countryCode != null && countryCode.length() == 3) {
            return countryMapAlphaCode3.get(countryCode).getPhoneCode();
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_COUNTRY_CODE);
        }
    }
}
