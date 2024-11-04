package com.pathsnap.Backend.Exception;

public class PhotoRecordNotFoundException extends RuntimeException{
    public PhotoRecordNotFoundException(String photoId) {
        super("PhotoRecord with ID " + photoId + " not found");
    }
}
