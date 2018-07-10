package com.github.wsmt.tracker.config.properties;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HBasePropertiesTest {
    @Test
    public void hBasePropertiesTest() {
        HBaseProperties hBaseProperties = new HBaseProperties();

        HashMap<String, String> expectedHBaseProperties = new HashMap<>();
        hBaseProperties.setHBase(expectedHBaseProperties);
        Optional<Map<String, String>> actualHBaseProperties = hBaseProperties.getHBase();

        Assert.assertTrue(actualHBaseProperties.isPresent());
        Assert.assertEquals(expectedHBaseProperties, actualHBaseProperties.get());
    }
}