package com.example.Billing.System.Repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String companyName;
    private BigDecimal amount;
    private LocalDate date;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;

    @Override
    public String toString() {
        return "Invoice{" +
                ", companyName='" + companyName + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }   
}
