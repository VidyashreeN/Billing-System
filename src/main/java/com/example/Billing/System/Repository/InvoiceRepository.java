package com.example.Billing.System.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Billing.System.repository.entity.Invoice;



@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
List<Invoice> findByUserId(Long userId);

}
