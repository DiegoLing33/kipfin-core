package me.ling.core.log;

/**
 * Класс логгера
 */
public class WithLogger {

    /**
     * Имя логгера
     */
    private String loggerName;

    public WithLogger(String name) {
        this.loggerName = name;
    }

    /**
     * Выводит лог в консоль
     *
     * @param message - сообщение по частям
     */
    protected void log(Object... message) {
        Logger.logAs(this.loggerName, message);
    }

}
