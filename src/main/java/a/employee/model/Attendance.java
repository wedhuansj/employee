package a.employee.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

class AttendanceId implements Serializable {
    private String employeeId;
    private int day;
    public AttendanceId() {}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceId that = (AttendanceId) o;
        return day == that.day && Objects.equals(employeeId, that.employeeId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(employeeId, day);
    }
}
@Entity
@Table(name = "attendance")
@IdClass(AttendanceId.class)
public class Attendance {
    @Id
    @Column(name= "employee_id")
    private String employeeId;
    @Id
    private int day;
    private int hour;
    private int ot;
    public Attendance() {}
    public Attendance(String employeeId, int day, int hour, int ot) {
        this.employeeId = employeeId;
        this.day = day;
        this.hour = hour;
        this.ot = ot;
    }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }
    public int getOt() { return ot; }
    public void setOt(int ot) { this.ot = ot; }
}