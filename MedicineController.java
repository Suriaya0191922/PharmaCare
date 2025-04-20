package com.pharmacare.controller;

import com.pharmacare.model.Medicine;
import com.pharmacare.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:63342")  // Allow frontend access
@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    private static final Logger LOGGER = Logger.getLogger(MedicineController.class.getName());

    @Autowired
    private MedicineService medicineService;

    // RESTful endpoint for searching medicines (returns JSON response)
    @GetMapping("/search")
    public ResponseEntity<?> searchMedicines(
            @RequestParam String query,
            @RequestParam(required = false) String type) {

        LOGGER.info("Searching for medicines with query: " + query + " and type: " + type);

        List<Medicine> medicines = medicineService.searchMedicines(query, type);
        if (medicines.isEmpty()) {
            LOGGER.warning("No medicines found for query: " + query + " and type: " + type);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"No medicines found\"}");
        }

        return ResponseEntity.ok(medicines);
    }

    // Get medicine by ID (RESTful API, returns a single medicine as JSON)
    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicineById(@PathVariable Long id) {
        LOGGER.info("Searching for medicine with ID: " + id);

        Medicine medicine = medicineService.getMedicineById(id);
        if (medicine == null) {
            LOGGER.warning("Medicine not found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Medicine not found\"}");
        }

        return ResponseEntity.ok(medicine);
    }
    // Fetch all medicines (RESTful API, returns JSON response)
    @GetMapping("/all")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        LOGGER.info("Fetching all medicines");

        List<Medicine> medicines = medicineService.getAllMedicines();
        if (medicines.isEmpty()) {
            LOGGER.warning("No medicines found in the database");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returns 404 with no body
        }

        return ResponseEntity.ok(medicines);
    }
    // Add a new medicine
    @PostMapping("/add")
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine) {
        LOGGER.info("Adding new medicine: " + medicine.getName());
        Medicine savedMedicine = medicineService.addMedicine(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicine);
    }

    // Update an existing medicine by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails) {
        LOGGER.info("Updating medicine with ID: " + id);
        Medicine updatedMedicine = medicineService.updateMedicine(id, medicineDetails);
        if (updatedMedicine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Medicine not found\"}");
        }
        return ResponseEntity.ok(updatedMedicine);
    }

    // Delete a medicine by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedicine(@PathVariable Long id) {
        LOGGER.info("Deleting medicine with ID: " + id);
        boolean deleted = medicineService.deleteMedicine(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Medicine not found\"}");
        }
        return ResponseEntity.ok("{\"message\": \"Medicine deleted successfully\"}");
    }


}
