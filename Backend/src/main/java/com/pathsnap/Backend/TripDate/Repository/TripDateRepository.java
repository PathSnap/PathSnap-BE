package com.pathsnap.Backend.TripDate.Repository;

import com.pathsnap.Backend.TripDate.Entity.TripDate1Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripDateRepository  extends JpaRepository<TripDate1Entity, String> {
}
