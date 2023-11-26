package com.selling.system.data.shared.module.entites;

import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.exceptions.QueryMethodNotFoundException;

import static com.selling.system.data.shared.module.convertors.QueryObjectToObjectsConvertor.toSale;
import static com.selling.system.data.shared.module.convertors.QueryObjectToObjectsConvertor.toSales;


/**
 * This Data class represents t
 */
public class QueryCommandDTO extends QueryCommand {


    @Override
    public Object getPayload() {
        switch (this.queryMethod) {
            case SAVE_SALE, UPDATE_SALE, DELETE_SALE -> {
                return toSale(this.payload);
            }
            case SAVE_SALES, UPDATE_SALES, DELETE_SALES -> {
                return toSales(this.payload);
            }
            case DELETE_QUERY_SALES -> {
                return this.payload;
            }
        }
        throw new QueryMethodNotFoundException("QueryMethod: " + queryMethod + " is not found");
    }
}

