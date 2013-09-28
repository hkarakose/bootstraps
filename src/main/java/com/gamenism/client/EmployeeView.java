package com.gamenism.client;

import com.gamenism.client.service.EmployeeService;
import com.gamenism.client.service.EmployeeServiceAsync;
import com.gamenism.client.widgets.EmployeeForm;
import com.gamenism.model.Employee;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 8:56 PM
 */
public class EmployeeView extends Composite{
    EmployeeServiceAsync employeeService = EmployeeService.App.getInstance();

    Employee employee;

    @UiField
    EmployeeForm employeeForm;

    @UiField
    Button resetEmployeeButton;

    @UiField
    Button saveEmployeeButton;

    @UiField
    Button fetchEmployeeButton;

    public EmployeeView() {
        employee = new Employee();
        initWidget(ourUiBinder.createAndBindUi(this));
        driver.initialize(employeeForm);

        //sync state between the editor and the domain object before the user edits any fields
        driver.edit(employee);

    }

    @UiHandler(value="resetEmployeeButton")
    void onClickReset(ClickEvent event) {
        employeeForm.reset();
    }

    @UiHandler(value = "saveEmployeeButton")
    void onClickSave(ClickEvent event) {
        driver.flush();
        employeeService.create(employee, new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                System.err.println("Error: " + caught);
            }

            public void onSuccess(Void result) {
                Window.alert("Persist success");
            }
        });
    }

    @UiHandler("fetchEmployeeButton")
    void onClickGet(ClickEvent event) {
        employeeService.getEmployee(1l, new AsyncCallback<Employee>() {
            public void onFailure(Throwable caught) {
                System.err.println("Error: " + caught);
            }

            public void onSuccess(Employee result) {
                if (result == null) {
                    Window.alert("Employee cannot be found. ");
                }
                driver.edit(result);
            }
        });
    }

    interface Driver extends SimpleBeanEditorDriver<Employee, EmployeeForm>{};

    Driver driver = GWT.create(Driver.class);

    interface EmployeeViewUiBinder extends UiBinder<Widget, EmployeeView> {
    }

    private static EmployeeViewUiBinder ourUiBinder = GWT.create(EmployeeViewUiBinder.class);
}