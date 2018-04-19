package com.finki.eimt.EmployeeManager.service.implementation;

import com.finki.eimt.EmployeeManager.model.Department;
import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.model.Role;
import com.finki.eimt.EmployeeManager.persistence.EmployeeDao;
import com.finki.eimt.EmployeeManager.service.EmployeeService;
import com.finki.eimt.EmployeeManager.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MailSenderService mailSenderService;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, BCryptPasswordEncoder bCryptPasswordEncoder, MailSenderService mailSenderService) {
        this.employeeDao = employeeDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setActivated(false);
        employee.setRole(Role.ROLE_EMPLOYEE);
        employee.setRegistrationDate(LocalDateTime.now());
        String randomString = UUID.randomUUID().toString();
        employee.setActivationCode(randomString);
        mailSenderService.sendActivationCode(employee);
        return employeeDao.save(employee);
    }

    @Override
    public Employee activateAccount(String code) {
        Employee employee = employeeDao.findByActivationCode(code.trim());
        employee.setActivated(true);
        return employeeDao.save(employee);
    }

    @Override
    public Employee resetPassword(String email) {
        System.out.println(email);
        String randomPassword = UUID.randomUUID().toString();
        Employee employee = employeeDao.findByEmail(email);
        employee.setPassword(bCryptPasswordEncoder.encode(randomPassword));
        employeeDao.save(employee);
        mailSenderService.sendResetPassword(employee, randomPassword);
        return employee;
    }

    @Override
    public Employee changePassword(String email, String currentPassword, String newPassword) {
        Employee employee = employeeDao.findByEmail(email);
        if (bCryptPasswordEncoder.matches(currentPassword, employee.getPassword())) {
            System.out.println("Password match");
            employee.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return employeeDao.save(employee);
        } else {
            System.out.println("Password don't match");
            return null;
        }
    }

    @Override
    public List<Employee> getNonActivatedEmployees() {
        return employeeDao.findAllByActivated(false);
    }

    @Override
    public long deleteEmployee(String email) {
        return employeeDao.deleteByEmail(email);
    }

    @Override
    public long countEmployees() {
        return employeeDao.count();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee saveEmployeeFromAdmin(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setActivated(true);
        employee.setRegistrationDate(LocalDateTime.now());
        return employeeDao.save(employee);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> getAllEmployeesForManager(String email) {
        Employee manager = employeeDao.findByEmail(email);
        Department department = manager.getDepartment();
        return employeeDao.findAllByDepartment(department);
    }

    @Override
    public Employee getEmployeeFromEmail(String email) {
        return employeeDao.findByEmail(email);
    }
}
