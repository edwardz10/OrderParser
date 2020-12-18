package com.cardpay.orderparser.enums;

import java.util.HashMap;
import java.util.Map;

public enum CurrencyEnum {
    USD("USD"),
    EUR("EUR"),
    RUB("RUB"),
    AUD("AUD"),
    CHK("CHK"),
    CAD("CAD");

    private final String currency;

    CurrencyEnum(String currency) {
        this.currency = currency;
    }

    private static final Map<String, CurrencyEnum> LOOKUP = new HashMap<>();

    static {
        for (CurrencyEnum ce : CurrencyEnum.values()) {
            LOOKUP.put(ce.getCurrency(), ce);
        }
    }

    public static CurrencyEnum fromString(String text) {
        return LOOKUP.get(text);
    }

    public String getCurrency() {
        return currency;
    }
}
