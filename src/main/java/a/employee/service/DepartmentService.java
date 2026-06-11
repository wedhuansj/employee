package a.employee.service;

import a.employee.dto.DepartmentRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Department;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;
import a.employee.repository.GenericRepositoryImpl;
@Service
public class DepartmentService {
    private final GenericRepositoryImpl<Department> repo;
    private final ValidationUtility valid;
    public DepartmentService(GenericRepositoryImpl<Department> repo, ValidationUtility valid) {
        this.repo = repo;
        this.valid = valid;
    }
    public void createDep(DepartmentRequestDTO a) throws CustomException {
        if (valid.checkValid(a.getId())) throw new CustomException("ID phòng ban không được để trống!");
        if (valid.checkValid(a.getName())) throw new CustomException("Tên phòng ban không được để trống!");
        if (valid.checkValid(a.getMgr())) throw new CustomException("Tên quản lí phòng ban không được để trống!");
        Department d = repo.findById(a.getId(), Department.class);
        if (d != null) throw new CustomException("ID phòng ban đã tồn tại!");
        repo.add(new Department(a.getId(), a.getName(), a.getMgr()));
    }
}
