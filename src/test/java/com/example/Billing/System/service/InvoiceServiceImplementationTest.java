package com.example.Billing.System.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Billing.System.model.InvoiceDTO;
import com.example.Billing.System.repository.InvoiceRepository;
import com.example.Billing.System.repository.UserRepository;
import com.example.Billing.System.repository.entity.Invoice;

@ExtendWith(SpringExtension.class)
public class InvoiceServiceImplementationTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    InvoiceServiceImplementation invoiceService;

/* 
    @BeforeAll
    v oid setUp() {
    }

   /*  @AfterEach
    void tearDown() {
    } */

    @Test
    void testsaveInvoice() {

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        Invoice invoice = new Invoice();
         invoiceDTO.setUserId(42L);

        //stub mapping
        when(modelMapper.map(invoiceDTO, Invoice.class)).thenReturn(invoice);
        when (userRepository.findById(42L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> invoiceService.saveInvoice(invoiceDTO)); 

        assertEquals("User not found with id: 42", exception.getMessage());
    
    }

    @Test
    void getInvoiceByUserId() {
    }

    @Test
    void getInvoiceById() {
    }

    @Test
    void getAllInvoices(){

    }
}