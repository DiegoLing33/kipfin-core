package me.ling.core.callbacks;

/**
 * Обработчик с исключением
 * @param <T>
 * @param <ExceptionType>
 */
@FunctionalInterface
public interface CallbackWithException<T, ExceptionType extends Exception> {
    void apply(T t) throws ExceptionType;
}
