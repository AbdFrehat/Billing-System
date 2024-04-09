package com.orderizer.export.data.json.model.field;


import lombok.Data;

@Data
public class MatchField implements Field {

    private String field;

    private String value;

}
