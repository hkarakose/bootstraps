package com.gamenism.client.widgets;

import com.gamenism.model.User;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import java.util.List;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 10:37 PM
 */
public class SignupForm extends Composite implements ValueAwareEditor<User>, HasEditorErrors<User> {
    @UiField
    TextBox email;
    @UiField
    PasswordTextBox password;

    @UiField @Ignore
    PasswordTextBox passwordAgain;

    public SignupForm() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void showErrors(List<EditorError> errors) {
    }

    public void flush() {
        System.out.println("SignupForm.flush");
    }

    public void onPropertyChange(String... paths) {

    }

    public void setValue(User value) {
        System.out.println("value = " + value);
    }

    public void setDelegate(EditorDelegate<User> delegate) {

    }

    interface SignupFormUiBinder extends UiBinder<HTMLPanel, SignupForm> {
    }

    private static SignupFormUiBinder ourUiBinder = GWT.create(SignupFormUiBinder.class);
}