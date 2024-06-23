package com.properties.property.service;

import com.properties.property.dto.PropertyRequest;
import com.properties.property.model.Property;
import java.util.List;

public interface PropertyServices {

	/**
	 * <h1>Post Saves a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Property registerProperty(PropertyRequest property);

	/**
	 * <h1>Get gives all Properties</h1>
	 * 
	 * @return List<Property>
	 */
	List<Property> listAllProperties();

	/**
	 * <h1>Get information about a particular Property</h1>
	 * 
	 * @param p_id
	 * @return Property
	 */
	Property getPropertyById(Integer p_id);

	/**
	 * <h1>Get information about a particular Property searching by Title of
	 * the</h1>
	 * 
	 * @param title
	 * @return Property
	 */
	Property getPropertyByTitle(String title);

	/**
	 * <h1>Get list of Properties searching by location</h1>
	 * 
	 * @param location
	 * @return List<Property>
	 */
	List<Property> getListOfPropertyByLoc(String location);

	/**
	 * <h1>Get list of Properties searching by type</h1>
	 * 
	 * @param type
	 * @return
	 */
	List<Property> getListOfPropertyByType(String type);

	/**
	 * <h1>Get list of Properties searching by price</h1>
	 * 
	 * @param price
	 * @return List<Property>
	 */
	List<Property> getListOfPropertyByprice(double price);

	/**
	 * <h1>Delete Deletes a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	String deleteProperty(int propertyId);

	/**
	 * <h1>Put updates a particular Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Property updateProperty(PropertyRequest property, int p_id);
}
