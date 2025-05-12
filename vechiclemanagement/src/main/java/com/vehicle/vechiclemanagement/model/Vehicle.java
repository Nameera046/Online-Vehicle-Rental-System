package com.vehicle.vechiclemanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @NotBlank(message = "Price per hour is required")
    private String pricePerHour;

    @NotNull(message = "Seats are required")
    @Min(value = 1, message = "Seats must be at least 1")
    private Integer seats;

    @NotNull(message = "Bags are required")
    @Min(value = 0, message = "Bags cannot be negative")
    private Integer bags;

    @NotNull(message = "Doors are required")
    @Min(value = 1, message = "Doors must be at least 1")
    private Integer doors;

    @NotBlank(message = "A/C is required")
    private String ac;

    @NotBlank(message = "Cost details are required")
    private String costDetails;

    @NotBlank(message = "Cancellation policy is required")
    private String cancellationPolicy;

    @NotBlank(message = "Online check-in is required")
    private String onlineCheckin;

    @NotNull(message = "Availability is required")
    private Boolean available;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(String pricePerHour) { this.pricePerHour = pricePerHour; }
    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }
    public Integer getBags() { return bags; }
    public void setBags(Integer bags) { this.bags = bags; }
    public Integer getDoors() { return doors; }
    public void setDoors(Integer doors) { this.doors = doors; }
    public String getAc() { return ac; }
    public void setAc(String ac) { this.ac = ac; }
    public String getCostDetails() { return costDetails; }
    public void setCostDetails(String costDetails) { this.costDetails = costDetails; }
    public String getCancellationPolicy() { return cancellationPolicy; }
    public void setCancellationPolicy(String cancellationPolicy) { this.cancellationPolicy = cancellationPolicy; }
    public String getOnlineCheckin() { return onlineCheckin; }
    public void setOnlineCheckin(String onlineCheckin) { this.onlineCheckin = onlineCheckin; }
    public Boolean isAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}