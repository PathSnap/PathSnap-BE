package com.pathsnap.Backend.Record.Repository;

import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record1Entity,String>{

    //JpaRepository를 상속받아 기본적인 CRUD기능을 제공
    //String은 RecordEntity의 RecordId 타입을 반환

    // 여러 레코드를 리스트로 반환하는 경우

    List<Record1Entity> findByUser(User1Entity user);

    List<Record1Entity> findByUser_UserId(String userId);

    // SQL 쿼리를 사용하여 userId와 startDate 기준으로 정렬
    @Query("SELECT r FROM Record1Entity r WHERE r.user.userId = :userId ORDER BY r.startDate ASC")
    List<Record1Entity> findByUser_UserIdOrderByStartDateAsc(@Param("userId") String userId);

    @Query("SELECT r FROM Record1Entity r " +
            "JOIN FETCH r.user u " +
            "LEFT JOIN FETCH r.friends f " +
            "WHERE r.recordId = :recordId")
    Optional<Record1Entity> findByIdWithUserAndFriends(@Param("recordId") String recordId);

    //  SQL 쿼리를 사용하여 userId와 startDate 기준으로 정렬 && 월 , 년도 기준 정렬
    @Query("SELECT r FROM Record1Entity r WHERE r.user.userId = :userId AND " +
            "YEAR(r.startDate) = :year AND MONTH(r.startDate) = :month " +
            "ORDER BY r.startDate ASC")
    List<Record1Entity> findByUserIdAndStartDateMonth(
            @Param("userId") String userId,
            @Param("year") int year,
            @Param("month") int month
    );

}
