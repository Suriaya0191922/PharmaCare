package com.pharmacare.model;

import jakarta.persistence.*;
import java.util.List;

// Medicine.java
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // "allopathy" or "homeopathy"
    private String company;
    private String description;
    private Double price;

    @ElementCollection
    private List<String> ingredients;

    private String dosage; // Added field for dosage instructions
    private String sideEffects; // Added field for side effects
    private String usageInstructions; // Added field for usage instructions
    private Integer stock; // Fixed stock type from Object to Integer

    // Default constructor
    public Medicine() {}

    // Constructor with parameters
    public Medicine(Long id, String name, String type, String company, String description, Double price, List<String> ingredients, String dosage, String sideEffects, String usageInstructions, Integer stock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
        this.dosage = dosage;
        this.sideEffects = sideEffects;
        this.usageInstructions = usageInstructions;
        this.stock = stock; // Fixed constructor
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getUsageInstructions() {
        return usageInstructions;
    }

    public void setUsageInstructions(String usageInstructions) {
        this.usageInstructions = usageInstructions;
    }

    public Integer getStock() {
        return stock; // Fixed missing return statement
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
