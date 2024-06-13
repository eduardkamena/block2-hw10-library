package pro.sky.streams.services;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void checkEmployee(String firstName, String lastName);

    // Метод записи нового сотрудника
    void addEmployee(String firstName, String lastName, int department, int salary);

    // Метод удаления сотрудника
    void removeEmployee(String firstName, String lastName, int department, int salary);

    // Метод для поиска сотрудника
    Employee findEmployee(String firstName, String lastName, int department, int salary);

    // Метод печати всех сотрудников
    Map<String, Employee> printEmployee();

    // Метод печати размера массива
    int printSize();

    Employee getMinSalaryEmployee(int department);

    Employee getMaxSalaryEmployee(int department);

    List<Employee> getDepartmentEmployees(int department);

    Map<Integer, List<Employee>> getAllSortedDepartmentsEmployee();
}