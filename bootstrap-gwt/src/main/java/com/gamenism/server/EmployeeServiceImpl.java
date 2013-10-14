package com.gamenism.server;

import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.Employee;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gamenism.client.service.EmployeeService;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 11:11 PM
 */
public class EmployeeServiceImpl extends RemoteServiceServlet implements EmployeeService {
    ActiveRecord<Employee> activeRecord = new ActiveRecord<Employee>();

    public Employee getEmployee(Long employeeId) {
        return activeRecord.find(1l);
    }

    public Employee create(Employee employee) {
        activeRecord.persist(employee);
        return employee;
    }
}