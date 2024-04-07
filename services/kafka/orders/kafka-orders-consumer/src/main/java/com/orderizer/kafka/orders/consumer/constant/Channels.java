package com.orderizer.kafka.orders.consumer.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.plaf.PanelUI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Channels {

    public static final String DATA_DELETE_ORDERS = "data-delete-orders";
    public static final String DATA_SAVE_ORDERS = "data-save-orders";
    public static final String DATA_UPDATE_ORDERS = "data-update-orders";
}
