package com.github.remcalmels;

import lombok.Data;

@Data
public class TestObjectWithFinal {
    private String aString;
    private Boolean aBoolean;
    private Integer anInteger;
    private Long aLong;
    private Unknown anUnknown;
    private final String aFinalString = "";
}
