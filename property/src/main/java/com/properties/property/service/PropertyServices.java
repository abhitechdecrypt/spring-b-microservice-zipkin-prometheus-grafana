package com.properties.property.service;

import com.properties.property.model.Property;
import java.util.List;

public interface PropertyServices {

	/**
	 * <h1>Post Saves a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Property registerProperty(Property property);

//Get gives all Properties
	List<Property> listAllProperties();

//	 Get information about a particular Property 
	Property getPropertyById(Integer p_id);

	// Get information about a particular Property searching by Title of the
	// property
	Property getPropertyByTitle(String title);

	// Get list of Properties searching by location
	List<Property> getListOfPropertyByLoc(String location);

//	Get list of Properties searching by type 
	List<Property> getListOfPropertyByType(String type);

	// Get list of Properties searching by price
	List<Property> getListOfPropertyByprice(double price);

	// Delete Deletes a Property
	Property deleteProperty(Property property);

//Put updates a particular Property
	Property updateProperty(Property property);
}
