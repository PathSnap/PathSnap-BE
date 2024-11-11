package com.pathsnap.Backend.WebSocket.Redis;

import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<Person, String> {
}