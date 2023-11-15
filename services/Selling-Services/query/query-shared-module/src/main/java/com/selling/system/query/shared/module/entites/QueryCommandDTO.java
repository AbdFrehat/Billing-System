package com.selling.system.query.shared.module.entites;

import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.exceptions.QueryMethodNotFoundException;

import static com.selling.system.query.shared.module.convertors.QueryObjectToObjectsConvertor.toSale;
import static com.selling.system.query.shared.module.convertors.QueryObjectToObjectsConvertor.toSales;


public class QueryCommandDTO extends QueryCommand {


    @Override
    public Object getPayload() {
        switch (this.queryMethod) {
            case SAVE_SALE -> {
                return toSale(this.payload);
            }
            case SAVE_SALES -> {
                return toSales(this.payload);
            }
        }
        throw new QueryMethodNotFoundException("QueryMethod: " + queryMethod + " is not found");
    }
}

