package a.employee.controller;

import a.employee.dto.AssignDepDTO;
import a.employee.dto.AssignPosDTO;
import a.employee.dto.EmployeeRequestDTO;
import a.employee.dto.UpdateNameDTO;
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
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeRequestDTO e) throws CustomException {
        empSrv.registerEmployee(e);
        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) throws CustomException {
         empSrv.removeEmployee(id);
         return ResponseEntity.ok("ok");
    }
    @PatchMapping("/{id}/name")
    public ResponseEntity<String> updateName(@Valid @RequestBody UpdateNameDTO a) throws CustomException {
        empSrv.updateEmployeeName(a);
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) throws CustomException {
        Employee e = empSrv.searchById(id);
        return ResponseEntity.ok(e);
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
    public ResponseEntity<String> assignPos(@Valid @RequestBody AssignPosDTO a) throws CustomException {
        empSrv.assignPos(a);
        return ResponseEntity.ok("ok");
    }
    @PutMapping("/assign-dep")
    public ResponseEntity<String> assignDep(@Valid @RequestBody AssignDepDTO a) throws CustomException {
        empSrv.assignDep(a);
        return ResponseEntity.ok("ok");
    }
}
