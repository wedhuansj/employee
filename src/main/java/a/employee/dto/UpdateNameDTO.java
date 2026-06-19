package a.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNameDTO {
    @NotBlank(message = "ID nhân viên không được để trống!")
    private String id;
    @NotBlank(message = "Tên nhân viên không được để trống!")
    private String name;
}
