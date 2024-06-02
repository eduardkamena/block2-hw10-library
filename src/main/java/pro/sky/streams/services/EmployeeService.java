package pro.sky.streams.services;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    // Метод записи нового сотрудника
    void addEmployee(String firstName, String lastName, int department, int salary);

    // Метод удаления сотрудника
    void removeEmployee(String firstName, String lastName, int department, int salary);

    // Метод для поиска сотрудника
    EmployeeBook findEmployee(String firstName, String lastName, int department, int salary);

    // Метод печати всех сотрудников
    Map<String, EmployeeBook> printEmployee();

    // Метод печати размера массива
    int printSize();

    EmployeeBook getMinSalaryEmployee(int department);

    EmployeeBook getMaxSalaryEmployee(int department);

    List<EmployeeBook> getDepartmentEmployees(int department);

    Map<Integer, List<EmployeeBook>> getAllSortedDepartmentsEmployee();
}