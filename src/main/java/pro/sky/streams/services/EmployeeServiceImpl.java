package pro.sky.streams.services;

import org.springframework.stereotype.Service;
import pro.sky.streams.exceptions.EmployeeAlreadyAddedException;
import pro.sky.streams.exceptions.EmployeeNotFoundException;
import pro.sky.streams.exceptions.EmployeeStorageIsFullException;
import pro.sky.streams.exceptions.InvalidCheckEmployeeException;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_LIST = 11;

    private final static Map<String, Employee> employees = new HashMap(Map.of(
            "Egorova",
            new Employee("Arina", "Egorova", 1, 110000),
            "Vasiliev",
            new Employee("Andrey", "Vasiliev", 2, 134000),
            "Egorov",
            new Employee("Alexandr", "Egorov", 3, 123000),
            "Erohin",
            new Employee("Sergey", "Erohin", 5, 160000),
            "Rud",
            new Employee("Irina", "Rud", 4, 113000),
            "Morozova",
            new Employee("Marina", "Morozova", 5, 99000),
            "Bogolubov",
            new Employee("Valeriy", "Bogolubov", 2, 87000),
            "Lavrentiev",
            new Employee("Mihail", "Lavrentiev", 3, 143000),
            "Pakulichev",
            new Employee("Dmitry", "Pakulichev", 1, 136000),
            "Simonyan",
            new Employee("Karina", "Simonyan", 4, 101000)
    ));

    @Override
    public void checkEmployee(String firstName, String lastName) {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new InvalidCheckEmployeeException("Имя должно содержать только буквы");
        }
    }

    // Метод записи нового сотрудника
    @Override
    public void addEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        if (employees.size() >= MAX_LIST) {
            throw new EmployeeStorageIsFullException("Нет места для записи сотрудника");
        }
        if (employees.containsValue(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employees.put(employee.lastName(), employee);
    }

    // Метод удаления сотрудника
    @Override
    public void removeEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        employees.remove(employee.lastName(), employee);
    }

    // Метод для поиска сотрудника
    @Override
    public Employee findEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        if (!employees.containsValue(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> printEmployee() {
        return employees;
    }

    @Override
    public int printSize() {
        return employees.size();
    }

    @Override
    public Employee getMinSalaryEmployee(int department) {
        return employees.values()
                .stream()
                .filter(employee -> employee.department() == department)
                .min(comparing(Employee::salary))
                .orElse(null);
    }

    @Override
    public Employee getMaxSalaryEmployee(int department) {
        return employees.values()
                .stream()
                .filter(employee -> employee.department() == department)
                .max(comparing(Employee::salary))
                .orElse(null);
    }

    @Override
    public List<Employee> getDepartmentEmployees(int department) {
        return employees.values()
                .stream()
                .filter(employee -> employee.department() == department)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllSortedDepartmentsEmployee() {
        return employees.values()
                .stream()
                .collect(groupingBy(Employee::department));
    }
}
