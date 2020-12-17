package com.taku.util.function.Function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply()}.
 *
 * @param <R> the type of the result of the function
 *
 * @since 1.8
 */
@FunctionalInterface
public interface VoidFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    R apply();
}