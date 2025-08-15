package com.example.Billing.System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Billing.System.Repository.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // JpaRepository provides methods like save, findById, findAll, deleteById, etc.
    // No additional methods are defined here, but you can add custom query methods if needed.
    // Additional query methods can be defined here if needed

}
