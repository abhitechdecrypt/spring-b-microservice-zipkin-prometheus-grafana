package com.properties.property.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.properties.property.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer>{

}
