package a.employee.service;

import exception.CustomException;
import a.employee.model.*;
import a.employee.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private final GenericRepositoryImpl<Employee> repo;
    private final GenericRepositoryImpl<Department> dRepo;
    private final GenericRepositoryImpl<Position> pRepo;
    public EmployeeService(GenericRepositoryImpl<Employee> repo, GenericRepositoryImpl<Department> dRepo, GenericRepositoryImpl<Position> pRepo) {
        this.repo = repo;
        this.dRepo = dRepo;
        this.pRepo = pRepo;
    }
    public void registerEmployee(String id, String name, int age, String gender, String address, String phone, String email, double sal, int type) throws CustomException {
        if (repo.findById(id, Employee.class) != null) throw new CustomException("Trùng mã nhân viên!");
        if (!email.contains("@")) throw new CustomException("Email không hợp lệ!");
        if (sal < 0) throw new CustomException("Lương không được âm!");
        Employee emp = (type == 1) ? new FullTimeEmployee(id, name, age, gender, address, phone, email, sal) : new PartTimeEmployee(id, name, age, gender, address, phone, email, sal);
        repo.add(emp);
    }
    public void removeEmployee(String id) throws CustomException {
        if (repo.findById(id, Employee.class) == null) throw new CustomException("Nhân viên không tồn tại!");
        repo.delete(id, Employee.class);
    }
    public void updateEmployeeName(String id, String newName) throws CustomException {
        Employee e = (Employee) repo.findById(id, Employee.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại!");
        e.setName(newName);
        repo.update(e);
    }
    public Employee searchById(String id) {
        return (Employee) repo.findById(id, Employee.class);
    }
    public List<Employee> getAllEmployees() {
        return repo.findAllEmployees();
    }
    public List<Employee> getEmployeesSorted() { // sorted by salary
        List<Employee> list = repo.findAllEmployees();
        list.sort((e1, e2) -> Double.compare(e2.calculateSalary(), e1.calculateSalary()));
        return list;
    }
    public void assignDep(String empId, String depId) throws CustomException {
        Employee e = (Employee) repo.findById(empId, Employee.class);
        Department d = (Department) dRepo.findById(depId, Department.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại!");
        if (d == null) throw new CustomException("Phòng ban không tồn tại!");
        e.setDepartment(d);
        repo.update(e);
    }
    public void assignPos(String empId, String posId) throws CustomException {
        Employee e = (Employee) repo.findById(empId, Employee.class);
        Position p = (Position) pRepo.findById(posId, Position.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại!");
        if (p == null) throw new CustomException("Chức vụ không tồn tại!");
        e.getPositions().add(p);
        repo.update(e);
    }
}
