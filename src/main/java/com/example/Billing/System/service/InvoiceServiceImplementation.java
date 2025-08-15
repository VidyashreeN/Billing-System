package com.example.Billing.System.service;
import com.example.Billing.System.service.InvoiceService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Billing.System.Repository.InvoiceRepository;
import com.example.Billing.System.Repository.entity.Invoice;
import com.example.Billing.System.controller.InvoiceDTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImplementation implements InvoiceService {

    // Injecting the ModelMapper to map between DTO and Entity
    private final ModelMapper modelMapper;
    //Inject repository
    private final InvoiceRepository invoiceRepository;

    // Constructor injection for InvoiceRepository
    public InvoiceServiceImplementation(InvoiceRepository invoiceRepository) {
        this.modelMapper = new ModelMapper();
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void saveInvoice(InvoiceDTO invoice) throws Exception {
        // Save the invoice using the repository
        try {
        
            // Convert InvoiceDTO to Invoice entity
            Invoice invoiceEntity = modelMapper.map(invoice, Invoice.class);
            
            //print the invoice entity for debugging
            System.out.println("Invoice Entity: " + invoiceEntity.toString());

            invoiceRepository.save(invoiceEntity);
            
        }
        catch(Exception e) {
            throw new Exception("Error saving invoice: " + e.getMessage());
        }
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {

        Invoice allInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        InvoiceDTO finalInvoice = modelMapper.map(allInvoice, InvoiceDTO.class);

        return finalInvoice;
    }

}
