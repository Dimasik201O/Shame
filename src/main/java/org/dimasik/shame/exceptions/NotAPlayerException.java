package org.dimasik.shame.exceptions;

public class NotAPlayerException extends Exception {
    public NotAPlayerException() {
        super("Отправитель команды должен быть игроком!");
    }
}
