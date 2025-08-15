package com.example.Billing.System.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Billing.System.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




// 


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    // Injecting the InvoiceService to handle invoice operations
    private final InvoiceService invoiceService;

    // Constructor injection for InvoiceService
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping()
    public ResponseEntity<String> createInvoice(@RequestBody InvoiceDTO invoice) throws Exception {
        invoiceService.saveInvoice(invoice);
        return ResponseEntity.ok("Invoice created successfully with details: " );
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceDTO inv = invoiceService.getInvoiceById(id);
        return new ResponseEntity<> (inv, HttpStatus.OK)    ;
    }

    public ResponseEntity<String> getAllInvoices() {
        
        return ResponseEntity.ok("Invoice retrieved successfully");
    }

}
