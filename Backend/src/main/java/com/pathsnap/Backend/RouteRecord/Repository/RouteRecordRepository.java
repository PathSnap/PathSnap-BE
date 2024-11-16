package com.pathsnap.Backend.RouteRecord.Repository;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRecordRepository extends JpaRepository<RouteRecord1Entity,String> {

    @Query("SELECT DISTINCT r FROM RouteRecord1Entity r " +
            "JOIN r.coordinates c " +
            "JOIN r.record rec " +
            "WHERE rec.recordId = :recordId " +
            "ORDER BY r.startDate")
    List<RouteRecord1Entity> findByRecordIdWithCoordinates(@Param("recordId") String recordId);
}
