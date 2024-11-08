package com.pathsnap.Backend.RouteRecord.Repository;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRecordRepository extends JpaRepository<RouteRecordEntity,String> {

    List<RouteRecordEntity> findByRecord_RecordId(String recordId);

}
