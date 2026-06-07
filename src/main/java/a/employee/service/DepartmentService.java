package a.employee.service;

import a.employee.model.Department;
import org.springframework.stereotype.Service;
import a.employee.repository.GenericRepositoryImpl;
@Service
public class DepartmentService {
    private final GenericRepositoryImpl<Department> repo;
    public DepartmentService(GenericRepositoryImpl<Department> repo) { this.repo = repo; }
    public void createDep(String id, String name, String mgr) {
        repo.add(new Department(id, name, mgr));
    }
}
