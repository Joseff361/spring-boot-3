package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.dao.EmployeeDAO;
import com.luv2code.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        Employee employee = this.employeeDAO.findById(id);
        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        return employee;
    }

    @Override
    @Transactional // It is a good practice to place @Transactional in the @Service class
    public Employee save(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    @Override
    @Transactional // It is a good practice to place @Transactional in the @Service class
    public void deleteById(int id) {
        Employee employee = this.employeeDAO.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        this.employeeDAO.deleteById(id);
    }
}
