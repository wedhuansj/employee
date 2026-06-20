package a.employee.service;

import a.employee.dto.DepartmentRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Department;
import a.employee.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    public DepartmentService(DepartmentRepository repo) { this.repo = repo; }
    public void createDep(DepartmentRequestDTO a) throws CustomException {
        Optional<Department> d = repo.findById(a.getId());
        if (d.isPresent()) throw new CustomException("ID phòng ban đã tồn tại!");
        repo.save(new Department(a.getId(), a.getName(), a.getMgr()));
    }
}
