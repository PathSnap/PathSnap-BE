package com.pathsnap.Backend.Exception;

public class FriendNotFoundException extends RuntimeException{
    public FriendNotFoundException(String friendId){

        super("Friend not found with id: " + friendId);

    }
}
