package com.properties.property.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.properties.property.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
	
	Property findByTitle(String title);

	List<Property> findByLocation(String locations);

	List<Property> findByType(String type);
	
	@Query("SELECT p FROM Property p WHERE p.price BETWEEN :price * 0.9 AND :price * 1.1")
	List<Property> findByPrice(@Param("price") double price);
}
