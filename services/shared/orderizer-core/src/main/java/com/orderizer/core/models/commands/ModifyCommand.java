package com.orderizer.core.models.commands;

import com.orderizer.core.models.enums.CommandType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyCommand implements ModifyCommandAbstract {

    private CommandType commandType;

    private Object data;

}
