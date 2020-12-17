package com.cardpay.orderparser.enums;

public enum FileFormatEnum {
    CSV("csv"),
    JSON("json");

    private String format;

    FileFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static FileFormatEnum fromString(String text) {
        for (FileFormatEnum f : FileFormatEnum.values()) {
            if (f.format.equalsIgnoreCase(text)) {
                return f;
            }
        }
        return null;
    }
}
