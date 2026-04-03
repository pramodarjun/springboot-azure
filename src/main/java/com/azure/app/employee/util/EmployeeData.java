package com.azure.app.employee.util;

import com.azure.app.employee.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John Doe", "john.doe@company.com", "Engineering"));
        employees.add(new Employee(2L, "Jane Smith", "jane.smith@company.com", "Marketing"));
        employees.add(new Employee(3L, "Mike Johnson", "mike.johnson@company.com", "Sales"));
        employees.add(new Employee(4L, "Sarah Williams", "sarah.williams@company.com", "HR"));
        employees.add(new Employee(5L, "David Brown", "david.brown@company.com", "Engineering"));
        return employees;
    }
}
