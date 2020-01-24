package me.ling.kipfin.exceptions.users;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;

public class UserDevTokenNotFoundException extends DatabaseEntityNotFoundException {
    public UserDevTokenNotFoundException(String q){
        super("Токен приложения [ " + q + " ] не найден!");
    }
}
