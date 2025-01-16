package com.example.api.controller;

import com.example.api.entity.Maintenance;
import com.example.api.service.MaintenanceService;

import org.springframework.ui.Model; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller 
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;
    
    @GetMapping("") 
    public String showMaintenancePage(Model model) {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        model.addAttribute("maintenances", maintenances);
        return "Maintenance/Maintenance"; 
    }

    // Lấy tất cả lịch bảo trì
    @GetMapping("/list")
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    // Lấy lịch bảo trì theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        return maintenance != null ? ResponseEntity.ok(maintenance) : ResponseEntity.notFound().build();
    }

    // Tạo lịch bảo trì mới
    @PostMapping("")
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        return new ResponseEntity<>(maintenanceService.createMaintenance(maintenance), HttpStatus.CREATED);
    }

    // Cập nhật lịch bảo trì
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody Maintenance updatedMaintenance) {
        Maintenance maintenance = maintenanceService.updateMaintenance(id, updatedMaintenance);
        return maintenance != null ? ResponseEntity.ok(maintenance) : ResponseEntity.notFound().build();
    }

    // Xóa lịch bảo trì
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}