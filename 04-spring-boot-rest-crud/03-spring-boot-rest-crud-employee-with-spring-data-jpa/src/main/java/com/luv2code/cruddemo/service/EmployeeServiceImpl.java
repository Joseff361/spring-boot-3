package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.dao.EmployeeRepository;
import com.luv2code.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        return optionalEmployee.get();
    }

    @Override
    // @Transactional => JpaRepository provides this functionality
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    // @Transactional => JpaRepository provides this functionality
    public void deleteById(int id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);

        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        this.employeeRepository.deleteById(id);
    }
}
