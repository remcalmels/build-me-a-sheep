package com.github.remcalmels;

import com.github.remcalmels.config.SheepFactoryConfig;
import com.github.remcalmels.config.TypeProperties;
import com.github.remcalmels.exception.SheepException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SheepFactoryConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
public class SheepFactoryTest {

    @Autowired
    private SheepFactory sheepFactory;

    @Autowired
    private TypeProperties typeProperties;

    @Test
    public void build_shouldReturnAnEmptyOptional_whenClassIsNull() throws SheepException {
        // When
        Optional optional = sheepFactory.build(null);
        // Then
        Assert.assertFalse(optional.isPresent());
    }

    @Test
    public void build_shouldReturnNotEmptyOptional_whenClassIsNotNull() throws SheepException {
        // When
        Optional optional = sheepFactory.build(TestObject.class);
        // Then
        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void build_shouldReturnOptionalWithFilledAttributes_forString() throws SheepException {
        // When
        Optional optional = sheepFactory.build(TestObject.class);
        // Then
        Assert.assertTrue(optional.isPresent());
        TestObject testObject = (TestObject) optional.get();
        Assert.assertEquals("aString", testObject.getAString());
    }

    @Test
    public void build_shouldReturnOptionalWithFilledAttributes_forBoolean() throws SheepException {
        // When
        Optional optional = sheepFactory.build(TestObject.class);
        // Then
        Assert.assertTrue(optional.isPresent());
        TestObject testObject = (TestObject) optional.get();
        Assert.assertEquals(typeProperties.getBooleanValue(), testObject.getABoolean());
    }

    @Test
    public void build_shouldReturnOptionalWithFilledAttributes_forInteger() throws SheepException {
        // When
        Optional optional = sheepFactory.build(TestObject.class);
        // Then
        Assert.assertTrue(optional.isPresent());
        TestObject testObject = (TestObject) optional.get();
        Assert.assertEquals(typeProperties.getIntegerValue(), testObject.getAnInteger());
    }

    @Test
    public void build_shouldReturnOptionalWithFilledAttributes_forLong() throws SheepException {
        // When
        Optional optional = sheepFactory.build(TestObject.class);
        // Then
        Assert.assertTrue(optional.isPresent());
        TestObject testObject = (TestObject) optional.get();
        Assert.assertEquals(typeProperties.getLongValue(), testObject.getALong());
    }

    @Test
    public void build_shouldReturnOptionalWithFilledAttributes_butNullValueForUnknownType() throws SheepException {
        // When
        Optional optional = sheepFactory.build(TestObject.class);
        // Then
        Assert.assertTrue(optional.isPresent());
        TestObject testObject = (TestObject) optional.get();
        Assert.assertNull(testObject.getAnUnknown());
    }

    @Test
    public void build_shouldNotThrowException_withFinal() {
        try {
            // When
            Optional optional = sheepFactory.build(TestObjectWithFinal.class);
            // Then
            Assert.assertTrue(optional.isPresent());
        } catch (SheepException e) {
            Assert.fail("Must not throw this exception");
        }
    }

}