package com.taku.util.function.Consumer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import java.util.Objects;

@FunctionalInterface
public interface Consumer0 {
    public void accept();

    default Consumer0 andThen(Consumer0 after) {
        Objects.requireNonNull(after);
        return () -> { accept(); after.accept(); };
    }
}