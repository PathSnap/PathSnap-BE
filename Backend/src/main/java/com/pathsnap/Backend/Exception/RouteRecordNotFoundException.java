package com.pathsnap.Backend.Exception;

public class RouteRecordNotFoundException extends RuntimeException{
    public RouteRecordNotFoundException(String routeRecordId) {
        super("RouteRecord with ID " + routeRecordId + " not found");
    }
}

