package com.pathsnap.Backend.Coordinate.Repository;

import com.pathsnap.Backend.Coordinate.Entitiy.Coordinate1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate1Entity,String> {

}
