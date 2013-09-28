package com.gamenism.client.widgets;

import com.gamenism.model.Employee;
import com.google.gwt.editor.client.CompositeEditor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.user.client.ui.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 2:16 PM
 */
public class ListEmployeeForm extends Composite
       implements CompositeEditor<List<Employee>, Employee, EmployeeForm> {

    private EditorChain<Employee, EmployeeForm> editorChain;
    private EditorDelegate<List<Employee>> editorDelegate;
    private List<EmployeeForm> editorList;

    public EmployeeForm createEditorForTraversal() {
        return new EmployeeForm();
    }

    public void setEditorChain(EditorChain<Employee, EmployeeForm> chain) {
        editorChain = chain;
    }

    public void setValue(List<Employee> value) {
        if (value == null) {
            value = new ArrayList<Employee>();
        } else {
            for (EmployeeForm employeeForm : editorList) {
                employeeForm.reset();
                editorChain.detach(employeeForm);
            }
        }

        for (Employee employee : value) {
            editorChain.attach(employee, new EmployeeForm());
        }
    }

    public void setDelegate(EditorDelegate<List<Employee>> delegate) {
        editorDelegate = delegate;
    }

    public String getPathElement(EmployeeForm subEditor) {
        return "[" + editorList.indexOf(subEditor) + "]";
    }

    public void flush() {

    }

    public void onPropertyChange(String... paths) {

    }
}
