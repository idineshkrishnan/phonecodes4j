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
 * @author Dinesh Krishnan
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
     * A method will return a possible country names for given phone code.
     * @param phoneCode
     * @return A possible country names for give phone code
     */
    public String getCountryNameByPhoneCode(final Integer phoneCode) {
        if(phoneCode > 0 && phoneCode < 1000) {
            List<Country> countryList = countryMapPhoneCode.get(phoneCode);
            if(countryList != null && countryList.size() > 0) {
                String countries = countryList
                        .stream()
                        .map(country -> country.getCountryName() + Constants.SEPARATOR)
                        .collect(Collectors.joining());
                return countries.substring(0, countries.length() - 1);
            } else {
                throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
            }
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     * A method will return a possible country names for given phone code.
     * @param phoneCode
     * @return A possible country names for give phone code
     */
    public String getCountryNameByPhoneCode(final String phoneCode) {
        if(phoneCode != null && phoneCode.matches(Constants.INTEGER_REGEX)) {
            return getCountryNameByPhoneCode(Integer.valueOf(phoneCode));
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     * A method will return a possible country names for given phone codes.
     * @param phoneCodes
     * @return A possible country names for give phone codes
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
     * A method will return a possible country names for given phone codes.
     * @param phoneCodes
     * @return A possible country names for give phone codes
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
     * A method will return a possible country codes for given phone code.
     * @param phoneCode
     * @param type
     * @return A possible country codes for give phone code
     * @see Type
     */
    public String getCountryCodeByPhoneCode(final Integer phoneCode, final Type type) {
        if(phoneCode > 0 && phoneCode < 1000) {
            List<Country> countryList = countryMapPhoneCode.get(phoneCode);
            if(countryList != null && countryList.size() > 0) {
                String countries = countryList
                        .stream()
                        .map(country ->
                                (type == Type.ALPHA_CODE_2) ?
                                        country.getCountryAlphaCode2() + Constants.SEPARATOR :
                                        country.getCountryAlphaCode3() + Constants.SEPARATOR)
                        .collect(Collectors.joining());
                return countries.substring(0, countries.length() - 1);
            } else {
                throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
            }
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_PHONE_CODE);
        }
    }

    /**
     * A method will return a possible country codes for given phone code and type.
     * @param phoneCode
     * @param type
     * @return A possible country codes for give phone code and type
     * @see Type
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
     * This method will return phone code for give country code.
     * @param countryCode
     * @return A phone code will be returned for given country code.
     */
    public Integer getPhoneCodeByCountryCode(final String countryCode) {
        Country country;
        if(countryCode != null && countryCode.length() == 2) {
            country = countryMapAlphaCode2.get(countryCode);
            if(country != null)
                return country.getPhoneCode();
            else
                throw new PhoneCodesException(Constants.ERROR_INVALID_COUNTRY_CODE);
        } else if (countryCode != null && countryCode.length() == 3) {
            country = countryMapAlphaCode3.get(countryCode);
            if(country != null)
                return country.getPhoneCode();
            else
                throw new PhoneCodesException(Constants.ERROR_INVALID_COUNTRY_CODE);
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_COUNTRY_CODE);
        }
    }

    /**
     * This method will return phone codes for give country codes.
     * @param countryCodes
     * @return A phone codes will be returned for given country codes.
     */
    public List<Integer> getPhoneCodesByCountryCodes(final String...countryCodes) {
        List<Integer> phoneCodes = new ArrayList<Integer>();
        if(countryCodes != null && countryCodes.length > 0) {
            for(String countryCode : countryCodes)
                phoneCodes.add(getPhoneCodeByCountryCode(countryCode));
        } else {
            throw new PhoneCodesException(Constants.ERROR_INVALID_COUNTRY_CODE);
        }
        return phoneCodes;
    }
}
