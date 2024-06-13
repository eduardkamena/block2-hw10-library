package pro.sky.streams.services;

public record Employee(String firstName, String lastName, int department, int salary) {


    @Override
    public String toString() {
        return "Сотрудник: " +
                "{Имя - " + firstName +
                ", Фамилия - " + lastName +
                ", Отдел - " + department +
                ", Зарплата - " + salary +
                "}" + "\n";
    }
}
