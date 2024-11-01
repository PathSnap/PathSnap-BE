package com.pathsnap.Backend.PhotoRecord.Repository;

import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRecordRepository extends JpaRepository<PhotoRecordEntity,String> {

}
