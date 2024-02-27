package com.selling.system.shared.module.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class CompressionUtil {

    public static byte[] compress(byte[] data) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             GZIPOutputStream gzipOS = new GZIPOutputStream(bos)) {
            gzipOS.write(data);
            gzipOS.finish();
            return bos.toByteArray();
        } catch (IOException e) {
            return data;
        }
    }
}
