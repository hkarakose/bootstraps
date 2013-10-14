package com.gamenism.client.service;

import com.gamenism.model.Employee;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 11:11 PM
 */
public interface EmployeeServiceAsync {
    void getEmployee(Long employeeId, AsyncCallback<Employee> async);

    void create(Employee employee, AsyncCallback<Employee> async);
}
