package com.selling.system.shared.module.models.commands;

import com.selling.system.shared.module.models.enums.CommandType;
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
