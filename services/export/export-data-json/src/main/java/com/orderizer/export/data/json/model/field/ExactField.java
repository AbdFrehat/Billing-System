package com.orderizer.export.data.json.model.field;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExactField implements Field {

    private String field;

    private Object value;

}
