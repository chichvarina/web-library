package pro.sky.java.course2.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.weblibrary.model.Employee;
import pro.sky.java.course2.weblibrary.record.EmployeeRequest;
import pro.sky.java.course2.weblibrary.service.EmployeeService;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmplyees(){
        return employeeService.getAllEmploees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public List<Employee> getSalaryMin(){
        return this.employeeService.getSalaryMin();
    }

    @GetMapping("/employees/salary/max")
    public List<Employee> getSalaryMax(){
        return this.employeeService.getSalaryMax();
    }

    @GetMapping("/employees/high-salary")
    public List<Employee> getHighSalary(){
        return this.employeeService.getHighSalary();
    }

}
