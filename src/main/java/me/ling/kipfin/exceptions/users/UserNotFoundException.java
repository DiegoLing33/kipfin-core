package me.ling.kipfin.exceptions.users;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;

public class UserNotFoundException extends DatabaseEntityNotFoundException {
    public UserNotFoundException(String q){
        super("Пользователь [ " + q + " ] не найден!");
    }
    public UserNotFoundException(Integer q){
        super("Пользователь с идентификатором [ " + q + " ] не найден!");
    }
}
