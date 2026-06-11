package a.employee.service;

import a.employee.dto.DepartmentRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Department;
import a.employee.repository.DepartmentRepository;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    private final ValidationUtility valid;
    public DepartmentService(DepartmentRepository repo, ValidationUtility valid) {
        this.repo = repo;
        this.valid = valid;
    }
    public void createDep(DepartmentRequestDTO a) throws CustomException {
        if (valid.checkValid(a.getId())) throw new CustomException("ID phòng ban không được để trống!");
        if (valid.checkValid(a.getName())) throw new CustomException("Tên phòng ban không được để trống!");
        if (valid.checkValid(a.getMgr())) throw new CustomException("Tên quản lí phòng ban không được để trống!");
        Optional<Department> d = repo.findById(a.getId());
        if (d != null) throw new CustomException("ID phòng ban đã tồn tại!");
        repo.save(new Department(a.getId(), a.getName(), a.getMgr()));
    }
}
