package a.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDTO {
    @NotBlank(message = "ID phòng ban không được để trống!")
    private String id;
    @NotBlank(message = "Tên phòng ban không được để trống!")
    private String name;
    @NotBlank(message = "Tên quản lí phòng ban không được để trống!")
    private String mgr;
}
