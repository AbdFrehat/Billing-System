package com.orderizer.export.data.json.model.client.request;

import com.orderizer.export.data.json.model.field.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersSearchRequest {

    List<ExactField> exactFields;

    List<MatchField> matchFields;

    List<RangeField> rangeFields;

    List<RangeDateField> rangeDateFields;

    List<ListExactField> listExactFields;

    List<ListMatchField> listMatchFields;

    List<ListRangeField> listRangeFields;

    List<ListRangeDateField> listRangeDateFields;

}
