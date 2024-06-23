package com.properties.property;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.properties.property.controller.PropertyController;
import com.properties.property.dto.PropertyRequest;
import com.properties.property.model.Property;
import com.properties.property.service.PropertyServiceImpl;

@WebMvcTest(PropertyController.class)
@SpringBootTest
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PropertyServiceImpl propertyService;

    @InjectMocks
    private PropertyController propertyController;

    private ObjectMapper objectMapper = new ObjectMapper();
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterProperty() throws Exception {
        PropertyRequest request = new PropertyRequest();
        request.setTitle("Test Property");
        request.setLocation("Test Location");
        request.setType("Test Type");
        request.setPrice(100000.0);

        Property mockProperty = new Property();
        mockProperty.setId(1);
        mockProperty.setTitle("Test Property");
        mockProperty.setLocation("Test Location");
        mockProperty.setType("Test Type");
        mockProperty.setPrice(100000.0);

        when(propertyService.registerProperty(any(PropertyRequest.class))).thenReturn(mockProperty);

        mockMvc.perform(MockMvcRequestBuilders.post("/property")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Property"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Test Location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Test Type"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100000.0));

        verify(propertyService, times(1)).registerProperty(any(PropertyRequest.class));
    }

    @Test
    public void testListAllProperties() throws Exception {
        Property mockProperty = new Property();
        mockProperty.setId(1);
        mockProperty.setTitle("Test Property");
        mockProperty.setLocation("Test Location");
        mockProperty.setType("Test Type");
        mockProperty.setPrice(100000.0);

        List<Property> mockPropertyList = Collections.singletonList(mockProperty);

        when(propertyService.listAllProperties()).thenReturn(mockPropertyList);

        mockMvc.perform(MockMvcRequestBuilders.get("/property")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Property"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("Test Location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("Test Type"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(100000.0));
        verify(propertyService, times(1)).listAllProperties();
    }
}