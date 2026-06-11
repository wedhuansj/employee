package a.employee.controller;

import a.employee.dto.AttendanceRequestDTO;
import a.employee.exception.CustomException;
import a.employee.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attSrv;
    public AttendanceController(AttendanceService attSrv) { this.attSrv = attSrv; }
    @PostMapping
    public ResponseEntity<String> checkIn(@RequestBody AttendanceRequestDTO a) {
        try {
            attSrv.checkIn(a);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
}
