package com.selling.system.data.shared.module.service.interpreter;

import com.selling.system.shared.module.models.commands.QueryField;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Map;

public interface CriteriaExpression {

    Criteria interpret(Map<String, QueryField> queryFields);

}
