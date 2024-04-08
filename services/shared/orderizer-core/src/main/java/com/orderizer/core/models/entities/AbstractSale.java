package com.orderizer.core.models.entities;

import java.util.Date;
import java.util.List;


public interface AbstractSale {
    String getId();

    Date getSaleDate();

    List<? extends AbstractItem> getItems();

    String getStoreLocation();

    AbstractCustomer getCustomer();

    boolean isCouponUsed();

    String getPurchaseMethod();

}
