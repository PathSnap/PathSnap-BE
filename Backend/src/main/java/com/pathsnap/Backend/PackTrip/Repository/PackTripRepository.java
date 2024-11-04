package com.pathsnap.Backend.PackTrip.Repository;

import com.pathsnap.Backend.PackTrip.Entity.PackTripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackTripRepository extends JpaRepository<PackTripEntity, String> {
    List<PackTripEntity> findByUser_UserId(String userId);
}