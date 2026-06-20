package a.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionRequestDTO {
    @NotBlank(message = "ID không được để trống!")
    private String id;
    @NotBlank(message = "Tên không được để trống!")
    private String name;
    @NotNull(message = "Tiền phụ cấp không được để trống!")
    @PositiveOrZero(message = "Sai định dạng!")
    private Double alw;
}
