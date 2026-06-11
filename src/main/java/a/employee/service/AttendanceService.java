package a.employee.service;

import a.employee.dto.AttendanceRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Attendance;
import a.employee.model.Employee;
import a.employee.repository.AttendanceRepository;
import a.employee.repository.EmployeeRepository;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository repo;
    private final EmployeeRepository eRepo;
    private final ValidationUtility valid;
    public AttendanceService(AttendanceRepository repo, EmployeeRepository eRepo, ValidationUtility valid) {
        this.repo = repo;
        this.eRepo = eRepo;
        this.valid = valid;
    }
    public void checkIn(AttendanceRequestDTO a) throws CustomException {
        if (valid.checkValid(a.getEmpId())) throw new CustomException("Mã nhân viên không được để trống!");
        if (a.getDay() < 2 || a.getDay() > 8) throw new CustomException("Sai định dạng ngày!");
        if (a.getHour() < 0 || a.getHour() > 24) throw new CustomException("Sai định dạng giờ!");
        if (a.getOt() < 0) throw new CustomException("OT không được dưới 0!");
        Optional<Employee> e = eRepo.findById(a.getEmpId());
        if (e.isEmpty()) throw new CustomException("Nhân viên không tồn tại");
        repo.save(new Attendance(a.getEmpId(), a.getDay(), a.getHour(), a.getOt()));
        e.get().setOt(e.get().getOt()+a.getOt());
        eRepo.save(e.get());
    }
}
