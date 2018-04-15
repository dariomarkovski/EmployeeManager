package com.finki.eimt.EmployeeManager.service.implementation;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendActivationCode(Employee employee) {
        String mailSubject = "[Employee Manager] Activate your account";
        String mailText = "Here is your activation code. Paste it in the activation field. " + employee.getActivationCode();
        SimpleMailMessage simpleMailMessage = constructSimpleMailMessage(employee.getEmail(), mailSubject, mailText);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    @Async
    public void sendResetPassword(Employee employee, String password) {
        String mailSubject = "[Employee Manager] Reset Password";
        String mailText = "Here is your new password: " + password;
        SimpleMailMessage simpleMailMessage = constructSimpleMailMessage(employee.getEmail(), mailSubject, mailText);
        System.out.println(employee.getEmail());
        System.out.println(password);
        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage constructSimpleMailMessage(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }
}
