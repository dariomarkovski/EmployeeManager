package com.finki.eimt.EmployeeManager.init;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.model.Gender;
import com.finki.eimt.EmployeeManager.model.Role;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class EmployeeInit {

    private EmployeeService employeeService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${employee.email}")
    private String employeeEmail;
    @Value("${employee.password}")
    private String employeePassword;
    @Value("${employee.firstName}")
    private String employeeFirstName;
    @Value("${employee.lastName}")
    private String employeeLastName;
    @Value("${employee.gender}")
    private String employeeGender;
    @Value("${employee.role}")
    private String employeeRole;

    @Autowired
    public EmployeeInit(EmployeeService employeeService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeService = employeeService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void init() {
        if (employeeService.countEmployees() == 0) {
            Employee employee = new Employee();
            employee.setEmail(employeeEmail);
            employee.setPassword(bCryptPasswordEncoder.encode(employeePassword));
            employee.setFirstName(employeeFirstName);
            employee.setLastName(employeeLastName);
            employee.setGender(Gender.valueOf(employeeGender));
            employee.setActivated(true);
            employee.setRegistrationDate(LocalDateTime.now());
            employee.setRole(Role.valueOf(employeeRole));
            employeeService.saveEmployee(employee);
        }
    }
}
