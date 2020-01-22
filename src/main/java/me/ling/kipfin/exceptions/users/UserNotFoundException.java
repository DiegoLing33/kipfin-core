package me.ling.kipfin.exceptions.users;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;

public class UserNotFoundException extends DatabaseEntityNotFoundException {
    public UserNotFoundException(String q){
        super("User [ " + q + " ] not found!");
    }
    public UserNotFoundException(Integer q){
        super("User with identifier [ " + q + " ] not found!");
    }
}
