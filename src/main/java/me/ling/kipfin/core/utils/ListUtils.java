package me.ling.kipfin.core.utils;

import me.ling.kipfin.exceptions.NotFoundEntityException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

    /**
     * Проверяет наличие по строгому соотвествию
     *
     * @param list  - список
     * @param value - значение для теста
     * @param <T>   - тип значения для теста
     * @return - результат тестирования
     */
    public static <T> boolean contains(@NotNull List<T> list, T value) {
        return list.contains(value);
    }

    /**
     * Проверяет наличие по НЕстрогому соотвествию
     * <p>
     * Данное тестирование работает по принципу:
     * ```
     * var x = StringUtils.removeAllSpaces(a.toLoverCase());
     * var y = StringUtils.removeAllSpaces(b.toLoverCase());
     * <p>
     * var result = a.contains(b);
     * ```
     *
     * @param list  - лист
     * @param value - значение
     * @return - результат тестирования
     */
    public static boolean containsLike(@NotNull List<String> list, String value) {
        return ListUtils.contains(list, (Predicate<String>) s -> StringUtils.removeAllSpaces(s.toLowerCase())
                .contains(StringUtils.removeAllSpaces(value.toLowerCase())));
    }

    /**
     * Выполняет поиск по листу
     *
     * @param list      - лист
     * @param predicate - предикат
     * @param <T>       - тип листа
     * @return - результат
     */
    public static <T> boolean contains(@NotNull List<T> list, Predicate<T> predicate) {
        return list.stream().anyMatch(predicate);
    }

    /**
     * Выполняет поиск по листу с помощью полей объекта
     * <p>
     * Пример использования:
     * ```
     * //...
     * class Teacher{
     * public int id;
     * }
     * //...
     * <p>
     * List<Teacher>teachers = List.of(new Teacher(1), new Teacher(2));
     * <p>
     * ListUtils.contains(teachers, Teacher::id, 1) // true
     * ListUtils.contains(teachers, Teacher::id, 2) // true
     * ListUtils.contains(teachers, Teacher::id, 3) // false
     * ```
     *
     * @param list      - список
     * @param reference - ссылка
     * @param test      - тестируемое значение
     * @param <T>       - тип тестируемого объекта
     * @param <P>       - тип тестируемого значение
     * @return - результат тестирования
     */
    public static <T, P> boolean contains(@NotNull List<T> list, Function<T, P> reference, P test) {
        return ListUtils.contains(list, (Predicate<T>) s -> reference.apply(s).equals(test));
    }

    /**
     * Выполняет поиск по листу с помощью полей объекта. Поиск производится по "похожим элементам"
     * <p>
     * Данное тестирование работает по принципу:
     * ```
     * var x = StringUtils.removeAllSpaces(a.toLoverCase());
     * var y = StringUtils.removeAllSpaces(b.toLoverCase());
     * <p>
     * var result = a.contains(b);
     * ```
     *
     * @param list      - список
     * @param reference - ссылка на поле
     * @param like      - тестируемое значение
     * @param <T>       - тип объекта
     * @return - результат тестирования
     */
    public static <T> boolean containsLike(@NotNull List<T> list, Function<T, String> reference, String like) {
        return ListUtils.contains(list, (Predicate<T>) s -> StringUtils.removeAllSpaces(reference.apply(s))
                .toLowerCase().contains(StringUtils.removeAllSpaces(like.toLowerCase())));
    }

    /**
     * Возвращает <b>первое</b> значение из списка по предикату
     *
     * @param list      - лист
     * @param predicate - предикат
     * @param <T>       - тип листа
     * @return - возвращаемое значение
     * @throws NotFoundEntityException - объект не найден
     */
    public static <T> T get(@NotNull List<T> list, Predicate<T> predicate) throws NotFoundEntityException {
        for (T item : list) if (predicate.test(item)) return item;
        throw new NotFoundEntityException(" ПРЕДИКАТ ");
    }

    /**
     * Возвращает <b>первое</b> значение из списка по критерию
     *
     * @param list      - список
     * @param reference - поле
     * @param value     - значение
     * @param <T>       - тип списка
     * @param <P>       - тип значения
     * @return - элемент
     * @throws NotFoundEntityException - элемент не найден в списке
     */
    public static <T, P> T get(@NotNull List<T> list, Function<T, P> reference, P value) throws NotFoundEntityException {
        for (T item : list)
            if (reference.apply(item).equals(value)) return item;
        throw new NotFoundEntityException(value);
    }

    /**
     * Возвращает <b>первое</b> значение из списка по НЕ строгому критерию
     * <p>
     * Данное тестирование работает по принципу:
     * ```
     * var x = StringUtils.removeAllSpaces(a.toLoverCase());
     * var y = StringUtils.removeAllSpaces(b.toLoverCase());
     * <p>
     * var result = a.contains(b);
     * ```
     *
     * @param list      - список
     * @param reference - поле
     * @param value     - значение
     * @param <T>       - тип списка
     * @return - элемент
     * @throws NotFoundEntityException - элемент не найден в списке
     */
    public static <T> T getLike(@NotNull List<T> list, Function<T, String> reference, String value) throws NotFoundEntityException {
        for (T item : list)
            if (StringUtils.removeAllSpaces(reference.apply(item).toLowerCase()).contains(StringUtils.removeAllSpaces(value.toLowerCase())))
                return item;
        throw new NotFoundEntityException(value);
    }

    /**
     * Создает новый список из уникальных элементов списка list
     *
     * @param list - исходной список
     * @param <T>  - тип списка
     * @return - список уникальных элементов
     */
    @NotNull
    public static <T> List<T> createUnique(@NotNull List<T> list) {
        List<T> arrayList = new ArrayList<>();
        list.forEach(item -> {
            if (!arrayList.contains(item)) arrayList.add(item);
        });
        return arrayList;
    }
}
