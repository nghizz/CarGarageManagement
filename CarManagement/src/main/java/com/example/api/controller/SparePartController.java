package com.example.api.controller;

import com.example.api.entity.SpareParts;
import com.example.api.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spareparts")
public class SparePartController {

    @Autowired
    private SparePartService sparePartService;

    @GetMapping
    public List<SpareParts> getAllSpareParts() {
        return sparePartService.getAllSpareParts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpareParts> getSparePartById(@PathVariable Long id) {
        SpareParts spareParts = sparePartService.getSparePartById(id);
        return spareParts != null ? ResponseEntity.ok(spareParts) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SpareParts> createSparePart(@RequestBody SpareParts spareParts) {
        SpareParts createdSpareParts = sparePartService.createSparePart(spareParts);
        return ResponseEntity.ok(createdSpareParts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpareParts> updateSparePart(@PathVariable Long id, @RequestBody SpareParts updatedSpareParts) {
        SpareParts spareParts = sparePartService.updateSparePart(id, updatedSpareParts);
        return spareParts != null ? ResponseEntity.ok(spareParts) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSparePart(@PathVariable Long id) {
        sparePartService.deleteSparePart(id);
        return ResponseEntity.noContent().build();
    }
}