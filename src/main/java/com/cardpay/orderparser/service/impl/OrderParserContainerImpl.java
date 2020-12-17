package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.exception.ParseException;
import com.cardpay.orderparser.service.OrderParserContainer;
import com.cardpay.orderparser.service.FileParsingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.google.common.io.Files.getFileExtension;

@Service
@AllArgsConstructor
@Slf4j
public class OrderParserContainerImpl implements OrderParserContainer {

    private final Map<String, FileParsingService> parsingServices;

    @Override
    public boolean isExtentionSupported(String extension) {
        log.info("Extensions supported: {}, checking {}..."
                , parsingServices.keySet()
                , extension);
        return parsingServices
                .keySet()
                .stream()
                .anyMatch(serviceExtension -> serviceExtension.equals(extension));
    }

    @Override
    public void parse(List<String> filePaths) {
        filePaths
                .forEach(filePath -> {
                    try {
                        parsingServices.get(getFileExtension(filePath)).parse(filePath);
                    } catch (ParseException e) {
                        log.error("Failed to parse {}: {}", filePath, e.getLocalizedMessage());
                    }
                });
    }
}
