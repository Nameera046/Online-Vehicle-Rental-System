package com.vehicle.vechiclemanagement.dto;

public class VehicleDTO {
    private String type;
    private String name;
    private String imageUrl;
    private String pricePerHour;
    private int seats;
    private int bags;
    private int doors;
    private String ac;
    private String costDetails;
    private String cancellationPolicy;
    private String onlineCheckin;
    private boolean available;

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(String pricePerHour) { this.pricePerHour = pricePerHour; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
    public int getBags() { return bags; }
    public void setBags(int bags) { this.bags = bags; }
    public int getDoors() { return doors; }
    public void setDoors(int doors) { this.doors = doors; }
    public String getAc() { return ac; }
    public void setAc(String ac) { this.ac = ac; }
    public String getCostDetails() { return costDetails; }
    public void setCostDetails(String costDetails) { this.costDetails = costDetails; }
    public String getCancellationPolicy() { return cancellationPolicy; }
    public void setCancellationPolicy(String cancellationPolicy) { this.cancellationPolicy = cancellationPolicy; }
    public String getOnlineCheckin() { return onlineCheckin; }
    public void setOnlineCheckin(String onlineCheckin) { this.onlineCheckin = onlineCheckin; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}