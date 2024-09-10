package com.company.employeeleavemanagement.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String email;

    private String department;

    // Bu alan, kalan izin günlerini tutar. Yapıcıda varsayılan olarak 15 atanır.
    private int remainingLeaveDays;

    // Yapıcı
    public Employee() {
        this.remainingLeaveDays = 15; // Varsayılan değeri burada veriyoruz
    }
}
