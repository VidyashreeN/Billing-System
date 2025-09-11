package com.example.Billing.System.service;
import com.example.Billing.System.model.InvoiceDTO;
import com.example.Billing.System.repository.InvoiceRepository;
import com.example.Billing.System.repository.UserRepository;
import com.example.Billing.System.repository.entity.Invoice;
import com.example.Billing.System.repository.entity.User;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
// import 

@Service
public class InvoiceServiceImplementation implements InvoiceService {

    // Injecting the ModelMapper to map between DTO and Entity
    private final ModelMapper modelMapper;
    //Inject repository
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    // Constructor injection for InvoiceRepository
    public InvoiceServiceImplementation(InvoiceRepository invoiceRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveInvoice(InvoiceDTO invoice) {
        
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

    @Override
    public List<InvoiceDTO> getInvoiceByUserId(Long userId) {
        
        List<Invoice> invoices = invoiceRepository.findByUserId(userId);
        if (invoices.isEmpty()) {
            throw new EntityNotFoundException("No invoices found for user with id: " + userId);   
        }
        List<InvoiceDTO> invoiceDTOs = invoices.stream()
            .map(inv -> modelMapper.map(inv, InvoiceDTO.class))
            .toList();
        invoiceDTOs.forEach(System.out::println);
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
    public Page<InvoiceDTO> getAllInvoices(int pageNumber, int pageSize, String sortBy, String sortOrder){
        
        List<Invoice> allInvoices = invoiceRepository.findAll();
        if (allInvoices.isEmpty()) {
            throw new RuntimeException("No invoices found");
         }

        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageDetails = PageRequest.of(pageNumber, pageSize, sort);

        Page<Invoice> invoicePage = invoiceRepository.findAll(pageDetails);
        Page<InvoiceDTO> allInvoiceDTO = invoicePage.map(inv->modelMapper.map(inv, InvoiceDTO.class));
        
        return allInvoiceDTO;
   
    }
}
