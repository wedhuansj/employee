package a.employee.controller;

import a.employee.model.Employee;
import a.employee.service.EmployeeService;
import a.employee.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {
    private final SalaryService salSrv;
    public SalaryController(SalaryService salSrv) { this.salSrv = salSrv; }
    public ResponseEntity<List<Map<String, Object>>> getSalaries() {
        List<Map<String, Object>> res = salSrv.getSalaries();
        return ResponseEntity.ok(res);
    }
}
