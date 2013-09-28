package com.gamenism.client.service;

import com.gamenism.model.Employee;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 11:11 PM
 */
@RemoteServiceRelativePath("EmployeeService")
public interface EmployeeService extends RemoteService {
    public Employee getEmployee(Long employeeId);

    void create(Employee employee);

    public static class App {
        private static final EmployeeServiceAsync ourInstance = (EmployeeServiceAsync) GWT.create(EmployeeService.class);

        public static EmployeeServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
