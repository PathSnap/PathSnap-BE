package com.pathsnap.Backend.Record.Repository;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity,String>{

    //JpaRepository를 상속받아 기본적인 CRUD기능을 제공
    //String은 RecordEntity의 RecordId 타입을 반환

    // 여러 레코드를 리스트로 반환하는 경우

    List<RecordEntity> findByUser(UserEntity user);

    List<RecordEntity> findByUser_UserId(String userId);

    // SQL 쿼리를 사용하여 userId와 startDate 기준으로 정렬
    @Query("SELECT r FROM RecordEntity r WHERE r.user.userId = :userId ORDER BY r.startDate ASC")
    List<RecordEntity> findByUser_UserIdOrderByStartDateAsc(@Param("userId") String userId);
}
