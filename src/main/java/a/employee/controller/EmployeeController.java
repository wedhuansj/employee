package a.employee.controller;

import a.employee.dto.*;
import a.employee.exception.CustomException;
import a.employee.model.Employee;
import a.employee.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(value = "{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> uploadAvatar(@PathVariable @NotBlank String id, @RequestParam("file")MultipartFile file) throws CustomException {
        if (file.isEmpty()) throw new CustomException("Vui lòng chọn 1 file ảnh hợp lệ!");
        String contentType  = file.getContentType();
        if (contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) throw new CustomException("Chỉ chấp nhận định dạng ảnh JPG hoặc PNG.");
        empSrv.saveAvatar(id, file);
        return ResponseEntity.ok(new MessageResponse("Upload ảnh đại diện thành công"));
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
    public ResponseEntity<Page<Employee>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue =  "10") int size) {
        Pageable pageable  = PageRequest.of(page,size);
        return ResponseEntity.ok(empSrv.getAllEmployees(pageable));
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
