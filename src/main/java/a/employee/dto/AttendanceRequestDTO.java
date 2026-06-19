package a.employee.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequestDTO {
    @NotBlank(message = "Tên nhân viên không được để trống!")
    private String empId;
    @NotNull(message = "Ngày không được để trống!")
    @Min(value = 2, message = "Ngày không hợp lệ!")
    @Max(value = 8, message = "Ngày không hợp lệ!")
    private Integer day;
    @NotNull(message = "Số tiếng không được để trống!")
    @Min(value = 0, message = "Số tiếng không hợp lệ!")
    @Max(value = 24, message = "Số tiếng không hợp lệ!")
    private Integer hour;
    @PositiveOrZero(message = "OT không được dưới 0!")
    private Integer ot;
}
