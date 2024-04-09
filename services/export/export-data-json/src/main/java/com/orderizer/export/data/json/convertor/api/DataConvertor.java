package com.orderizer.export.data.json.convertor.api;

import com.orderizer.export.data.json.model.response.ExportOrdersResponse;
import reactor.core.publisher.Mono;

public interface DataConvertor {

    Mono<byte[]> convert(ExportOrdersResponse exportOrdersResponse);
}
