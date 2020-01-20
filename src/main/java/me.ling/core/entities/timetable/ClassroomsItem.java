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

package me.ling.core.entities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Элемент аудиторий
 */
public class ClassroomsItem {

    @JsonProperty
    private final String where;

    @JsonProperty
    private final String who;

    @JsonProperty
    private final Integer index;

    public ClassroomsItem(String where, String who, Integer index) {
        this.where = where;
        this.who = who;
        this.index = index;
    }

    /**
     * Возвращает аудиторию
     * @return аудитория
     */
    public String getWhere() {
        return where;
    }

    /**
     * Возвращает преподавателя
     * @return имя преподавателя
     */
    public String getWho() {
        return who;
    }

    /**
     * Возвращает индекс
     * @return индекс
     */
    public Integer getIndex() {
        return index;
    }
}
