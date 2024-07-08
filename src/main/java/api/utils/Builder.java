package api.utils;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Builder<T> {
    private final Class<T> clazz;
    private final Predicate<T> condition;
    private T instance;

    private Builder(Class<T> clazz, Predicate<T> condition) {
        this.clazz = clazz;
        this.condition = condition;
        this.instance = createInstance();
    }

    private T createInstance() {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
        }
    }

    public Builder<T> with(Consumer<T> setter) {
        if (condition.test(instance)) {
            setter.accept(instance);
        }
        return this;
    }

    public T get() {
        return instance;
    }

    public static <T> Builder<T> build(Class<T> clazz) {
        return new Builder<>(clazz, t -> true);
    }

    public Builder<T> If(Predicate<T> condition) {
        return new Builder<>(clazz, condition);
    }
}