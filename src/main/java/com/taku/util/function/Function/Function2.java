package com.taku.util.function.Function;

import java.util.Objects;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <T2> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Function2<T1, T2, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @param t2 the function argument
     * @return the function result
     */
    R apply(T1 t1, T2 t2);
}
