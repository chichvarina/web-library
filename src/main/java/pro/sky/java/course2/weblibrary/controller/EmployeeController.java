package pro.sky.java.course2.weblibrary.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest)  {
        if (! StringUtils.isAlpha(employeeRequest.getFirstName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Фамилия должна содержать только буквы");
        }
        if (! StringUtils.isAlpha(employeeRequest.getLastName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Имя должно содержать только буквы");
        }
        String newFirstName = StringUtils.capitalize(employeeRequest.getFirstName());
        employeeRequest.setFirstName(newFirstName);

        String newLastName = StringUtils.capitalize(employeeRequest.getLastName());
        employeeRequest.setLastName(newLastName);


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
