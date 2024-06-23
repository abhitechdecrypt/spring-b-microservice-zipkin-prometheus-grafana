package com.properties.property.dtoMapper;

import java.util.Optional;

import com.properties.property.dto.PropertyRequest;
import com.properties.property.model.Property;

public class PropertyMapper {

	public static Property propertyRequestToPropertyMapper(PropertyRequest propertyReq, Property property) {
	    Optional.ofNullable(propertyReq.getTitle()).ifPresent(property::setTitle);
	    Optional.ofNullable(propertyReq.getLocation()).ifPresent(property::setLocation);
	    
	    Optional.ofNullable(propertyReq.getAvailableRooms())
	            .filter(availableRooms -> availableRooms != 0)
	            .ifPresent(property::setAvailableRooms);
	    
	    property.setLockForBook(propertyReq.isLockForBook()); // Assuming boolean can't be null
	    
	    Optional.ofNullable(propertyReq.getPrice())
	            .filter(price -> price != 0.0)
	            .ifPresent(property::setPrice);
	    
	    Optional.ofNullable(propertyReq.getType()).ifPresent(property::setType);
	    
	    return property;
	}
}
