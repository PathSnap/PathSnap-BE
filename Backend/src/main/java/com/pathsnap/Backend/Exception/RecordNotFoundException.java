package com.pathsnap.Backend.Exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String recordId) {
        super("Record with ID " + recordId + " not found");
    }
}
