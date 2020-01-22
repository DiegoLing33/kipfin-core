package me.ling.kipfin.core.sql;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Колонка SQL
 *
 * Анотация для SQLObjectMapper утилиты
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    /**
     * Имя столбца
     * @return - столбец
     */
    String name() default "";

    /**
     * Тип столбца.
     *
     * Поддерживается только `String.class` и `Integer.class`.
     *
     * @return - класс типа (Class<?>)
     */
    Class<?> type() default String.class;
}
