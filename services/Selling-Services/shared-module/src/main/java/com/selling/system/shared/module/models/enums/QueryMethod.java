package com.selling.system.shared.module.models.enums;

import lombok.Getter;

@Getter
public enum QueryMethod {

    GET_SALES("//SALES-GET-MS/selling/query/get/sale/"),
    SAVE_SALE("//SALES-SAVE-MS/selling/query/save/sale/"),
    SAVE_SALES("//SALES-SAVE-MS/selling/query/save/sale/"),
    UPDATE_SALE("//SALES-UPDATE-MS/selling/query/update/sale/"),
    DELETE_SALE("//SALES-DELETE-MS/selling/query/delete/sale/"),
    DELETE_SALES("//SALES-DELETE-MS/selling/query/delete/sale/");

    QueryMethod(String value) {
        this.value = value;
    }

    private String value;
}
