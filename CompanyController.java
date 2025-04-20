package com.pharmacare.controller;

import com.pharmacare.model.Company;
import com.pharmacare.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:63342")  // Allow frontend access
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private static final Logger LOGGER = Logger.getLogger(CompanyController.class.getName());

    @Autowired
    private CompanyService companyService;

    @GetMapping("/search")  // ✅ Search Endpoint
    public ResponseEntity<?> searchCompany(@RequestParam String name) {
        LOGGER.info("Searching for company: " + name);  // Log the search term

        Optional<Company> company = Optional.ofNullable(companyService.getCompanyByName(name));

        if (company.isPresent()) {
            LOGGER.info("Company found: " + company.get().getName());
            return ResponseEntity.ok(company.get());
        } else {
            LOGGER.warning("Company not found: " + name);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Company not found\"}");
        }
    }
}
