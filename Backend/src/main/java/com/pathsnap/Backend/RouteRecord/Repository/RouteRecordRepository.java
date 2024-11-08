package com.pathsnap.Backend.RouteRecord.Repository;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRecordRepository extends JpaRepository<RouteRecord1Entity,String> {

    List<RouteRecord1Entity> findByRecord_RecordId(String recordId);

}
