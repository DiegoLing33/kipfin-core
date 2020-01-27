package me.ling.kipfin.core.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Утилиты integer
 */
public class IntUtils {

    /**
     * Возвращает случайное число в диапазоне min ... max (включая концы)
     * @param max   - максимальное значение
     * @param min   - минимаьное значение
     * @return      - случайное значение
     */
    @NotNull
    public static Integer getRandomInteger(int max, int min){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Возвращает случайнео число в диапазоне 0 ... max (включая концы)
     * @param max   - максимальное значение
     * @return  - слуайное значений
     */
    @NotNull
    public static Integer getRandomInteger(int max){
        return IntUtils.getRandomInteger(max, 0);
    }
}
