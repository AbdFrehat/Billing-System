package com.selling.system.query.shared.module.convertors;

import com.selling.system.query.shared.module.command.QueryField;
import com.selling.system.shared.models.commons.Range;
import com.selling.system.shared.models.enums.FieldsType;
import com.selling.system.shared.models.exceptions.BadConvertorException;

import java.util.LinkedHashMap;

public class ObjectToObjectsConvertor {

    public static Range<Object> toRange(Object object) {
        if (object instanceof LinkedHashMap<?, ?> list) {
            return new Range<>(list.get("min"), list.get("max"));
        }
        throw new BadConvertorException("Unable to convert the provided object to range");
    }

    public static QueryField toQueryFiled(Object object) {
        if (object instanceof LinkedHashMap<?, ?> fieldsMap) {
            return QueryField.builder()
                    .field((String) fieldsMap.get("field"))
                    .value(fieldsMap.get("value"))
                    .fieldsType(FieldsType.valueOf((String) fieldsMap.get("fieldType")))
                    .build();
        }
        throw new BadConvertorException("Unable to convert the provided object to queryField");
    }
}
