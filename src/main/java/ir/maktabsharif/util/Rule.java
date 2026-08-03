package ir.maktabsharif.util;

import ir.maktabsharif.exception.BusinessException;

import java.util.function.Function;

public class Rule {
    private Rule() {
    }

    public static void check (
            boolean condition,
            Function<String, ? extends BusinessException> exceptionFunction,
            String message
    ) throws BusinessException {
        if (condition) throw exceptionFunction.apply(message);
    }
}
