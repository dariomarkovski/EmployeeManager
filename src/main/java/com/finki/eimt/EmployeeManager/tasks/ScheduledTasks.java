package com.finki.eimt.EmployeeManager.tasks;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    EmployeeService employeeService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteNonActivatedAccounts() {
        List<Employee> employees = employeeService.getNonActivatedEmployees();
        for (Employee employee : employees) {
            LocalDateTime registrationDate = employee.getRegistrationDate();
            if (registrationDate.plusHours(24).isBefore(LocalDateTime.now())) {
                employeeService.deleteEmployee(employee.getEmail());
            }
        }
    }

}
