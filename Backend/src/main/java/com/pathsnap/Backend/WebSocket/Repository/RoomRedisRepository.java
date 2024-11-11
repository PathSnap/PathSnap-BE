package com.pathsnap.Backend.WebSocket.Repository;

import com.pathsnap.Backend.WebSocket.Dto.Res.RoomRedisResDto;
import org.springframework.data.repository.CrudRepository;

public interface RoomRedisRepository  extends CrudRepository<RoomRedisResDto, String> {
}
