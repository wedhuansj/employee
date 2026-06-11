package a.employee.service;

import a.employee.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryService {
    private final EmployeeService empSrv;
    public SalaryService(EmployeeService empSrv) { this.empSrv = empSrv; }
    public List<Map<String, Object>> getSalaries() {
        List<Employee> list = empSrv.getAllEmployees();
        List<Map<String, Object>> res = new ArrayList<>();
        for (Employee e : list) {
            Map<String, Object> m = new HashMap<>();
            m.put("name", e.getName());
            m.put("salary", e.calculateSalary());
            res.add(m);
        }
        return res;
    }
}
