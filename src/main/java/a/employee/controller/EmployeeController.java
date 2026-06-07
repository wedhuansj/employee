package a.employee.controller;

import exception.CustomException;
import a.employee.model.Employee;
import a.employee.service.AttendanceService;
import a.employee.service.DepartmentService;
import a.employee.service.EmployeeService;
import a.employee.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
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
    public ResponseEntity<String> addEmployee(@RequestParam String id, @RequestParam String name, @RequestParam int age, @RequestParam String gen, @RequestParam String addr, @RequestParam String phone, @RequestParam String email, @RequestParam double sal, @RequestParam int type) {
        try {
            empSrv.registerEmployee(id, name, age, gen, addr, phone, email, sal, type);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            empSrv.removeEmployee(id);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PutMapping("/employees/{id}/name")
    public ResponseEntity<String> updateName(@PathVariable String id, @RequestParam String name) {
        try {
            empSrv.updateEmployeeName(id, name);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
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
    public ResponseEntity<String> createDep(@RequestParam String id, @RequestParam String name, @RequestParam String mgr) {
        depSrv.createDep(id, name, mgr);
        return ResponseEntity.ok("success");
    }
    @PutMapping("/employees/assign-department")
    public ResponseEntity<String> assignDep(@RequestParam String empId, @RequestParam String targetId) {
        try {
            empSrv.assignDep(empId, targetId);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PostMapping("/positions")
    public ResponseEntity<String> createPos(@RequestParam String id, @RequestParam String name, @RequestParam double alw) {
        posSrv.createPos(id, name, alw);
        return ResponseEntity.ok("success");
    }
    @PutMapping("/employees/assign-position")
    public ResponseEntity<String> assignPos(@RequestParam String empId, @RequestParam String targetId) {
        try {
            empSrv.assignPos(empId, targetId);
            return ResponseEntity.ok("success");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PostMapping("/attendance")
    public ResponseEntity<String> checkIn(@RequestParam String empId, @RequestParam int day, @RequestParam int hour, @RequestParam int ot) {
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
            if (s > maxEmp.calculateSalary() || maxEmp == null) maxEmp = e;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("totalBudget", total);
        res.put("highestPaidEmployee", maxEmp);
        return ResponseEntity.ok(res);
    }
}
