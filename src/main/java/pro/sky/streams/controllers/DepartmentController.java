package pro.sky.streams.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.streams.services.EmployeeService;
import pro.sky.streams.services.EmployeeBook;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final EmployeeService employeeService;

    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/max-salary")
    public EmployeeBook getMaxSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeService.getMaxSalaryEmployee(department);
    }

    @GetMapping(path = "/min-salary")
    public EmployeeBook getMinSalaryEmployee(@RequestParam("departmentId") int department) {
        return employeeService.getMinSalaryEmployee(department);
    }

    @GetMapping(path = "/all-sorted")
    public Map<Integer, List<EmployeeBook>> getAllSortedDepartmentsEmployee() {
        return employeeService.getAllSortedDepartmentsEmployee();
    }

    @GetMapping(path = "/all")
    public List<EmployeeBook> getDepartmentEmployees(@RequestParam("departmentId") int department) {
        return employeeService.getDepartmentEmployees(department);
    }
}
