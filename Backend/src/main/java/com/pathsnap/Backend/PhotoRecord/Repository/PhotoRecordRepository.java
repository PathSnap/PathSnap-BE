package com.pathsnap.Backend.PhotoRecord.Repository;

import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRecordRepository extends JpaRepository<PhotoRecord1Entity,String> {
    List<PhotoRecord1Entity> findByRecord_RecordId(String recordId);

}

