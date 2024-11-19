package com.pathsnap.Backend.Friend.Repository;

import com.pathsnap.Backend.Friend.Entity.Friend1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend1Entity,String> {
}
