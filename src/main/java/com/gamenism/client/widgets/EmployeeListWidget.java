package com.gamenism.client.widgets;

import com.gamenism.model.Employee;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.adapters.HasDataEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import static com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.*;
import static com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.*;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 3:54 PM
 */
public class EmployeeListWidget extends Composite {
    HasDataEditor<Employee> hasDataEditor;

    public EmployeeListWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        CellTable<Employee> table = new CellTable<Employee>();
        table.setKeyboardSelectionPolicy(ENABLED);

        TextColumn<Employee> nameColumn = new TextColumn<Employee>(){
            @Override
            public String getValue(Employee object) {
                return object.getName();
            }
        };
        table.addColumn(nameColumn, "Name");

        TextColumn<Employee> titleColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee object) {
                return object.getTitle();
            }
        };
        table.addColumn(titleColumn, "Title");

        hasDataEditor = HasDataEditor.of(table);

    }

    interface EmployeeListWidgetUiBinder extends UiBinder<Widget, EmployeeListWidget>{
    };

    private static EmployeeListWidgetUiBinder uiBinder = GWT.create(EmployeeListWidgetUiBinder.class);
}
