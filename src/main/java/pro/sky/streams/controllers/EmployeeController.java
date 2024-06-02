package pro.sky.streams.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.streams.exceptions.EmployeeAlreadyAddedException;
import pro.sky.streams.exceptions.EmployeeNotFoundException;
import pro.sky.streams.exceptions.EmployeeStorageIsFullException;
import pro.sky.streams.services.EmployeeService;
import pro.sky.streams.services.EmployeeBook;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public void addEmployee(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("department") int department,
                            @RequestParam("salary") int salary) {
        employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/remove")
    public void removeEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("department") int department,
                               @RequestParam("salary") int salary) {
        employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/find")
    public EmployeeBook findEmployee(@RequestParam("firstName") String firstName,
                                     @RequestParam("lastName") String lastName,
                                     @RequestParam("department") int department,
                                     @RequestParam("salary") int salary) {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/print")
    public Map<String, EmployeeBook> printEmployee() {
        return employeeService.printEmployee();
    }

    @GetMapping(path = "/size")
    public int printSize() {
        return employeeService.printSize();
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handlerEx() {
        return "Нет места для записи сотрудника";
    }

    @ExceptionHandler
    public String handlerEx2(EmployeeNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    public String handlerEx3(EmployeeAlreadyAddedException e) {
        return e.getMessage();
    }
}
