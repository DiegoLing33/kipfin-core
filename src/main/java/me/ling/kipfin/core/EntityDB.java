/*
 *
 *   ██████╗░██╗███████╗░██████╗░░█████╗░  ██╗░░░░░██╗███╗░░██╗░██████╗░
 *   ██╔══██╗██║██╔════╝██╔════╝░██╔══██╗  ██║░░░░░██║████╗░██║██╔════╝░
 *   ██║░░██║██║█████╗░░██║░░██╗░██║░░██║  ██║░░░░░██║██╔██╗██║██║░░██╗░
 *   ██║░░██║██║██╔══╝░░██║░░╚██╗██║░░██║  ██║░░░░░██║██║╚████║██║░░╚██╗
 *   ██████╔╝██║███████╗╚██████╔╝╚█████╔╝  ███████╗██║██║░╚███║╚██████╔╝
 *   ╚═════╝░╚═╝╚══════╝░╚═════╝░░╚════╝░  ╚══════╝╚═╝╚═╝░░╚══╝░╚═════╝░
 *
 *   Это программное обеспечение имеет лицензию, как это сказано в файле
 *   COPYING, который Вы должны были получить в рамках распространения ПО.
 *
 *   Использование, изменение, копирование, распространение, обмен/продажа
 *   могут выполняться исключительно в согласии с условиями файла COPYING.
 *
 *   Mail: diegoling33@gmail.com
 *
 */

package me.ling.kipfin.core;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Сущность базы
 *
 * @param <TSource>
 */
public abstract class EntityDB<TSource, TNotFoundExceptionsType extends DatabaseEntityNotFoundException> {

    /**
     * Имя таблицы данных
     */
    public static String TABLE_NAME = "";

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
     * Загружает и возвращает все записи из таблицы. Используйте этот метод с осторжностью.
     * Для получения данных стоит при запуске приложения выполнить метод `EntityDB::update`,
     * и далее использовать метод `EntityDB::getCache`.
     *
     * @return - карта записей Identifier->TSource
     * @throws SQLException - исключения, которые могут возникнуть при работе с базой данных
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
     * Возвращает true, если id существует
     *
     * @param id - идентификатор
     * @return - результат проверки
     */
    public boolean hasId(Integer id) {
        return this.getCache().containsKey(id);
    }

    /**
     * Возвращает элемент по ID
     *
     * @param id - идентификатор
     * @return - возвращает элемент
     * @throws TNotFoundExceptionsType - исключение, оповещающее что элемент не найден
     */
    @NotNull
    public TSource getById(Integer id) throws TNotFoundExceptionsType {
        return this.getCache().get(id);
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
