package com.vehicle.vechiclemanagement.service;

import com.vehicle.vechiclemanagement.dto.BookedVehicleResponseDTO;
import com.vehicle.vechiclemanagement.dto.BookingRequest;
import com.vehicle.vechiclemanagement.model.BookedVehicle;
import com.vehicle.vechiclemanagement.model.User;
import com.vehicle.vechiclemanagement.model.Vehicle;
import com.vehicle.vechiclemanagement.repository.BookedVehicleRepository;
import com.vehicle.vechiclemanagement.repository.UserRepository;
import com.vehicle.vechiclemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookedVehicleRepository bookedVehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public BookedVehicleResponseDTO bookVehicle(BookingRequest request) {
        // Validate user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        // Validate vehicle
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + request.getVehicleId()));

        // Map request to entity
        BookedVehicle bookedVehicle = new BookedVehicle();
        bookedVehicle.setUser(user);
        bookedVehicle.setVehicle(vehicle);
        bookedVehicle.setVehicleName(request.getVehicleName());
        bookedVehicle.setVehicleType(request.getVehicleType());
        bookedVehicle.setPickupLocation(request.getPickupLocation());
        bookedVehicle.setDropoffLocation(request.getDropoffLocation());
        bookedVehicle.setPickupDate(request.getPickupDate());
        bookedVehicle.setDropoffDate(request.getDropoffDate());
        bookedVehicle.setBookingDate(LocalDateTime.now());
        bookedVehicle.setStatus(request.getStatus());

        // Save to database
        BookedVehicle savedBooking = bookedVehicleRepository.save(bookedVehicle);

        // Map to DTO
        return mapToResponseDTO(savedBooking);
    }

    public List<BookedVehicleResponseDTO> getAllBookedVehicles() {
        return bookedVehicleRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public BookedVehicleResponseDTO getBookingByName(String vehicleName) {
        BookedVehicle bookedVehicle = bookedVehicleRepository.findByVehicleName(vehicleName)
                .orElseThrow(() -> new RuntimeException("Booking not found with vehicle name: " + vehicleName));
        return mapToResponseDTO(bookedVehicle);
    }

    private BookedVehicleResponseDTO mapToResponseDTO(BookedVehicle bookedVehicle) {
        BookedVehicleResponseDTO dto = new BookedVehicleResponseDTO();
        dto.setId(bookedVehicle.getId());
        dto.setUserId(bookedVehicle.getUser().getId());
        // Combine firstName and lastName for userName
        String userName = bookedVehicle.getUser().getFirstName() + " " + bookedVehicle.getUser().getLastName();
        dto.setUserName(userName);
        dto.setUserEmail(bookedVehicle.getUser().getEmail());
        dto.setVehicleId(bookedVehicle.getVehicle().getId());
        dto.setVehicleName(bookedVehicle.getVehicleName());
        dto.setVehicleType(bookedVehicle.getVehicleType());
        dto.setPickupLocation(bookedVehicle.getPickupLocation());
        dto.setDropoffLocation(bookedVehicle.getDropoffLocation());
        dto.setPickupDate(bookedVehicle.getPickupDate());
        dto.setDropoffDate(bookedVehicle.getDropoffDate());
        dto.setBookingDate(bookedVehicle.getBookingDate());
        dto.setStatus(bookedVehicle.getStatus());
        return dto;
    }
}