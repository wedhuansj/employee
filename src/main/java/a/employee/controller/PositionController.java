package a.employee.controller;

import a.employee.dto.PositionRequestDTO;
import a.employee.exception.CustomException;
import a.employee.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
    private final PositionService posSrv;
    public PositionController(PositionService posSrv) { this.posSrv = posSrv; }
    @PostMapping
    public ResponseEntity<String> createPos(@RequestBody PositionRequestDTO a) {
        try {
            posSrv.createPos(a);
            return ResponseEntity.ok("ok");
        } catch (CustomException ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }
}
