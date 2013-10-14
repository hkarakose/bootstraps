package com.gamenism.client.view;

import com.gamenism.client.Views;
import com.gamenism.client.service.UserService;
import com.gamenism.client.service.UserServiceAsync;
import com.gamenism.client.widgets.SignupForm;
import com.gamenism.model.User;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 10:40 PM
 */
public class SignupView extends Composite {
    UserServiceAsync userService = UserService.App.getInstance();

    @UiField
    SignupForm signupForm;
    @UiField
    Button signUpButton;

    @UiHandler("signUpButton")
    public void onClickSignUpButton(ClickEvent event) {
        User user = driver.flush();
        if (userExists(user)) {
            //TODO error
            return;
        }

        userService.create(user, new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert("Error creating user: " + caught);
            }

            public void onSuccess(Void result) {
                Window.alert("User created successfully.");
                goToLoginPage();
            }
        });
    }

    private void goToLoginPage() {
        History.newItem(Views.LOGIN.name());
    }

    private boolean userExists(final User userFromForm) {
        UserAsyncCallback callback = new UserAsyncCallback(userFromForm);
        userService.findByEmail(userFromForm.getEmail(), callback);

        return callback.isUserFound();
    }

    public SignupView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        //bind driver with editor
        driver.initialize(signupForm);

        //bind driver with domain object
        driver.edit(new User());
    }

    interface Driver extends SimpleBeanEditorDriver<User, SignupForm> {
    }

    Driver driver = GWT.create(Driver.class);

    interface SignupViewUiBinder extends UiBinder<HTMLPanel, SignupView> {
    }

    private static SignupViewUiBinder ourUiBinder = GWT.create(SignupViewUiBinder.class);

    private static class UserAsyncCallback implements AsyncCallback<User> {
        private final User userFromForm;
        public boolean userFound;

        public UserAsyncCallback(User userFromForm) {
            this.userFromForm = userFromForm;
        }

        public void onFailure(Throwable caught) {
            Window.alert("Error finding user by email: " + userFromForm.getEmail());
        }

        public void onSuccess(User result) {
            Window.alert("User found with email: " + userFromForm.getEmail());
            if (result != null) {
                userFound = true;
            } else {
                userFound = false;
            }
        }

        private boolean isUserFound() {
            return userFound;
        }
    }
}