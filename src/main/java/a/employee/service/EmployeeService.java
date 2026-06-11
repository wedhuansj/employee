package a.employee.service;

import a.employee.dto.EmployeeRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.*;
import a.employee.repository.GenericRepositoryImpl;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private final GenericRepositoryImpl<Employee> repo;
    private final GenericRepositoryImpl<Department> dRepo;
    private final GenericRepositoryImpl<Position> pRepo;
    private final ValidationUtility valid;
    public EmployeeService(GenericRepositoryImpl<Employee> repo, GenericRepositoryImpl<Department> dRepo, GenericRepositoryImpl<Position> pRepo, ValidationUtility valid) {
        this.repo = repo;
        this.dRepo = dRepo;
        this.pRepo = pRepo;
        this.valid = valid;
    }
    public void registerEmployee(EmployeeRequestDTO e) throws CustomException {
        if (valid.checkValid(e.getId())) throw new CustomException("Mã nhân viên không được để trống!");
        if (valid.checkValid(e.getName())) throw new CustomException("Tên không được để trống!");
        if (valid.checkValid(e.getAddress())) throw new CustomException("Địa chỉ nhân viên không được để trống!");
        if (valid.checkValid(e.getPhoneNum())) throw new CustomException("Số điện thoại nhân viên không được để trống!");
        if (valid.checkValid(e.getEmail())) throw new CustomException("Không được để trống email!");
        if (valid.checkSal(e.getBaseSalary())) throw new CustomException("Lương không được dưới 0 hoặc bằng 0!");
        if (e.getAge() > 61) throw new CustomException("Nhân viên quá tuổi lao động!");
        if (e.getAge() < 18) throw new CustomException("Nhân viên không đủ tuổi đi làm!");
        if (!valid.checkNum(e.getAge())) throw new CustomException("Nhập sai dữ liệu tuổi nhân viên!");
        if (!valid.checkGen(e.getGender())) throw new CustomException("Giới tính không hợp lệ!");
        if (!valid.checkPhone(e.getPhoneNum())) throw new CustomException("Định dạng số điện thoại không hợp lệ!");
        if (!valid.checkEmail(e.getEmail())) throw new CustomException("Sai định dạng email!");
        if (!valid.checkType(e.getType())) throw new CustomException("Loại không hợp lệ!");
        if (repo.findById(e.getId(), Employee.class) != null) throw new CustomException("Mã nhân viên đã tồn tại!");
        if (e.getType() == 1) repo.add(new FullTimeEmployee(e.getId(), e.getName(), e.getAge(), e.getGender(), e.getAddress(), e.getPhoneNum(), e.getEmail(), e.getBaseSalary()));
        if (e.getType() == 2) repo.add(new PartTimeEmployee(e.getId(), e.getName(), e.getAge(), e.getGender(), e.getAddress(), e.getPhoneNum(), e.getEmail(), e.getBaseSalary()));
    }
    public void removeEmployee(String id) throws CustomException {
        if (valid.checkValid(id)) throw new CustomException("ID nhân viên không được để trống!");
        if (repo.findById(id, Employee.class) == null) throw new CustomException("Nhân viên không tồn tại!");
        repo.delete(id, Employee.class);
    }
    public void updateEmployeeName(String id, String newName) throws CustomException {
        if (valid.checkValid(id)) throw new CustomException("ID nhân viên không được để trống!");
        if (valid.checkValid(newName)) throw new CustomException("Tên nhân viên không được để trống!");
        Employee e = (Employee) repo.findById(id, Employee.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại!");
        e.setName(newName);
        repo.update(e);
    }
    public Employee searchById(String id) throws CustomException {
        if (valid.checkValid(id)) throw new CustomException("ID nhân viên không được để trống");
        Employee e = repo.findById(id, Employee.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại!");
        return e;
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
        if (valid.checkValid(empId)) throw new CustomException("ID nhân viên không được để trống!");
        if (valid.checkValid(depId)) throw new CustomException("ID phòng ban không được để trống!");
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
