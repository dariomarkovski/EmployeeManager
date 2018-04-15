package com.finki.eimt.EmployeeManager.config;

import com.finki.eimt.EmployeeManager.service.implementation.EmployeeUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private EmployeeUserDetailsServiceImpl employeeUserDetailsServiceImpl;

    @Autowired
    public WebSecurityConfiguration(EmployeeUserDetailsServiceImpl employeeUserDetailsServiceImpl) {
        this.employeeUserDetailsServiceImpl = employeeUserDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/activation").permitAll()
                .antMatchers("/reset-password").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
                .and().rememberMe().rememberMeParameter("remember-me")
                    .rememberMeCookieName("employee-manager-remember")
                    .userDetailsService(employeeUserDetailsServiceImpl)
                .and().logout().deleteCookies("employee-manager-remember").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(employeeUserDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("dario.markovskii@gmail.com");
        javaMailSender.setPassword("wahdosajjzhshrye");
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return javaMailSender;
    }
}
