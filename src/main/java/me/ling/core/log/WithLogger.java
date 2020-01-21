package me.ling.core.log;

/**
 * Класс логгера
 */
public class WithLogger {

    /**
     * Имя логгера
     */
    private String loggerName;

    private Object[] lastWaitMessage;

    public WithLogger(String name) {
        this.loggerName = name;
    }

    /**
     * Начинает ожидание
     *
     * @param message - сообщение
     */
    public void wait(Object... message) {
        this.lastWaitMessage = message;
        Object[] obj = new Object[message.length + 1];
        obj[0] = Logger.WAIT;
        System.arraycopy(message, 0, obj, 1, message.length);
        System.out.print(Logger.getLoggerString(this.loggerName, obj));
    }

    /**
     * Завершает ожидание
     *
     * @param result - результат
     * @return - дублирует результат
     */
    public Boolean result(Boolean result) {
        Object[] obj = new Object[this.lastWaitMessage.length + 1];
        obj[0] = result;
        System.arraycopy(this.lastWaitMessage, 0, obj, 1, this.lastWaitMessage.length);
        System.out.print("\r" + Logger.getLoggerString(this.loggerName, obj) + "\n");
        return result;
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
