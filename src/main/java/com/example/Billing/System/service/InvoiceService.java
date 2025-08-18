package com.example.Billing.System.service;
import java.util.List;

import com.example.Billing.System.controller.InvoiceDTO;

public interface InvoiceService {

    void saveInvoice(InvoiceDTO invoice) throws Exception;
    List<InvoiceDTO> getInvoiceByUserId(Long userId);
    List<InvoiceDTO> getInvoiceById(Long id);
    List<InvoiceDTO> getAllInvoices();


}
