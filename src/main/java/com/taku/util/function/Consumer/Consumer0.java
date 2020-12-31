package com.taku.util.function.Consumer;

import java.util.Objects;

@FunctionalInterface
public interface Consumer0 {
    public void accept();

    default Consumer0 andThen(Consumer0 after) {
        Objects.requireNonNull(after);
        return () -> { accept(); after.accept(); };
    }
}