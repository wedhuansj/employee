package a.employee.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PARTTIME")
public class PartTimeEmployee extends Employee {
    public PartTimeEmployee() {}

    public PartTimeEmployee(String id, String name, int age, String gender, String address, String phoneNum, String email, Double hourlyRate) {
        super(id, name, age, gender, address, phoneNum, email, hourlyRate);
    }
    @Override
    public Double calculateSalary() {
        double totalAllowance = baseAllowance;
        for (Position p : positions) {
            totalAllowance += p.getAllowance();
        }
        return (baseSalary*ot) + totalAllowance-tax;
    }
    @Override
    public void displayInfo() {
        System.out.println("PT-ID: " + id + " | Tên: " + name + " | Phòng : " + (department != null ? department.getName() : "Chưa có") + " | Lương: " + calculateSalary());
    }
}
