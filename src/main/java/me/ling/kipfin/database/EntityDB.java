package me.ling.kipfin.database;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Сущность базы
 *
 * @param <TSource>
 */
public abstract class EntityDB<TSource> {

    /**
     * Кэшированные данные
     */
    @NotNull
    private Map<Integer, TSource> cache = new HashMap<>();

    /**
     * Возвращает кэшированные данные запроса
     *
     * @return - кэшированные данные
     */
    @NotNull
    public Map<Integer, TSource> getCache() {
        return cache;
    }

    /**
     * Выполняет запрос на получение всех данных из БД
     *
     * @return - данные из БД в TSource
     * @throws SQLException - исключение при запросе
     */
    public abstract Map<Integer, TSource> getAll() throws SQLException;

    /**
     * Выполняет обновление кэша
     *
     * @return - данные
     * @throws SQLException - исключение при запросе
     */
    public Map<Integer, TSource> update() throws SQLException {
        this.cache = this.getAll();
        return this.cache;
    }

    /**
     * Возвращает элемент по ID
     *
     * @param id - идентификатор
     * @return - возвращает элемент
     */
    @Nullable
    public TSource getById(Integer id) {
        return this.cache.getOrDefault(id, null);
    }

    /**
     * Возвращает true, если элемент найден
     *
     * @param item - элемент
     * @return - результат проверки
     */
    public boolean contains(TSource item) {
        return this.cache.containsValue(item);
    }

    /**
     * Возвращает true, если элемент найден по предикату
     *
     * @param filter - функция фильтр
     * @return - результат проверки
     */
    public boolean contains(Function<TSource, Boolean> filter) {
        return this.cache.values().stream().anyMatch(filter::apply);
    }

}
