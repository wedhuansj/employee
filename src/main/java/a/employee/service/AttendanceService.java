package a.employee.service;

import a.employee.dto.AttendanceRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Attendance;
import a.employee.model.Employee;
import a.employee.repository.GenericRepositoryImpl;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private final GenericRepositoryImpl<Attendance> repo;
    private final GenericRepositoryImpl<Employee> eRepo;
    private final ValidationUtility valid;
    public AttendanceService(GenericRepositoryImpl<Attendance> repo, GenericRepositoryImpl<Employee> eRepo, ValidationUtility valid) {
        this.repo = repo;
        this.eRepo = eRepo;
        this.valid = valid;
    }
    public void checkIn(AttendanceRequestDTO a) throws CustomException {
        if (valid.checkValid(a.getEmpId())) throw new CustomException("Mã nhân viên không được để trống!");
        if (a.getDay() < 2 || a.getDay() > 8) throw new CustomException("Sai định dạng ngày!");
        if (a.getHour() < 0 || a.getHour() > 24) throw new CustomException("Sai định dạng giờ!");
        if (a.getOt() < 0) throw new CustomException("OT không được dưới 0!");
        Employee e = (Employee) eRepo.findById(a.getEmpId(), Employee.class);
        if (e == null) throw new CustomException("Nhân viên không tồn tại");
        repo.add(new Attendance(a.getEmpId(), a.getDay(), a.getHour(), a.getOt()));
        e.setOt(e.getOt()+a.getOt());
        eRepo.update(e);
    }
}
