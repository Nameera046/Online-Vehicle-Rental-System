package com.vehicle.vechiclemanagement.service;

import com.vehicle.vechiclemanagement.dto.VehicleDTO;
import com.vehicle.vechiclemanagement.model.Vehicle;
import com.vehicle.vechiclemanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return vehicleRepository.findByType(type);
    }

    public Vehicle getVehicleByName(String name) {
        return vehicleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with name: " + name));
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setType(vehicleDTO.getType());
        vehicle.setName(vehicleDTO.getName());
        vehicle.setImageUrl(vehicleDTO.getImageUrl());
        vehicle.setPricePerHour(vehicleDTO.getPricePerHour());
        vehicle.setSeats(vehicleDTO.getSeats());
        vehicle.setBags(vehicleDTO.getBags());
        vehicle.setDoors(vehicleDTO.getDoors());
        vehicle.setAc(vehicleDTO.getAc());
        vehicle.setCostDetails(vehicleDTO.getCostDetails());
        vehicle.setCancellationPolicy(vehicleDTO.getCancellationPolicy());
        vehicle.setOnlineCheckin(vehicleDTO.getOnlineCheckin());
        vehicle.setAvailable(vehicleDTO.isAvailable());
        return vehicleRepository.save(vehicle);
    }
}