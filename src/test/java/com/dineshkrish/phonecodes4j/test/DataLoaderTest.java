package com.dineshkrish.phonecodes4j.test;

import com.dineshkrish.phonecodes4j.util.DataLoader;
import org.junit.Assert;
import org.junit.Test;

public class DataLoaderTest {

    @Test
    public void dataLoaderTest() {
        Assert.assertNotNull(DataLoader.getCountryList());
    }
}
