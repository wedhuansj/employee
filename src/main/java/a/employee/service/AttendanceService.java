package a.employee.service;

import exception.CustomException;
import a.employee.model.Attendance;
import a.employee.model.Employee;
import a.employee.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final GenericRepositoryImpl<Attendance> repo;
    private final GenericRepositoryImpl<Employee> eRepo;
    public AttendanceService(GenericRepositoryImpl<Attendance> repo, GenericRepositoryImpl<Employee> eRepo) {
        this.repo = repo;
        this.eRepo = eRepo;
    }
    public void checkIn(String empId, int day, int hour, int ot) throws CustomException {
        Employee e = (Employee) eRepo.findById(empId, Employee.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại");
        repo.add(new Attendance(empId, day, hour, ot));
        e.setOt(e.getOt()+ot);
        eRepo.update(e);
    }
}
