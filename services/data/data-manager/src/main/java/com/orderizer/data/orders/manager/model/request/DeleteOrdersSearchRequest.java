package com.orderizer.data.orders.manager.model.request;

import com.orderizer.data.orders.manager.model.field.*;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOrdersSearchRequest {

    List<ExactField> exactFields;

    List<MatchField> matchFields;

    List<RangeField> rangeFields;

    List<RangeDateField> rangeDateFields;

    List<ListExactField> listExactFields;

    List<ListMatchField> listMatchFields;

    List<ListRangeField> listRangeFields;

    List<ListRangeDateField> listRangeDateFields;

}
