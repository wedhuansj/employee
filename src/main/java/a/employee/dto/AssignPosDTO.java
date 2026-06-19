package a.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignPosDTO {
    @NotBlank(message = "ID nhân viên không được để trống!")
    private String empId;
    @NotBlank(message = "ID chức vụ không được để trống!")
    private String posId;
}
