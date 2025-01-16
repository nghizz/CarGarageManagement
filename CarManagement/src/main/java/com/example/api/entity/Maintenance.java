package com.example.api.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenanceId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarProfile car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToMany
    @JoinTable(
            name = "maintenance_spareparts",
            joinColumns = @JoinColumn(name = "maintenance_id"),
            inverseJoinColumns = @JoinColumn(name = "spareparts_id")
    )
    private List<SpareParts> spareParts;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    private LocalDateTime serviceDate; 
    private String status;
    private String notes;
	public Long getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(Long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public CarProfile getCar() {
		return car;
	}
	public void setCar(CarProfile car) {
		this.car = car;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public List<SpareParts> getSpareParts() {
		return spareParts;
	}
	public void setSpareParts(List<SpareParts> spareParts) {
		this.spareParts = spareParts;
	}
	public User getTechnician() {
		return technician;
	}
	public void setTechnician(User technician) {
		this.technician = technician;
	}
	public LocalDateTime getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(LocalDateTime serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}