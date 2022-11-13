package pro.sky.java.course2.weblibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pro.sky.java.course2.weblibrary.model.Employee;
import pro.sky.java.course2.weblibrary.record.EmployeeRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees= new HashMap<>();

    public Collection<Employee> getAllEmploees(){
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Имя работника не задано");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(),
                employeeRequest.getDepartment(), employeeRequest.getSalary());

        employees.put(employee.getId(), employee);

        return employee;

    }

    public int getSalarySum(){
        return employees.values().stream()
                .mapToInt(employee -> employee.getSalary()).sum();
    }

    public List<Employee> getSalaryMin(){
        //ищем минимальную зарплату
        int minSalary =  employees.values().stream()
                .mapToInt(employee -> employee.getSalary()).min().getAsInt();
        //сотрудников с минимальной зарплатой может быть несколько
        return employees.values().stream()
                .filter(employee -> employee.getSalary()==minSalary)
                .collect(Collectors.toList());
    }

    public List<Employee> getSalaryMax(){
        //ищем максимальную зарплату
        int maxSalary =  employees.values().stream()
                .mapToInt(employee -> employee.getSalary()).max().getAsInt();
        //сотрудников с максимальной зарплатой может быть несколько
        return employees.values().stream()
                .filter(employee -> employee.getSalary()==maxSalary)
                .collect(Collectors.toList());
    }


    public List<Employee> getHighSalary(){
        //получаем среднюю зарплату
        double averageSalary = employees.values().stream()
                .mapToInt(employee -> employee.getSalary()).average().getAsDouble();
        //список сотрудников, зарплата которых больше средней зарплаты
        return employees.values().stream()
                .filter(employee -> employee.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }
}
