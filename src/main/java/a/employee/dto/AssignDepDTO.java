package a.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignDepDTO {
    @NotBlank(message = "ID nhân viên không được để trống!")
    private String empId;
    @NotBlank(message = "ID phòng ban không được để trống!")
    private String depId;
}
