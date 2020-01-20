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

import java.util.Map;

/**
 * Элемент расписания с аудиториями и преподавателями
 */
public class TimetableWithClassroomItemEntity {

    @JsonProperty
    private final String group;

    @JsonProperty
    private final Map<String, String> who;

    @JsonProperty
    private final Integer index;

    public TimetableWithClassroomItemEntity(String group, Map<String, String> who, Integer index) {
        this.group = group;
        this.who = who;
        this.index = index;
    }

    /**
     * Возвращает группу
     * @return Группа пользователя
     */
    public String getGroup() {
        return group;
    }

    /**
     * Возвращает информацию о преподавателе и аудитории
     * @return связка Преподаваетль->Аудитория
     */
    public Map<String, String> getWho() {
        return who;
    }

    public Integer getIndex() {
        return index;
    }
}
