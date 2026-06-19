package a.employee.service;

import a.employee.dto.AssignDepDTO;
import a.employee.dto.AssignPosDTO;
import a.employee.dto.EmployeeRequestDTO;
import a.employee.dto.UpdateNameDTO;
import a.employee.exception.CustomException;
import a.employee.model.*;
import a.employee.repository.DepartmentRepository;
import a.employee.repository.EmployeeRepository;
import a.employee.repository.PositionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class EmployeeService {
    private final EmployeeRepository repo;
    private final DepartmentRepository dRepo;
    private final PositionRepository pRepo;
    public EmployeeService(EmployeeRepository repo, DepartmentRepository dRepo, PositionRepository pRepo) {
        this.repo = repo;
        this.dRepo = dRepo;
        this.pRepo = pRepo;
    }
    public void registerEmployee(EmployeeRequestDTO e) throws CustomException {
        if (repo.findById(e.getId()).isPresent()) throw new CustomException("Mã nhân viên đã tồn tại!");
        if (e.getType() == 1) repo.save(new FullTimeEmployee(e.getId(), e.getName(), e.getAge(), e.getGender(), e.getAddress(), e.getPhoneNum(), e.getEmail(), e.getBaseSalary()));
        if (e.getType() == 2) repo.save(new PartTimeEmployee(e.getId(), e.getName(), e.getAge(), e.getGender(), e.getAddress(), e.getPhoneNum(), e.getEmail(), e.getBaseSalary()));
    }
    public void removeEmployee(@NotBlank(message = "ID nhân viên không được để trống!") String id) throws CustomException {
        Optional<Employee> e = repo.findById(id);
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        repo.delete(e.get());
    }
    public void updateEmployeeName(UpdateNameDTO a) throws CustomException {
        Optional<Employee> optionalEmployee = repo.findById(a.getId());
        if (optionalEmployee.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        Employee employee = optionalEmployee.get();
        employee.setName(a.getName());
        repo.save(employee);
    }
    public Employee searchById(@NotBlank(message = "ID nhân viên không được để trống!") String id) throws CustomException {
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
    public void assignDep(AssignDepDTO a) throws CustomException {
        Optional <Employee> e = repo.findById(a.getEmpId());
        Optional <Department> d = dRepo.findById(a.getDepId());
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        if (d.isEmpty()) throw new CustomException("Phòng ban không tồn tại!");
        e.get().setDepartment(d.get());
        repo.save(e.get());
    }
    public void assignPos(AssignPosDTO a) throws CustomException {
        Optional<Employee> e = repo.findById(a.getEmpId());
        Optional<Position> p = pRepo.findById(a.getPosId());
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại!");
        if (p.isEmpty()) throw new CustomException("Chức vụ không tồn tại!");
        e.get().getPositions().add(p.get());
        repo.save(e.get());
    }
}
