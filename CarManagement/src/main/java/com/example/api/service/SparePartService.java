package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.SpareParts;
import com.example.api.repository.SparePartsRepository;

@Service
public class SparePartService {
    @Autowired
    private SparePartsRepository sparePartsRepository;

    // Lấy danh sách tất cả phụ tùng
    public List<SpareParts> getAllSpareParts() {
        return sparePartsRepository.findAll();
    }

    // Lấy phụ tùng theo ID
    public SpareParts getSparePartById(Long id) {
        return sparePartsRepository.findById(id).orElse(null);
    }

    // Tạo phụ tùng mới
    public SpareParts createSparePart(SpareParts spareParts) {
        return sparePartsRepository.save(spareParts);
    }

    // Cập nhật thông tin phụ tùng
    public SpareParts updateSparePart(Long id, SpareParts updatedSpareParts) {
        SpareParts spareParts = sparePartsRepository.findById(id).orElse(null);
        if (spareParts != null) {
            // Cập nhật các trường
            spareParts.setPartName(updatedSpareParts.getPartName());
            spareParts.setPartCode(updatedSpareParts.getPartCode());
            spareParts.setQuantity(updatedSpareParts.getQuantity());
            spareParts.setPrice(updatedSpareParts.getPrice());
            // ...
            return sparePartsRepository.save(spareParts);
        } else {
            return null;
        }
    }

    // Xóa phụ tùng
    public void deleteSparePart(Long id) {
        sparePartsRepository.deleteById(id);
    }
}