package a.employee.controller;

import a.employee.dto.EmployeeRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Employee;
import a.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
    private final EmployeeService empSrv;
    public EmployeeController(EmployeeService empSrv) { this.empSrv = empSrv; }
    @PostMapping
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeRequestDTO e) {
        try {
            empSrv.registerEmployee(e);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            empSrv.removeEmployee(id);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
    @PatchMapping("/{id}/name")
    public ResponseEntity<String> updateName(@PathVariable String id, @RequestParam String name) {
        try {
            empSrv.updateEmployeeName(id, name);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        try {
            Employee e = empSrv.searchById(id);
            return ResponseEntity.ok(e);
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(empSrv.getAllEmployees());
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Employee>> getAllSorted() {
        return ResponseEntity.ok(empSrv.getEmployeesSorted());
    }
    @PutMapping("/assign-position")
    public ResponseEntity<String> assignPos(@RequestParam String empId, @RequestParam String posId) {
        try {
            empSrv.assignPos(empId, posId);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
    @PutMapping("/assign-dep")
    public ResponseEntity<String> assignDep(@RequestParam String empId, @RequestParam String targetId) {
        try {
            empSrv.assignDep(empId, targetId);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
}
