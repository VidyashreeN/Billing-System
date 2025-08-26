package com.example.Billing.System.controller;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.Billing.System.repository.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long id;

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    
    private BigDecimal amount;
    private Long userId;

}
