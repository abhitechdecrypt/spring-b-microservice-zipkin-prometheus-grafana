package com.properties.payments.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.properties.payments.dto.PaymentRequest;
import com.properties.payments.model.Payment;
import com.properties.payments.service.PaymentServiceImpl;

@SpringBootTest
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMakePayment() throws Exception {
        PaymentRequest request = new PaymentRequest();
       

        Payment mockPayment = new Payment();
        

        when(paymentService.payment(any(PaymentRequest.class))).thenReturn(mockPayment);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusMsg").value("Payment created successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(201));

        verify(paymentService, times(1)).payment(any(PaymentRequest.class));
    }

    @Test
    public void testListAllPayment() throws Exception {
        Payment mockPayment = new Payment();

        List<Payment> mockPaymentList = Collections.singletonList(mockPayment);

        when(paymentService.listAllPayment()).thenReturn(mockPaymentList);

        mockMvc.perform(MockMvcRequestBuilders.get("/payment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(mockPayment.getId()));

        verify(paymentService, times(1)).listAllPayment();
    }

    @Test
    public void testGetPaymentById() throws Exception {
        Payment mockPayment = new Payment();
        when(paymentService.getPaymentById(anyInt())).thenReturn(mockPayment);

        mockMvc.perform(MockMvcRequestBuilders.get("/payment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockPayment.getId()));

        verify(paymentService, times(1)).getPaymentById(anyInt());
    }

    @Test
    public void testDeletePayment() throws Exception {
        when(paymentService.deletePayment(anyInt())).thenReturn("SUCCESS");

        mockMvc.perform(MockMvcRequestBuilders.delete("/payment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusMsg").value("Payment deleted successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200));

        verify(paymentService, times(1)).deletePayment(anyInt());
    }

    @Test
    public void testUpdatePayment() throws Exception {
        PaymentRequest request = new PaymentRequest();
       
        Payment mockPayment = new Payment();

        when(paymentService.updatePayment(any(PaymentRequest.class), anyInt())).thenReturn(mockPayment);

        mockMvc.perform(MockMvcRequestBuilders.put("/payment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockPayment.getId()));

        verify(paymentService, times(1)).updatePayment(any(PaymentRequest.class), anyInt());
    }
}
