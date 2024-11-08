package com.pathsnap.Backend.PackTrip.Repository;

import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackTripRepository extends JpaRepository<PackTrip1Entity, String> {
    List<PackTrip1Entity> findByUser_UserId(String userId);
}