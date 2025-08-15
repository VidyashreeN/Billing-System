package com.example.Billing.System.service;
import com.example.Billing.System.controller.InvoiceDTO;

public interface InvoiceService {

    void saveInvoice(InvoiceDTO invoice) throws Exception;
    InvoiceDTO getInvoiceById(Long id);


}
