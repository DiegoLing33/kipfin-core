package me.ling.kipfin.exceptions.users;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;

public class UserAuthTokenNotFoundException extends DatabaseEntityNotFoundException {
    public UserAuthTokenNotFoundException(String q){
        super("User auth token [ " + q + " ] not found!");
    }
}
