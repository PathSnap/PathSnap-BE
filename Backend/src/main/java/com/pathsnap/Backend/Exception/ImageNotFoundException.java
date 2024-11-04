package com.pathsnap.Backend.Exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String imageId){
        super("Image not found with id: " + imageId);
    }
}
