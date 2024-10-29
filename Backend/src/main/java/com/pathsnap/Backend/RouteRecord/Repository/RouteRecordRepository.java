package com.pathsnap.Backend.RouteRecord.Repository;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRecordRepository extends JpaRepository<RouteRecordEntity,String> {

}
