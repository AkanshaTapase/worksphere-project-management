package com.worksphere.worksphere.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_id", nullable = false, unique = true)
    private String empId;

    @NotBlank(message= "Name is required")
    @Size(max=100,message="Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message = "Department is required")

    private String department;

    private String designation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private AppUser user;
        
    
}
