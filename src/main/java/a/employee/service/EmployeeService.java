package a.employee.service;

import a.employee.dto.EmployeeRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.*;
import a.employee.repository.DepartmentRepository;
import a.employee.repository.EmployeeRepository;
import a.employee.repository.PositionRepository;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    private final DepartmentRepository dRepo;
    private final PositionRepository pRepo;
    private final ValidationUtility valid;
    public EmployeeService(EmployeeRepository repo, DepartmentRepository dRepo, PositionRepository pRepo, ValidationUtility valid) {
        this.repo = repo;
        this.dRepo = dRepo;
        this.pRepo = pRepo;
        this.valid = valid;
    }
    public void registerEmployee(EmployeeRequestDTO e) throws CustomException {
        /*
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
        if (!valid.checkNum(e.getBaseSalary())) throw new CustomException("Sai định dạng lương!");

        if (repo.findById(e.getId()).isPresent()) throw new CustomException("Mã nhân viên đã tồn tại!");*/
        if (e.getType() == 1) repo.save(new FullTimeEmployee(e.getId(), e.getName(), e.getAge(), e.getGender(), e.getAddress(), e.getPhoneNum(), e.getEmail(), e.getBaseSalary()));
        if (e.getType() == 2) repo.save(new PartTimeEmployee(e.getId(), e.getName(), e.getAge(), e.getGender(), e.getAddress(), e.getPhoneNum(), e.getEmail(), e.getBaseSalary()));

    }
    public void removeEmployee(String id) throws CustomException {
        if (valid.checkValid(id)) throw new CustomException("ID nhân viên không được để trống!");
        if (repo.findById(id).isPresent()) throw new CustomException("Nhân viên không tồn tại!");
        repo.delete(repo.findById(id).get());
    }
    public void updateEmployeeName(String id, String newName) throws CustomException {
        if (valid.checkValid(id)) throw new CustomException("ID nhân viên không được để trống!");
        if (valid.checkValid(newName)) throw new CustomException("Tên nhân viên không được để trống!");
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        Employee employee = optionalEmployee.get();
        employee.setName(newName);
        repo.save(employee);
    }
    public Employee searchById(String id) throws CustomException {
        if (valid.checkValid(id)) throw new CustomException("ID nhân viên không được để trống");
        Optional<Employee> e = repo.findById(id);
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        return e.get();
    }
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }
    public List<Employee> getEmployeesSorted() { // sorted by salary
        List<Employee> list = repo.findAll();
        list.sort((e1, e2) -> Double.compare(e2.calculateSalary(), e1.calculateSalary()));
        return list;
    }
    public void assignDep(String empId, String depId) throws CustomException {
        if (valid.checkValid(empId)) throw new CustomException("ID nhân viên không được để trống!");
        if (valid.checkValid(depId)) throw new CustomException("ID phòng ban không được để trống!");
        Optional <Employee> e = repo.findById(empId);
        Optional <Department> d = dRepo.findById(depId);
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        if (d.isEmpty()) throw new CustomException("Phòng ban không tồn tại!");
        e.get().setDepartment(d.get());
        repo.save(e.get());
    }
    public void assignPos(String empId, String posId) throws CustomException {
        Optional<Employee> e = repo.findById(empId);
        Optional<Position> p = pRepo.findById(posId);
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        if (p.isEmpty()) throw new CustomException("Chức vụ không tồn tại!");
        e.get().getPositions().add(p.get());
        repo.save(e.get());
    }
}
