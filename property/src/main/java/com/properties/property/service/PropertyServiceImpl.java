package com.properties.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.properties.property.constant.PropertyConstant;
import com.properties.property.dto.PropertyRequest;
import com.properties.property.dtoMapper.PropertyMapper;
import com.properties.property.exception.PropertyAlreadyExistException;
import com.properties.property.exception.PropertyDataNotFoundException;
import com.properties.property.model.Property;
import com.properties.property.repositroy.PropertyRepository;

import jakarta.transaction.Transactional;

@Service
public class PropertyServiceImpl implements PropertyServices {

	@Autowired
	private PropertyRepository propertyRepository;

	@Override
	@Transactional
	public Property registerProperty(PropertyRequest propertyReq) {
		Property existingProperty = propertyRepository.findByTitle(propertyReq.getTitle());
		if (existingProperty != null) {
			throw new PropertyAlreadyExistException(
					"Property you are trying to register is already registered in our system.",
					PropertyConstant.PROPERTY_ALREADY_EXIT_CODE);
		}
		Property newProperty = PropertyMapper.propertyRequestToPropertyMapper(propertyReq, new Property());
		propertyRepository.save(newProperty);
		return newProperty;
	}

	@Override
	public List<Property> listAllProperties() {
		List<Property> listOfProperty = propertyRepository.findAll();
		if (listOfProperty.size() == 0) {
			throw new PropertyDataNotFoundException(
					"There is no Property Register with us. So, Please keep some patience to see the latest and best property",
					PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE);
		}
		return listOfProperty;
	}

	@Override
	public Property getPropertyById(Integer p_id) {
		Property property = propertyRepository.findById(p_id)
				.orElseThrow(() -> new PropertyDataNotFoundException("The Property You are tyring to find is not found",
						PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE));

		return property;
	}

	@Override
	public Property getPropertyByTitle(String title) {
		Property property = propertyRepository.findByTitle(title);
		if (property == null) {
			throw new PropertyDataNotFoundException("The Property You are tyring to find is not found",
					PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE);
		}
		return property;
	}

	@Override
	public List<Property> getListOfPropertyByLoc(String location) {
		List<Property> property = propertyRepository.findByLocation(location);
		if (property == null) {
			throw new PropertyDataNotFoundException("The Property You are tyring to find is not found",
					PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE);
		}
		return property.stream().collect(Collectors.toList());
	}

	@Override
	public List<Property> getListOfPropertyByType(String type) {
		List<Property> property = propertyRepository.findByType(type);
		if (property == null) {
			throw new PropertyDataNotFoundException("The Property You are tyring to find is not found",
					PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE);
		}
		return property.stream().collect(Collectors.toList());
	}

	@Override
	public List<Property> getListOfPropertyByprice(double price) {
		List<Property> property = propertyRepository.findByPrice(price);
		if (property == null) {
			throw new PropertyDataNotFoundException("The Property You are tyring to find is not found",
					PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE);
		}
		return property.stream().collect(Collectors.toList());
	}

	@Override
	public String deleteProperty(int propertyId) {
		String response = "";
		Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyDataNotFoundException(
				"The Property You are tyring to delete is not found", PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE));
		if (property != null) {
			propertyRepository.delete(property);
			response = "SUCCESS";
		}
		return response;
	}

	@Override
	public Property updateProperty(PropertyRequest propertyReq, int p_id) {
		Property property = propertyRepository.findById(p_id).orElseThrow(() -> new PropertyDataNotFoundException(
				"The Property You are tyring to update is not found", PropertyConstant.PROPERTY_NOT_FOUND_EXIT_CODE));
		Property propertyMapper = PropertyMapper.propertyRequestToPropertyMapper(propertyReq, property);
		propertyRepository.save(propertyMapper);
		return propertyMapper;
	}

}
