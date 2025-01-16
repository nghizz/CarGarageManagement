package com.example.api.service;

import com.example.api.entity.Invoice;
import com.example.api.entity.Maintenance;
import com.example.api.entity.SpareParts;
import com.example.api.repository.InvoiceRepository;
import com.example.api.repository.MaintenanceRepository;
import com.example.api.repository.SparePartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private SparePartsRepository sparePartsRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    // Tổng số lượng phụ tùng còn lại trong kho
    public int getTotalSparePartsQuantity() {
        List<SpareParts> spareParts = sparePartsRepository.findAll();
        return spareParts.stream().mapToInt(SpareParts::getQuantity).sum();
    }

    // Số lượng phụ tùng đã sử dụng
    public int getUsedSparePartsQuantity() {
        // Lấy danh sách tất cả các dịch vụ bảo trì
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        // Tính tổng số lượng phụ tùng đã sử dụng từ các dịch vụ bảo trì
        int usedQuantity = maintenances.stream()
                .flatMap(maintenance -> maintenance.getSpareParts().stream()) // Lấy danh sách phụ tùng từ mỗi dịch vụ bảo trì
                .mapToInt(SpareParts::getQuantity) // Lấy số lượng của từng phụ tùng
                .sum(); // Tính tổng số lượng
        return usedQuantity;
    }

    // Tổng tiền doanh thu (ra và vào)
    public BigDecimal getTotalRevenue() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(Invoice::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Lợi nhuận
    public BigDecimal getProfit() {
        // Giả sử lợi nhuận được tính bằng tổng doanh thu trừ đi tổng chi phí
        BigDecimal totalRevenue = getTotalRevenue();
        BigDecimal totalCost = calculateTotalCost(); // Thay thế bằng logic tính toán chi phí thực tế
        return totalRevenue.subtract(totalCost);
    }

    // Phương thức tính toán tổng chi phí (cần được triển khai dựa trên logic nghiệp vụ)
    private BigDecimal calculateTotalCost() {
        // Tính toán tổng chi phí từ các nguồn khác nhau (ví dụ: chi phí phụ tùng, nhân công, ...)
        // ...
        return BigDecimal.ZERO; // Trả về 0 tạm thời
    }
}