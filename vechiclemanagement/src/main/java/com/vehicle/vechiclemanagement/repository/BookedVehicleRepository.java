package com.vehicle.vechiclemanagement.repository;

import com.vehicle.vechiclemanagement.model.BookedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookedVehicleRepository extends JpaRepository<BookedVehicle, Long> {
    Optional<BookedVehicle> findByVehicleName(String vehicleName);
}