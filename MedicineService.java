package com.pharmacare.service;

import com.pharmacare.model.Medicine;
import com.pharmacare.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// MedicineService.java
@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // Search for medicines based on query and optional type
    public List<Medicine> searchMedicines(String query, String type) {
        if (type != null && !type.isEmpty()) {
            // Using the native query method with type filter
            return medicineRepository.findByNameContainingAndType(query, type);
        } else {
            // Using the native query method without type filter
            return medicineRepository.findByNameContaining(query);
        }
    }

    // Get medicine by ID with better error handling
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine with ID " + id + " not found"));
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    

    // Add a new medicine
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Update an existing medicine
    public Medicine updateMedicine(Long id, Medicine medicineDetails) {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()) {
            Medicine existingMedicine = optionalMedicine.get();
            existingMedicine.setName(medicineDetails.getName());
            existingMedicine.setType(medicineDetails.getType());
            existingMedicine.setPrice(medicineDetails.getPrice());
            existingMedicine.setStock(medicineDetails.getStock());
            return medicineRepository.save(existingMedicine);
        }
        return null; // Medicine not found
    }

    // Delete a medicine by ID
    public boolean deleteMedicine(Long id) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.deleteById(id);
            return true;
        }
        return false; // Medicine not found
    }
}
