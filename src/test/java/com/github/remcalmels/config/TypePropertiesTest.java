package com.github.remcalmels.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SheepFactoryConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class TypePropertiesTest {

    @Autowired
    private TypeProperties typeProperties;

    @Value("${sheep.types.boolean}")
    private Boolean booleanValue;

    @Value("${sheep.types.integer}")
    private Integer integerValue;

    @Value("${sheep.types.long}")
    private Long longValue;

    @Test
    public void getBooleanValue_shouldReturnConfiguredBooleanDefaultValue() {
        Assert.assertEquals(booleanValue, typeProperties.getBooleanValue());
    }

    @Test
    public void getIntegerValue_shouldReturnConfiguredIntegerDefaultValue() {
        Assert.assertEquals(integerValue, typeProperties.getIntegerValue());
    }

    @Test
    public void getLongValue_shouldReturnConfiguredLongDefaultValue() {
        Assert.assertEquals(longValue, typeProperties.getLongValue());
    }

    @Test
    public void getValue_shouldReturnBooleanDefaultValue_whenClassIsBoolean() {
        Assert.assertEquals(booleanValue, typeProperties.getValue(Boolean.class));
    }

    @Test
    public void getValue_shouldReturnIntegerDefaultValue_whenClassIsInteger() {
        Assert.assertEquals(integerValue, typeProperties.getValue(Integer.class));
    }

    @Test
    public void getValue_shouldReturnLongDefaultValue_whenClassIsLong() {
        Assert.assertEquals(longValue, typeProperties.getValue(Long.class));
    }

}
