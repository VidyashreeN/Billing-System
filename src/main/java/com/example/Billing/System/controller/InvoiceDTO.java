package com.example.Billing.System.controller;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.Billing.System.Repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long id;
    private String companyName;
    private LocalDate date;
    private BigDecimal amount;
    private User userIdr;
}
