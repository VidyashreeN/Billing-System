package com.example.Billing.System.service;
import com.example.Billing.System.Repository.InvoiceRepository;
import com.example.Billing.System.Repository.UserRepository;
import com.example.Billing.System.Repository.entity.Invoice;
import com.example.Billing.System.Repository.entity.User;
import com.example.Billing.System.controller.InvoiceDTO;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImplementation implements InvoiceService {

    // Injecting the ModelMapper to map between DTO and Entity
    private final ModelMapper modelMapper;
    //Inject repository
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    // Constructor injection for InvoiceRepository
    public InvoiceServiceImplementation(InvoiceRepository invoiceRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.modelMapper = new ModelMapper();
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveInvoice(InvoiceDTO invoice) throws Exception {
        // Save the invoice using the repository
        try {
        
            // Convert InvoiceDTO to Invoice entity
            Invoice invoiceEntity = modelMapper.map(invoice, Invoice.class);
            
            User user = userRepository.findById(invoice.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + invoice.getUserId()));
            System.out.println("User found: " + user.getName());
            System.out.println("User ID: " + user);
            
            invoiceEntity.setUser(user);
            invoiceRepository.save(invoiceEntity);
            //print the invoice entity for debugging
            System.out.println("Invoice Entity: " + invoiceEntity.toString());
        }
        catch(Exception e) {
            throw new Exception("Error saving invoice: " + e.getMessage());
        }
    }

    @Override
    public List<InvoiceDTO> getInvoiceByUserId(Long userId) {
        
        List<Invoice> invoices = invoiceRepository.findByUserId(userId);
        if (invoices.isEmpty()) {
            throw new RuntimeException("No invoices found for user with id: " + userId);
        }
        List<InvoiceDTO> invoiceDTOs = invoices.stream()
            .map(inv -> modelMapper.map(inv, InvoiceDTO.class))
            .toList();
        return invoiceDTOs;
    }


    @Override
    public List<InvoiceDTO> getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);
        return List.of(invoiceDTO); // returns a singleton list

}

    @Override
    public List<InvoiceDTO> getAllInvoices(){
        
        List<Invoice> allInvoices = invoiceRepository.findAll();
        if (allInvoices.isEmpty()) {
            throw new RuntimeException("No invoices found");
         }
        return allInvoices.stream()
                .map(inv -> modelMapper.map(inv, InvoiceDTO.class))
                .toList();
   
    }
}
