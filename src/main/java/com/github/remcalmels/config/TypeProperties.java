package com.github.remcalmels.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
public class TypeProperties {

    @Value("${sheep.types.boolean:false}")
    private Boolean booleanValue;

    @Value("${sheep.types.integer:1}")
    private Integer integerValue;

    @Value("${sheep.types.long:123}")
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
