package com.selling.system.shared.module.utils;

import com.selling.system.shared.module.exceptions.Technical.SizeInvalidException;
import com.selling.system.shared.module.models.enums.SizeUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeUtil {

    public static int parseSize(String size) throws SizeInvalidException {
        try {
            String pattern = "(\\d+)([A-Za-z]+)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(size);
            if (matcher.matches()) {
                int sizeValue = Integer.parseInt(matcher.group(1));
                SizeUnit sizeUnit = SizeUnit.valueOf(matcher.group(2));
                return calculateSizeValue(sizeValue, sizeUnit);
            } else {
                throw new SizeInvalidException("invalid size structure: (\\d+)([A-Za-z]+) ");
            }
        } catch (Exception ex) {
            throw new SizeInvalidException("invalid size structure: (\\d+)([A-Za-z]+) ");
        }
    }

    private static int calculateSizeValue(int sizeValue, SizeUnit sizeUnit) {
        return switch (sizeUnit) {
            case B -> sizeValue;
            case KB -> sizeValue * 1024;
            case MB -> sizeValue * 1024 * 1024;
            case GB -> sizeValue * 1024 * 1024 * 1024;
        };
    }
}
