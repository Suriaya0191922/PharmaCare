package com.pharmacare.repository;

import com.pharmacare.model.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    // Native SQL query with pagination
    @Query(value = "SELECT * FROM medicine WHERE name LIKE %:name% AND type = :type", nativeQuery = true)
    List<Medicine> findByNameContainingAndType(String name, String type);

    // Native SQL query without type filter
    @Query(value = "SELECT * FROM medicine WHERE name LIKE %:name%", nativeQuery = true)
    List<Medicine> findByNameContaining(String name);

    // Native SQL query with pagination
    @Query(value = "SELECT * FROM medicine WHERE name LIKE %:name% AND type = :type", nativeQuery = true)
    Page<Medicine> findByNameContainingAndType(String name, String type, Pageable pageable);

    @Query(value = "SELECT * FROM medicine WHERE name LIKE %:name%", nativeQuery = true)
    Page<Medicine> findByNameContaining(String name, Pageable pageable);


}
