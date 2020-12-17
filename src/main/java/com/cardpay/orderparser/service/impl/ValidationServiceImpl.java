package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.exception.ValidationException;
import com.cardpay.orderparser.service.OrderParserContainer;
import com.cardpay.orderparser.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.io.Files.getFileExtension;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private OrderParserContainer parserContainer;

    @Override
    public void validate(List<String> args) throws ValidationException {
        List<String> errorDetails = new LinkedList<>();

        if (args.size() == 0) {
            errorDetails.add("Please provide at least 1 input file!");
        }

        for (String arg : args) {
            File file = new File(arg);

            if (!file.exists()) {
                errorDetails.add("Failed to read file " + arg);
            }

            String fileExtension = getFileExtension(arg);

            if (!parserContainer.isExtentionSupported(fileExtension)) {
                errorDetails.add("Extension " + fileExtension + " not supported");
            }
        }

        if (!errorDetails.isEmpty()) {
            throw new ValidationException("Arguments " + args + " are invalid", errorDetails);
        }
    }

}
