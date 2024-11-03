package com.pathsnap.Backend.Record.Repository;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity,String>{
    //JpaRepository를 상속받아 기본적인 CRUD기능을 제공
    //String은 RecordEntity의 RecordId 타입을 반환

    // 여러 레코드를 리스트로 반환하는 경우

    List<RecordEntity> findByUser(UserEntity user);
}
