package com.vehicle.vechiclemanagement.controller;

import com.vehicle.vechiclemanagement.dto.BookedVehicleResponseDTO;
import com.vehicle.vechiclemanagement.dto.BookingRequest;
import com.vehicle.vechiclemanagement.model.BookedVehicle;
import com.vehicle.vechiclemanagement.model.Vehicle;
import com.vehicle.vechiclemanagement.repository.BookedVehicleRepository;
import com.vehicle.vechiclemanagement.repository.VehicleRepository;
import com.vehicle.vechiclemanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookedVehicleResponseDTO> bookVehicle(@RequestBody BookingRequest request) {
        BookedVehicleResponseDTO bookedVehicleDTO = bookingService.bookVehicle(request);
        return ResponseEntity.ok(bookedVehicleDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookedVehicleResponseDTO>> getAllBookedVehicles() {
        List<BookedVehicleResponseDTO> bookedVehicles = bookingService.getAllBookedVehicles();
        return ResponseEntity.ok(bookedVehicles);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookedVehicleResponseDTO> getBookingByName(@PathVariable String name) {
        BookedVehicleResponseDTO booking = bookingService.getBookingByName(name);
        return ResponseEntity.ok(booking);
    }
}
