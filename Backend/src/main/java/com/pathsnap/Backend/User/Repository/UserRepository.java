package com.pathsnap.Backend.User.Repository;

import com.pathsnap.Backend.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    //JpaRepository를 상속받아 기본적인 CRUD기능을 제공
    //String은 UserEntity의 userId 타입을 반환
}
