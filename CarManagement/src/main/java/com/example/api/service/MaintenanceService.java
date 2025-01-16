package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.Maintenance;
import com.example.api.repository.MaintenanceRepository;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    // Lấy tất cả lịch bảo trì
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    // Lấy lịch bảo trì theo ID
    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id).orElse(null);
    }

    // Tạo lịch bảo trì mới
    public Maintenance createMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    // Cập nhật lịch bảo trì
    public Maintenance updateMaintenance(Long id, Maintenance updatedMaintenance) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElse(null);
        if (maintenance != null) {
            // Cập nhật các trường cần thiết
            maintenance.setServiceDate(updatedMaintenance.getServiceDate());
            maintenance.setStatus(updatedMaintenance.getStatus());
            return maintenanceRepository.save(maintenance);
        } else {
            return null;
        }
    }

    // Xóa lịch bảo trì
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }
}