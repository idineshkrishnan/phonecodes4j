package com.dineshkrish.phonecodes4j.util;

import com.dineshkrish.phonecodes4j.model.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public final class DataLoader {

    private static List<Country> countryList;

    static {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Country>>() {}.getType();
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(Constants.DATA_FILE_NAME);
        if(null != inputStream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            countryList = gson.fromJson(reader, listType);
        }
    }

    private DataLoader() {

    }

    public static List getCountryList() {
        return countryList;
    }
}
