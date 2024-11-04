package com.pathsnap.Backend.PhotoRecord.Repository;

import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRecordRepository extends JpaRepository<PhotoRecordEntity,String> {
    List<PhotoRecordEntity> findByRecord_RecordId(String recordId);
}
