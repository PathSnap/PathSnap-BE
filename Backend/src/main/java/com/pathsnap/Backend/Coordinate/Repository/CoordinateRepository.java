package com.pathsnap.Backend.Coordinate.Repository;

import com.pathsnap.Backend.Coordinate.Entitiy.Coordinate1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate1Entity,String> {

}
