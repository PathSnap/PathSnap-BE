package com.pathsnap.Backend.Record.Repository;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity,String>{
    List<RecordEntity> findByUser_UserId(String userId);
}
