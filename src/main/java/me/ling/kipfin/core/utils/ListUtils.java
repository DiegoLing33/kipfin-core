package me.ling.kipfin.core.utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Утилиты списков
 */
public class ListUtils {

    /**
     * Возвращает случайный элемент списка
     *
     * @param list - список
     * @param <T>  - тип элементов
     * @return - случайный элемент списка
     */
    @NotNull
    public static <T> T choice(@NotNull List<T> list) {
        return list.get(IntUtils.getRandomInteger(list.size() - 1));
    }

}
