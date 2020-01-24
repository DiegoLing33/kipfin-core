package me.ling.kipfin.exceptions.users;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;

public class UserGroupNotFoundException extends DatabaseEntityNotFoundException {
    public UserGroupNotFoundException(String q){
        super("Группа пользователей[ " + q + " ] не найдена!");
    }
    public UserGroupNotFoundException(Integer q){
        super("Группа пользователей с идентификатором [ " + q + " ] не найдена!");
    }
}
