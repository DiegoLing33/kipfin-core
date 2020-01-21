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

package me.ling.kipfin.timetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Элемент аудиторий
 */
public class ClassroomsItem {

    @JsonProperty
    private String where;

    @JsonProperty
    private String who;

    @JsonProperty
    private Integer index;

    @JsonProperty
    private String group;

    public ClassroomsItem(){}
    public ClassroomsItem(String where, String who, String group, Integer index) {
        this.where = where;
        this.who = who;
        this.group = group;
        this.index = index;
    }

    /**
     * Возвращает аудиторию
     *
     * @return аудитория
     */
    public String getWhere() {
        return where;
    }

    /**
     * Возвращает преподавателя
     *
     * @return имя преподавателя
     */
    public String getWho() {
        return who;
    }

    /**
     * Возвращает индекс
     *
     * @return индекс
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Возвращает группу
     *
     * @return группа
     */
    public String getGroup() {
        return group;
    }
}
