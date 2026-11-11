
package com.worksphere.worksphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;

    @JsonProperty("emp_id")
    private String empId;
    private String name;
    private String email;
    private String department;
    private String designation;
}
