package com.finki.eimt.EmployeeManager.service;

import com.finki.eimt.EmployeeManager.model.Employee;

public interface MailSenderService {
    void sendActivationCode(Employee employee);

    void sendResetPassword(Employee employee, String password);
}
