package com.orderizer.core.utils;

import com.orderizer.core.constants.ResourceBundlesConstants;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.nio.charset.Charset;
import java.util.Locale;

import static com.orderizer.core.utils.StringUtil.isNotEmpty;

public class ResourceBundlesUtil {


    public static <T extends Exception> String getExceptionMessage(MessageSource messageSource, T t, Charset charset, String lang) {
        try {
            return new String(messageSource.getMessage(t.getClass().getSimpleName() + ResourceBundlesConstants.MESSAGE, null, Locale.forLanguageTag(lang)).getBytes(), charset)
                    + (isNotEmpty(t.getMessage()) ? ": " + t.getMessage() : "");
        } catch (NoSuchMessageException ex) {
            return t.getMessage();
        }
    }

    public static <T extends Exception> String getExceptionMessage(MessageSource messageSource, T t, Charset charset, String message, String lang) {
        try {
            return new String(messageSource.getMessage(t.getClass().getSimpleName() + ResourceBundlesConstants.MESSAGE, null, Locale.forLanguageTag(lang)).getBytes(), charset)
                    + (isNotEmpty(message) ? ": " + message : "");
        } catch (NoSuchMessageException ex) {
            return t.getMessage();
        }
    }

    public static <T extends Exception> String getExceptionCode(MessageSource messageSource, T t, String lang) {
        try {
            return messageSource.getMessage(t.getClass().getSimpleName() + ResourceBundlesConstants.CODE, null, Locale.forLanguageTag(lang));
        } catch (NoSuchMessageException ex) {
            return "";
        }
    }


}
