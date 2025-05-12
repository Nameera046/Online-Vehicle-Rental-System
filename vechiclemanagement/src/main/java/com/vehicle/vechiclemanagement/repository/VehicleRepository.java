package com.vehicle.vechiclemanagement.repository;

import com.vehicle.vechiclemanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByType(String type);

    Optional<Vehicle> findByName(String name);
}