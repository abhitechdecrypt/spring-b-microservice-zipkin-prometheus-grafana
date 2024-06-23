package com.properties.booking.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.properties.booking.dto.Property;
import com.properties.booking.dto.PropertyRequest;

@FeignClient(name = "PROPERTY", path = "/property")
public interface PropertyFeignClientConfig {

	@GetMapping("/{id}")
	public ResponseEntity<Property> getPropertyById(@PathVariable("id") Integer id);

	@PutMapping("/{id}")
	public ResponseEntity<Property> updateProperty(@RequestBody PropertyRequest propertyReq,
			@PathVariable("id") int id);

}
