package com.selling.system.export.shared.service;

import com.orderizer.core.models.commands.DataCommand;
import com.orderizer.core.models.commands.ExportDataFilter;
import com.orderizer.core.models.commands.QueryField;
import com.orderizer.core.models.commons.Range;
import com.orderizer.core.models.enums.CommandType;
import com.orderizer.core.models.enums.FieldType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.orderizer.core.utils.StringUtil.isNotEmpty;

@Service
public class DataCommandBuilderImpl implements DataCommandBuilder {
    @Override
    public DataCommand build(ExportDataFilter exportDataCommand) {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_SALES)
                .page(0)
                .size(100)
                .build();
        Map<String, QueryField> queryFieldMap = new HashMap<>();

        if (isNotEmpty(exportDataCommand.getFromDate()) && isNotEmpty(exportDataCommand.getToDate())) {
            queryFieldMap.put("date", QueryField.builder()
                    .field("saleDate")
                    .value(new Range<String>(exportDataCommand.getFromDate(), exportDataCommand.getToDate()))
                    .fieldType(FieldType.RANGE_DATE)
                    .build());
        }
        if (exportDataCommand.getSatisfaction() != 0) {
            queryFieldMap.put("customer.satisfaction", QueryField.builder()
                    .field("customer.satisfaction")
                    .value(exportDataCommand.getSatisfaction())
                    .fieldType(FieldType.OTHER)
                    .build());
        }
        if (isNotEmpty(exportDataCommand.getPurchaseMethod())) {
            queryFieldMap.put("purchaseMethod", QueryField.builder()
                    .field("purchaseMethod")
                    .value(exportDataCommand.getPurchaseMethod())
                    .fieldType(FieldType.STRING)
                    .build());
        }
        if (isNotEmpty(exportDataCommand.getStoreLocation())) {
            queryFieldMap.put("storeLocation", QueryField.builder()
                    .field("storeLocation")
                    .value(exportDataCommand.getStoreLocation())
                    .fieldType(FieldType.STRING)
                    .build());
        }
        if (exportDataCommand.getCouponUsed() != null) {
            queryFieldMap.put("couponUsed", QueryField.builder()
                    .field("couponUsed")
                    .value(exportDataCommand.getCouponUsed())
                    .fieldType(FieldType.OTHER)
                    .build());
        }
        dataCommand.setQueryFields(queryFieldMap);
        return dataCommand;
    }
}
