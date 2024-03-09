package com.selling.system.data.sales.get.model.request;

import com.selling.system.data.sales.get.model.field.*;
import lombok.Data;

import java.util.List;

@Data
public class OrdersGetRequest {

    List<ExactField> exactFields;

    List<MatchField> matchFields;

    List<RangeField> rangeFields;

    List<RangeDateField> rangeDateFields;

    List<ListExactField> listExactFields;

    List<ListMatchField> listMatchFields;

    List<ListRangeField> listRangeFields;

    List<ListRangeDateField> listRangeDateFields;

}
