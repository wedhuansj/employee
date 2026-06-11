package a.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phoneNum;
    private String email;
    private Double baseSalary;
    private int type;
}
