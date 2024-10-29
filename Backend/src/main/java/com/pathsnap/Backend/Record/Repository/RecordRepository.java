package com.pathsnap.Backend.Record.Repository;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity,String>{
    //JpaRepository를 상속받아 기본적인 CRUD기능을 제공
    //String은 RecordEntity의 RecordId 타입을 반환
}
