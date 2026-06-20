package a.employee.service;

import a.employee.dto.AttendanceRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Attendance;
import a.employee.model.Employee;
import a.employee.repository.AttendanceRepository;
import a.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository repo;
    private final EmployeeRepository eRepo;
    public AttendanceService(AttendanceRepository repo, EmployeeRepository eRepo) {
        this.repo = repo;
        this.eRepo = eRepo;
    }
    public void checkIn(AttendanceRequestDTO a) throws CustomException {
        Optional<Employee> e = eRepo.findById(a.getEmpId());
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại");
        repo.save(new Attendance(a.getEmpId(), a.getDay(), a.getHour(), a.getOt()));
        e.get().setOt(e.get().getOt()+a.getOt());
        eRepo.save(e.get());
    }
}
