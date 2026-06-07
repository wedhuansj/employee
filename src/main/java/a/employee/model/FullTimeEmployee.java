package a.employee.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FULLTIME")
public class FullTimeEmployee extends Employee {
    public FullTimeEmployee() {}
    public FullTimeEmployee(String id, String name, int age, String gender, String address, String phoneNum, String email, Double baseSalary) {
        super(id, name, age, gender, address, phoneNum, email, baseSalary);
    }
    @Override
    public Double calculateSalary() {
        double totalAllowance = baseAllowance;
        for (Position p : positions)
            totalAllowance += p.getAllowance();
        return baseSalary + totalAllowance + (ot * 150000) - tax;
    }
    @Override
    public void displayInfo() {
        System.out.println("FT-ID: " + id + " | Tên: " + name + " | Phòng: " + (department != null ? department.getName() : "Chưa có") + " | Lương: " + calculateSalary());
    }
}
