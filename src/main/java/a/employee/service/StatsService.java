package a.employee.service;

import a.employee.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsService {
    private final EmployeeService empSrv;
    public StatsService(EmployeeService empSrv) { this.empSrv = empSrv; }
    public Map<String, Object> getStats() {
        List<Employee> list = empSrv.getAllEmployees();
        double total = 0;
        Employee maxEmp = null;
        for (Employee e : list) {
            double s = e.calculateSalary();
            total += s;
            if (maxEmp == null || s > maxEmp.calculateSalary()) maxEmp = e;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("totalBudget", total);
        res.put("highestPaidEmployee", maxEmp);
        return res;
    }
}
