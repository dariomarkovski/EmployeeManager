package com.finki.eimt.EmployeeManager.service.implementation;

import com.finki.eimt.EmployeeManager.model.Employee;
import com.finki.eimt.EmployeeManager.persistence.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeUserDetailsServiceImpl implements UserDetailsService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeUserDetailsServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeDao.findByEmail(email);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(employee.getRole().toString()));
        return new User(employee.getEmail(), employee.getPassword(), employee.isActivated(), true, true, true, grantedAuthorityList);
    }
}
