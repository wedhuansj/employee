package a.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {
    @NotBlank(message = "Mã nhân viên không được để trống!")
    private String id;
    @NotBlank(message = "Tên nhân viên không được để trống!")
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phoneNum;
    private String email;
    private Double baseSalary;
    private int type;
}
