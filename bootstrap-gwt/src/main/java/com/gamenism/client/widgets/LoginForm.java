package com.gamenism.client.widgets;

import com.gamenism.model.User;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 4:47 PM
 */
public class LoginForm extends Composite implements HasEditorErrors<User> {
    @UiField
    TextBox email;
    @UiField
    PasswordTextBox password;
    @UiField
    CheckBox rememberMe;

    User user;

    public void showErrors(List<EditorError> errors) {
        for (EditorError error : errors) {
            System.out.println("error = " + error);
            error.setConsumed(true);
        }
    }

    public LoginForm() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    interface LoginWidgetUiBinder extends UiBinder<Widget, LoginForm> {
    }

    private static LoginWidgetUiBinder ourUiBinder = GWT.create(LoginWidgetUiBinder.class);
}