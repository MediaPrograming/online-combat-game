package com.taku.util.function.Consumer;

import java.util.Objects;

@FunctionalInterface
public interface Consumer2<T1, T2> {
    public void accept(T1 t, T2 t2);

    default Consumer2<T1, T2> andThen(Consumer2<? super T1, ? super T2> after) {
        Objects.requireNonNull(after);
        return (t1, t2) -> { accept(t1, t2); after.accept(t1, t2); };
    }
}
