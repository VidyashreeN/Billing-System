package com.example.Billing.System.controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Billing.System.configuration.AppConstants;
import com.example.Billing.System.service.InvoiceService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/v1/invoice")
public class InvoiceController {

    // Injecting the InvoiceService to handle invoice operations
    private final InvoiceService invoiceService;

    // Constructor injection for InvoiceService
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping()
    public ResponseEntity<String> createInvoice(@RequestBody @Valid InvoiceDTO invoice) {
        invoiceService.saveInvoice(invoice);
        return ResponseEntity.ok("Invoice created successfully with details: " );
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoiceByUserId(@PathVariable Long userId) {
        List<InvoiceDTO> inv = invoiceService.getInvoiceByUserId(userId);
        return new ResponseEntity<>(inv, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<InvoiceDTO>> getAllInvoices(@RequestParam(name= "PageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)int pageNumber, 
    @RequestParam (name = "PageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
                                                             @RequestParam (name = "SortBy", defaultValue = AppConstants.SORT_INVOICE_BY, required = false) String sortBy,
                                                             @RequestParam (name = "SortOrder", defaultValue = AppConstants.SORT_ORDER  )  String sortOrder) {
        Page<InvoiceDTO> invoices = invoiceService.getAllInvoices( pageNumber ,  pageSize,  sortBy,  sortOrder );

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

}
