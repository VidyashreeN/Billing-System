package com.example.Billing.System.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.Billing.System.controller.InvoiceDTO;

public interface InvoiceService {

    void saveInvoice(InvoiceDTO invoice);
    List<InvoiceDTO> getInvoiceByUserId(Long userId);
    List<InvoiceDTO> getInvoiceById(Long id);
    Page<InvoiceDTO> getAllInvoices(int PageNumber, int PageSize, String sortBy, String sortDir);


}
