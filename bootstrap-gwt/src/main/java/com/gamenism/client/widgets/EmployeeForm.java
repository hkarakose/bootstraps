package com.gamenism.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gamenism.model.Employee;

import java.util.List;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 12:31 AM
 */
public class EmployeeForm
       extends Composite
       implements ValueAwareEditor<Employee>, HasEditorErrors<Employee> {
    @UiField
    TextBox name;

    @UiField
    @Editor.Path(value = "title")
    TextBox employeeTitle;

    @Editor.Ignore
    Label id;

    private Employee employee;
    private EditorDelegate<Employee> employeeDelegate;
    private HandlerRegistration subscribe;

    public void reset() {
        name.setText("");
        employeeTitle.setText("");
    }

    public EmployeeForm() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * called by the editor’s driver with the purpose of providing the attached delegate object and
     * is called before any value initialization is performed
     * @param delegate
     */
    public void setDelegate(EditorDelegate<Employee> delegate) {
        this.employeeDelegate = delegate;

        //In order for the class to get notifications, the subscribe method has to be called on the delegate object.
        subscribe = employeeDelegate.subscribe();
    }


    /**
     * called when the editor cycle is finished, in a depth-first manner.
     * You could put any logic in this method that you’d like to perform after flushing,
     * for example, as you do here to always transform the name and title to uppercase,
     * or you can add validity checks on your data and report errors by using the delegate object.
     */
    public void flush() {
        //TODO bu ornek faydasiz
        employee.setTitle(employee.getTitle().toUpperCase());
        employee.setName(employee.getName().toUpperCase());
    }

    /**
     * If you have code that you want to execute when some property in the underlying proxy object is changed,
     * you could put that logic into the onPropertyChange method.
     * The framework notifies this editor that one or more values have been changed.
     * Not all backing services support property-based notifications;
     * for example, SimpleBeanEditorDriver doesn’t. If your delegate doesn’t support this,
     * you could put your logic in the setValue method instead.
     *
     * @param paths
     */
    public void onPropertyChange(String... paths) {
        System.out.println("EmployeeForm.onPropertyChange");
        for (String path : paths) {
            System.out.println("path = " + path);
        }
    }

    /**
     * Called when the editor’s values are set.
     * The framework passes the object of which the editor is responsible for editing.
     * Here you could add initialization of any affected subeditors or
     * other objects that aren’t defined in the specific editor to be accessible by the Editor framework
     *
     *  @param value
     */
    public void setValue(Employee value) {
        this.employee = value;
    }

    /**
     * provides the ability to act on errors and gives the possibility of notifying
     * Whenever a ConstraintViolation has occurred,
     * the EditorDriver calls the showErrors method with a list of unconsumed errors reported by subeditors.
     * @param errors
     */
    public void showErrors(List<EditorError> errors) {
        for (EditorError error : errors) {
            System.out.println("error = " + error);
            error.setConsumed(true);
        }
    }

    interface EmployeeEditorUiBinder extends UiBinder<Widget, EmployeeForm> {
    }

    private static EmployeeEditorUiBinder uiBinder = GWT.create(EmployeeEditorUiBinder.class);
}
