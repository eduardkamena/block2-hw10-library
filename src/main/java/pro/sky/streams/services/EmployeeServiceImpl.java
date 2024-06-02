package pro.sky.streams.services;

import org.springframework.stereotype.Service;
import pro.sky.streams.exceptions.EmployeeAlreadyAddedException;
import pro.sky.streams.exceptions.EmployeeNotFoundException;
import pro.sky.streams.exceptions.EmployeeStorageIsFullException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_LIST = 11;

    private final static Map<String, EmployeeBook> employeeBooks = new HashMap(Map.of(
            "101",
            new EmployeeBook("Arina", "Egorova", 1, 110000),
            "102",
            new EmployeeBook("Andrey", "Vasiliev", 2, 134000),
            "103",
            new EmployeeBook("Alexandr", "Egorov", 3, 123000),
            "104",
            new EmployeeBook("Sergey", "Erohin", 5, 160000),
            "105",
            new EmployeeBook("Irina", "Rud", 4, 113000),
            "106",
            new EmployeeBook("Marina", "Morozova", 5, 99000),
            "107",
            new EmployeeBook("Valeriy", "Bogolubov", 2, 87000),
            "108",
            new EmployeeBook("Mihail", "Lavrentiev", 3, 143000),
            "109",
            new EmployeeBook("Dmitry", "Pakulichev", 1, 136000),
            "110",
            new EmployeeBook("Karina", "Simonyan", 4, 101000)
    ));

    // Метод записи нового сотрудника
    @Override
    public void addEmployee(String firstName, String lastName, int department, int salary) {
        EmployeeBook employeeBook = new EmployeeBook(firstName, lastName, department, salary);
        if (employeeBooks.size() >= MAX_LIST) {
            throw new EmployeeStorageIsFullException("Нет места для записи сотрудника");
        }
        if (employeeBooks.containsValue(employeeBook)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employeeBooks.put(employeeBook.lastName(), employeeBook);
    }

    // Метод удаления сотрудника
    @Override
    public void removeEmployee(String firstName, String lastName, int department, int salary) {
        EmployeeBook employeeBook = new EmployeeBook(firstName, lastName, department, salary);
        employeeBooks.remove(employeeBook.lastName(), employeeBook);
    }

    // Метод для поиска сотрудника
    @Override
    public EmployeeBook findEmployee(String firstName, String lastName, int department, int salary) {
        EmployeeBook employeeBook = new EmployeeBook(firstName, lastName, department, salary);
        if (!employeeBooks.containsValue(employeeBook)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employeeBook;
    }

    @Override
    public Map<String, EmployeeBook> printEmployee() {
        return employeeBooks;
    }

    @Override
    public int printSize() {
        return employeeBooks.size();
    }

    @Override
    public EmployeeBook getMinSalaryEmployee(int department) {
        return employeeBooks.values()
                .stream()
                .filter(employeeBook -> employeeBook.department() == department)
                .min(Comparator.comparing(EmployeeBook::salary))
                .orElse(null);
    }

    @Override
    public EmployeeBook getMaxSalaryEmployee(int department) {
        return employeeBooks.values()
                .stream()
                .filter(employeeBook -> employeeBook.department() == department)
                .max(Comparator.comparing(EmployeeBook::salary))
                .orElse(null);
    }

    @Override
    public List<EmployeeBook> getDepartmentEmployees(int department) {
        return employeeBooks.values()
                .stream()
                .filter(employeeBook -> employeeBook.department() == department)
                .toList();
    }

    @Override
    public Map<Integer, List<EmployeeBook>> getAllSortedDepartmentsEmployee() {
        return employeeBooks.values()
                .stream()
                .collect(Collectors.groupingBy(EmployeeBook::department));
    }
}


