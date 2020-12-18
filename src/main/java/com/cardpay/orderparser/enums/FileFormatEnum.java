package com.cardpay.orderparser.enums;

import java.util.HashMap;
import java.util.Map;

public enum FileFormatEnum {
    CSV("csv"),
    JSON("json");

    private final String format;

    FileFormatEnum(String format) {
        this.format = format;
    }

    private static final Map<String, FileFormatEnum> LOOKUP = new HashMap<>();

    static {
        for (FileFormatEnum ffe : FileFormatEnum.values()) {
            LOOKUP.put(ffe.getFormat(), ffe);
        }
    }

    public static FileFormatEnum fromString(String text) {
        return LOOKUP.get(text);
    }

    public String getFormat() {
        return format;
    }

}
