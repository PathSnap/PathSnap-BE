package com.pathsnap.Backend.User.Repository;

import com.pathsnap.Backend.User.Entity.User1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User1Entity,String> {

    //JpaRepository를 상속받아 기본적인 CRUD기능을 제공
    //String은 UserEntity의 userId 타입을 반환
    List<User1Entity> findByUserName(String userName);

}
