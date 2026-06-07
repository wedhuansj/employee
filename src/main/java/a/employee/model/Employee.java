package a.employee.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table (name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "emp_type")
public abstract class Employee extends Person {
    @Column(name="phone_num")
    protected String phoneNum;
    protected String email;
    @ManyToOne()
    @JoinColumn(name="dep_id")
    protected Department department;
    @ManyToMany()
    @JoinTable(
            name = "employee_position",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "position_id", referencedColumnName = "id")
    )
    protected List<Position> positions = new ArrayList<>();
    @Column(name="position")
    private String positionName;
    @Column(name = "allowance")
    protected Double baseAllowance = 0.0;
    protected Double ot = 0.0;
    protected Double tax = 0.0;
    @Column(name = "base_salary")
    protected Double baseSalary = 0.0;
    public Employee() {}
    public Employee(String id, String name, int age, String gender, String address, String phoneNum, String email, Double baseSalary) {
        super(id, name, age, gender, address);
        this.phoneNum = phoneNum;
        this.email = email;
        this.baseSalary = baseSalary;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public List<Position> getPositions() { return positions; }
    public void setPositions(List<Position> positions) { this.positions = positions; }
    public String getPositionName() { return positionName; }
    public void setPositionName(String positionName) { this.positionName = positionName; }
    public Double getOt() { return ot; }
    public void setOt(Double ot) { this.ot = ot; }
    public Double getTax() { return tax; }
    public void setTax(Double tax) { this.tax = tax; }
    public Double getBaseAllowance() { return baseAllowance; }
    public void setBaseAllowance(Double baseAllowance) { this.baseAllowance = baseAllowance;}
    public Double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(Double baseSalary) { this.baseSalary = baseSalary; }
    public abstract Double calculateSalary();
}