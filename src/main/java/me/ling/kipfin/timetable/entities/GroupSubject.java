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

import java.util.Map;

/**
 * Элемент расписания с аудиториями и преподавателями
 */
public class GroupSubject {

    @JsonProperty
    private String title;

    @JsonProperty
    private Map<String, String> who;

    @JsonProperty
    private Integer index;

    public GroupSubject(){}
    public GroupSubject(String title, Map<String, String> who, Integer index) {
        this.title = title;
        this.who = who;
        this.index = index;
    }

    /**
     * Возвращает информацию о преподавателе и аудитории
     *
     * @return связка Преподаваетль->Аудитория
     */
    public Map<String, String> getWho() {
        return who;
    }

    /**
     * Возвращает индекс пары
     *
     * @return индекс пары
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Возвращает название пары
     *
     * @return - название пары
     */
    public String getTitle() {
        return title;
    }
}
