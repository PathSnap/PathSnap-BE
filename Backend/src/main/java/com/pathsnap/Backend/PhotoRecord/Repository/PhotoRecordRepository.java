package com.pathsnap.Backend.PhotoRecord.Repository;

import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRecordRepository extends JpaRepository<PhotoRecord1Entity,String> {
    List<PhotoRecord1Entity> findByRecord_RecordId(String recordId);

    // recordId와 일치하는 데이터를 photo_date 기준으로 정렬하여 조회
    @Query("SELECT p FROM PhotoRecord1Entity p WHERE p.record.recordId = :recordId ORDER BY p.photoDate ASC")
    List<PhotoRecord1Entity> findByRecordIdOrderByPhotoDateAsc(@Param("recordId") String recordId);

}

