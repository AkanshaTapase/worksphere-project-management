
package com.worksphere.worksphere.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@lambok.getter
@lambok.setter
public class AdminEmployeeCreateRequestDTO {

    @NotBlank
    private String empId;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String department;

    @NotBlank
    private String designation;

    
}
