package me.ling.kipfin.exceptions;


public class DatabaseEntityNotFoundException extends RuntimeException {
    public DatabaseEntityNotFoundException(String description){
        super("Entity " + description + " not found!");
    }
}
