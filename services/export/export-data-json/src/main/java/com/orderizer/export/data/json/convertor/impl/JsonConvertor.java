package com.orderizer.export.data.json.convertor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderizer.export.data.json.convertor.api.DataConvertor;
import com.orderizer.export.data.json.model.response.ExportOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Component
@RequiredArgsConstructor
public class JsonConvertor implements DataConvertor {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<byte[]> convert(ExportOrdersResponse exportOrdersResponse) {
        return Mono.fromCallable(() -> objectMapper.writeValueAsString(exportOrdersResponse))
                .map(String::getBytes)
                .flatMap(bytes ->
                        Mono.fromCallable(() -> {
                            try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(bos)) {
                                zos.putNextEntry(new ZipEntry("results.json"));
                                zos.write(bytes);
                                zos.closeEntry();
                                zos.finish();
                                return bos.toByteArray();
                            }
                        }).subscribeOn(Schedulers.boundedElastic())
                )
                .map(Base64.getEncoder()::encode)
                .onErrorResume(JsonProcessingException.class, e -> Mono.error(new RuntimeException(e)));
    }


}
