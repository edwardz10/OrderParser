package com.cardpay.orderparser.service;

import java.util.Set;

public interface OrderParserContainer {
    boolean isExtensionSupported(String extension);
    void parse(Set<String> filePaths);
}
