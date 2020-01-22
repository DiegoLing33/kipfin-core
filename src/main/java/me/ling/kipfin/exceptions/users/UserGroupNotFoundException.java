package me.ling.kipfin.exceptions.users;

import me.ling.kipfin.exceptions.DatabaseEntityNotFoundException;

public class UserGroupNotFoundException extends DatabaseEntityNotFoundException {
    public UserGroupNotFoundException(String q){
        super("User group [ " + q + " ] not found!");
    }
    public UserGroupNotFoundException(Integer q){
        super("User group with identifier [ " + q + " ] not found!");
    }
}
