package a.employee.controller;

import exception.CustomException;
import a.employee.model.Employee;
import a.employee.service.AttendanceService;
import a.employee.service.DepartmentService;
import a.employee.service.EmployeeService;
import a.employee.service.PositionService;
import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {
    private final EmployeeService empSrv;
    private final DepartmentService depSrv;
    private final PositionService posSrv;
    private final AttendanceService attSrv;
    public EmployeeController(EmployeeService empSrv, DepartmentService depSrv, PositionService posSrv, AttendanceService attSrv) {
        this.empSrv = empSrv;
        this.depSrv = depSrv;
        this.posSrv = posSrv;
        this.attSrv = attSrv;
    }
    @PostMapping("/employees")
    public ResponseEntity<String> addEmployee(@RequestParam @NotBlank String id, @RequestParam @NotBlank String name, @RequestParam @Min(18) @Max(65) int age, @RequestParam @NotBlank String gen, @RequestParam @NotBlank String addr, @RequestParam @NotBlank String phone, @RequestParam @Email String email, @RequestParam @Positive double sal, @RequestParam @Min(1) @Max(2) int type) {
        try {
            empSrv.registerEmployee(id, name, age, gen, addr, phone, email, sal, type);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable @NotBlank String id) {
        try {
            empSrv.removeEmployee(id);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PutMapping("/employees/{id}/name")
    public ResponseEntity<String> updateName(@PathVariable @NotBlank String id, @RequestParam @NotBlank String name) {
        try {
            empSrv.updateEmployeeName(id, name);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getById(@PathVariable @NotBlank String id) {
        Employee e = empSrv.searchById(id);
        return e != null ? ResponseEntity.ok(e) : ResponseEntity.status(404).body("not found");
    }
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(empSrv.getAllEmployees());
    }
    @GetMapping("/employees/sorted")
    public ResponseEntity<List<Employee>> getAllSorted() {
        return ResponseEntity.ok(empSrv.getEmployeesSorted());
    }
    @PostMapping("/departments")
    public ResponseEntity<String> createDep(@RequestParam @NotBlank String id, @RequestParam @NotBlank String name, @RequestParam @NotBlank String mgr) {
        depSrv.createDep(id, name, mgr);
        return ResponseEntity.ok("success");
    }
    @PutMapping("/employees/assign-department")
    public ResponseEntity<String> assignDep(@RequestParam @NotBlank String empId, @RequestParam @NotBlank String targetId) {
        try {
            empSrv.assignDep(empId, targetId);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PostMapping("/positions")
    public ResponseEntity<String> createPos(@RequestParam @NotBlank String id, @RequestParam @NotBlank String name, @RequestParam @Positive double alw) {
        posSrv.createPos(id, name, alw);
        return ResponseEntity.ok("success");
    }
    @PutMapping("/employees/assign-position")
    public ResponseEntity<String> assignPos(@RequestParam @NotBlank String empId, @RequestParam @NotBlank String targetId) {
        try {
            empSrv.assignPos(empId, targetId);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PostMapping("/attendance")
    public ResponseEntity<String> checkIn(@RequestParam @NotBlank String empId, @RequestParam @Min(1) @Max(31) int day, @RequestParam @Min(0) @Max(24) int hour, @RequestParam @PositiveOrZero int ot) {
        try {
            attSrv.checkIn(empId, day, hour, ot);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @GetMapping("/salary")
    public ResponseEntity<List<Map<String, Object>>> getSalaries() {
        List<Employee> list = empSrv.getAllEmployees();
        List<Map<String, Object>> res = new ArrayList<>();
        for (Employee e : list) {
            Map<String, Object> m = new HashMap<>();
            m.put("name", e.getName());
            m.put("salary", e.calculateSalary());
            res.add(m);
        }
        return ResponseEntity.ok(res);
    }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        List<Employee> list = empSrv.getAllEmployees();
        double total = 0;
        Employee maxEmp = null;
        for (Employee e : list) {
            double s = e.calculateSalary();
            total  += s;
            if (maxEmp == null || s > maxEmp.calculateSalary()) maxEmp = e;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("totalBudget", total);
        res.put("highestPaidEmployee", maxEmp);
        return ResponseEntity.ok(res);
    }
}
