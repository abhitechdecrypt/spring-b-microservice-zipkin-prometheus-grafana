package com.properties.property.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.properties.property.constant.PropertyConstant;
import com.properties.property.dto.ErrorResponseDto;
import com.properties.property.dto.PropertyRequest;
import com.properties.property.dto.ResponseDto;
import com.properties.property.model.Property;
import com.properties.property.service.PropertyServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyServiceImpl propertyService;

	@Operation(summary = "Property REST API for rgister", description = "REST API to register new property")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping
	public ResponseEntity<?> registerProperty(@Validated @RequestBody PropertyRequest propertyReq) {
		ResponseDto responseDto = new ResponseDto();
		Property property = propertyService.registerProperty(propertyReq);
		if (property != null) {
			responseDto.setStatusMsg(PropertyConstant.PROPERTY_MESSAGE_201);
			responseDto.setStatusCode(PropertyConstant.PROPERTY_STATUS_201);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@Operation(summary = "Property REST API list property", description = "REST API to register all the property")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping
	public ResponseEntity<List<Property>> listAllProperties() {
		List<Property> properties = propertyService.listAllProperties();
		return ResponseEntity.status(HttpStatus.OK).body(properties);
	}

	@Operation(summary = "Property REST API get Property By Id", description = "REST API to find property by Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<Property> getPropertyById(@PathVariable("id") Integer id) {
		Property property = propertyService.getPropertyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(property);
	}

	@Operation(summary = "Property REST API get property by Title", description = "REST API to find property by title")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/title/{title}")
	public ResponseEntity<Property> getPropertyByTitle(@PathVariable("title") String title) {
		Property property = propertyService.getPropertyByTitle(title);
		return ResponseEntity.status(HttpStatus.OK).body(property);
	}

	@Operation(summary = "Property REST API find property by locations", description = "REST API to find property by 		locations")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/location/{location}")
	public ResponseEntity<List<Property>> getListOfPropertyByLoc(@PathVariable("location") String location) {
		List<Property> properties = propertyService.getListOfPropertyByLoc(location);
		return ResponseEntity.ok(properties);
	}

	@Operation(summary = "Property REST API find property by types", description = "REST API to find property by types")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Property>> getListOfPropertyByType(@PathVariable("type") String type) {
		List<Property> properties = propertyService.getListOfPropertyByType(type);
		return ResponseEntity.status(HttpStatus.OK).body(properties);
	}

	@Operation(summary = "Property REST API  find property by price", description = "REST API to find property by price")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/price/{price}")
	public ResponseEntity<List<Property>> getListOfPropertyByPrice(@PathVariable("price") double price) {
		List<Property> properties = propertyService.getListOfPropertyByprice(price);
		return ResponseEntity.status(HttpStatus.OK).body(properties);
	}

	@Operation(summary = "Property REST API delete property by id", description = "REST API to delete property by id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProperty(@PathVariable("id") int id) {
		String response = propertyService.deleteProperty(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Operation(summary = "Property REST API to update property", description = "REST API to update property")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/{id}")
	public ResponseEntity<Property> updateProperty(@RequestBody PropertyRequest propertyReq,
			@PathVariable("id") int id) {
		Property property = propertyService.updateProperty(propertyReq, id);
		return ResponseEntity.status(HttpStatus.OK).body(property);
	}
}
