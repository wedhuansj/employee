package a.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequestDTO {
    private String empId;
    private int day;
    private int hour;
    private int ot;
}
