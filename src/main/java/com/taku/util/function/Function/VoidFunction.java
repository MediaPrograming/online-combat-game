package com.taku.util.function.Function;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply()}.
 *
 * @param <R> the type of the result of the function
 *
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