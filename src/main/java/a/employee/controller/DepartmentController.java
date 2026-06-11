package a.employee.controller;

import a.employee.dto.DepartmentRequestDTO;
import a.employee.exception.CustomException;
import a.employee.service.DepartmentService;
import a.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService depSrv;
    private final EmployeeService empSrv;
    public DepartmentController(DepartmentService depSrv, EmployeeService empSrv) {
        this.depSrv = depSrv;
        this.empSrv = empSrv;
    }
    @PostMapping
    public ResponseEntity<String> createDep(@RequestBody DepartmentRequestDTO a) {
        try {
            depSrv.createDep(a);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
}
