package com.github.remcalmels;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:type.properties")
@Data
public class TypeProperties {

    @Value("${boolean}")
    private Boolean booleanValue;

    @Value("${integer}")
    private Integer integerValue;

    @Value("${long}")
    private Long longValue;

    public Object getValue(Class type) {
        Object value = null;
        if (Boolean.class.isAssignableFrom(type)) {
            value = booleanValue;
        }
        if (Integer.class.isAssignableFrom(type)) {
            value = integerValue;
        }
        if (Long.class.isAssignableFrom(type)) {
            value = longValue;
        }
        return value;
    }

}
