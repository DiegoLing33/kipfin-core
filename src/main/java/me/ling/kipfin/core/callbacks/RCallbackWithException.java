package me.ling.kipfin.core.callbacks;

@FunctionalInterface
public interface RCallbackWithException<T, R, ExceptionType extends Exception> {
    R apply(T t) throws ExceptionType;
}
