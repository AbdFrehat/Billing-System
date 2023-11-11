package com.selling.system.shared.models.entities;

import com.selling.system.shared.models.enums.PurchaseMethod;

import java.util.Date;
import java.util.List;

public interface AbstractSale {
    String getId();

    Date getSaleDate();

    List<? extends AbstractItem> getItems();

    String getStoreLocation();

    AbstractCustomer getCustomer();

    boolean isCouponUsed();

    PurchaseMethod getPurchaseMethod();


}
