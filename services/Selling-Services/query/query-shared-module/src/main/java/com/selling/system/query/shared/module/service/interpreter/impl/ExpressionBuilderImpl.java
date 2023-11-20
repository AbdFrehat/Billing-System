package com.selling.system.query.shared.module.service.interpreter.impl;

import com.selling.system.query.shared.module.service.interpreter.CriteriaExpression;
import com.selling.system.query.shared.module.service.interpreter.ExpressionBuilder;
import com.selling.system.query.shared.module.service.interpreter.impl.operatprs.AndExpression;
import com.selling.system.query.shared.module.service.interpreter.impl.operatprs.NotExpression;
import com.selling.system.query.shared.module.service.interpreter.impl.operatprs.OrExpression;
import org.springframework.stereotype.Service;

import java.util.Stack;
import java.util.StringTokenizer;

@Service
public class ExpressionBuilderImpl implements ExpressionBuilder {

    @Override
    public CriteriaExpression build(String expression) {
        return this.parse(expression);
    }

    private CriteriaExpression parse(String expression) {
        StringTokenizer tokenizer = new StringTokenizer(expression);
        Stack<String> parenthesisStack = new Stack<>();
        Stack<CriteriaExpression> criteriaExpressionStack = new Stack<>();
        Stack<String> operatorsStack = new Stack<>();
        while (tokenizer.hasMoreTokens()) {
            String token;
            switch (token = tokenizer.nextToken()) {
                case "AND", "OR", "NOT" -> operatorsStack.push(token);
                case "(" -> parenthesisStack.push(token);
                case ")" -> {
                    criteriaExpressionStack.push(buildOperatorExpression(criteriaExpressionStack, operatorsStack));
                    parenthesisStack.pop();
                }
                default -> {
                    FieldExpression fieldExpression = new FieldExpression(token);
                    criteriaExpressionStack.push(fieldExpression);
                }
            }
        }
        return criteriaExpressionStack.pop();
    }

    private CriteriaExpression buildOperatorExpression(Stack<CriteriaExpression> criteriaExpressionStack,
                                                       Stack<String> operatorsStack ) {
        return switch (operatorsStack.pop()) {
            case "AND" -> new AndExpression(criteriaExpressionStack.pop(), criteriaExpressionStack.pop());
            case "OR" -> new OrExpression(criteriaExpressionStack.pop(), criteriaExpressionStack.pop());
            case "NOT" -> new NotExpression(criteriaExpressionStack.pop());
            default -> throw new IllegalStateException("Unexpected value: " + operatorsStack.pop());
        };
    }
}
