package com.selling.system.shared.module.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.nio.charset.Charset;

import static com.selling.system.shared.module.constants.ResourceBundlesConstants.CODE;
import static com.selling.system.shared.module.constants.ResourceBundlesConstants.MESSAGE;
import static com.selling.system.shared.module.utils.StringUtil.isNotEmpty;

public class ResourceBundlesUtil {


    public static <T extends Exception> String getExceptionMessage(MessageSource messageSource, T t, Charset charset) {
        try {
            return new String(messageSource.getMessage(t.getClass().getSimpleName() + MESSAGE, null, LocaleContextHolder.getLocale()).getBytes(), charset)
                    + (isNotEmpty(t.getMessage()) ? ": " + t.getMessage() : "");
        } catch (NoSuchMessageException ex) {
            return t.getMessage();
        }
    }

    public static <T extends Exception> String getExceptionMessage(MessageSource messageSource, T t, Charset charset, String message) {
        try {
            return new String(messageSource.getMessage(t.getClass().getSimpleName() + MESSAGE, null, LocaleContextHolder.getLocale()).getBytes(), charset)
                    + (isNotEmpty(message) ? ": " + message : "");
        } catch (NoSuchMessageException ex) {
            return t.getMessage();
        }
    }

    public static <T extends Exception> String getExceptionCode(MessageSource messageSource, T t) {
        try {
            return messageSource.getMessage(t.getClass().getSimpleName() + CODE, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            return "";
        }
    }


}
