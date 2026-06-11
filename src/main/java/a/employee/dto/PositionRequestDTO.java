package a.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionRequestDTO {
    private String id, name;
    private double alw;
}
