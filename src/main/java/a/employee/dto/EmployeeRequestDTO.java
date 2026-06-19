package a.employee.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {
    @NotBlank(message = "Mã nhân viên không được để trống!")
    private String id;
    @NotBlank(message = "Tên nhân viên không được để trống!")
    private String name;
    @NotNull(message = "Không được để trống tuổi nhân viên!")
    @Positive(message = "Tuổi không được âm!")
    @Min(value = 18, message = "Nhân viên không đủ tuổi đi làm!")
    @Max(value = 61, message = "Nhân viên quá tuổi lao động!")
    private Integer age;
    @NotBlank(message = "Giới tính nhân viên không được để trống!")
    @Pattern(regexp = "^(Nam|Nữ)$", message = "Giới tính nhân viên không hợp lệ!")
    private String gender;
    @NotBlank(message = "Địa chỉ nhân viên không được để trống!")
    private String address;
    @NotBlank(message = "Số điện thoại nhân viên không được để trống!")
    @Pattern(regexp = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Số điện thoại nhân viên không họợp lệ!")
    private String phoneNum;
    @NotBlank(message = "Email nhân viên không được để trống!")
    @Email(message = "Email nhân viên sai định dạng!")
    private String email;
    @NotNull(message = "Lương nhân viên không được để trống!")
    @Positive(message = "Lương nhân viên phải lớn hơn 0!")
    private Double baseSalary;
    @NotNull(message = "Loại nhân viên không được để trống!")
    @Min(value = 1, message = "Loại không hợp lệ!")
    @Max(value = 2, message = "Loại không hợp lệ!")
    private Integer type;
}
