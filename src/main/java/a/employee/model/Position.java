package a.employee.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "position")
public class Position {
    @Id
    private String id;
    private String name;
    private double allowance;
    @ManyToMany(mappedBy = "positions")
    private List<Employee> employees = new ArrayList<>();
    public Position() {}
    public Position(double allowance, String name, String id) {
        this.allowance = allowance;
        this.name = name;
        this.id = id;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getAllowance() { return allowance; }
    public void setAllowance(double allowance) { this.allowance = allowance; }
    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
