package com.selling.system.shared.models.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Range<T> {

    private T min;

    private T max;

}
