package com.orderizer.data.delete.search.orders.model.request;

import com.orderizer.data.delete.search.orders.model.field.*;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOrdersRequest {

    List<ExactField> exactFields;

    List<MatchField> matchFields;

    List<RangeField> rangeFields;

    List<RangeDateField> rangeDateFields;

    List<ListExactField> listExactFields;

    List<ListMatchField> listMatchFields;

    List<ListRangeField> listRangeFields;

    List<ListRangeDateField> listRangeDateFields;

}
