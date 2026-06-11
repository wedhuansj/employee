package a.employee.controller;

import a.employee.service.StatsService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    private final StatsService statsSrv;
    public StatsController(StatsService statsSrv) { this.statsSrv = statsSrv; }
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> res = statsSrv.getStats();
        return ResponseEntity.ok(res);
    }
}
