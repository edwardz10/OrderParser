package com.cardpay.orderparser.service;

import java.util.List;

public interface OrderParserContainer {
    boolean isExtentionSupported(String extension);
    void parse(List<String> filePaths);
}
