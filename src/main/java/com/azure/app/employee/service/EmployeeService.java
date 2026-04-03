package com.azure.app.employee.service;

import com.azure.app.employee.model.Employee;
import com.azure.app.employee.util.EmployeeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return EmployeeData.getEmployees();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        return EmployeeData.getEmployees().stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }
}
