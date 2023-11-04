package com.sale.source.sales.source.ms.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {

    private List<String> genderValues;

    private SatisfactionRange satisfactionRange;

    private AgeRange ageRange;

    private List<String> emailValues;


    @Data
    public static class AgeRange extends Range {
    }

    public static class SatisfactionRange extends Range {
    }

}
