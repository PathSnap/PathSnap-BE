package com.pathsnap.Backend.Coordinate.Repository;

import com.pathsnap.Backend.Coordinate.Entitiy.CoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinateRepository extends JpaRepository<CoordinateEntity,String> {

}
