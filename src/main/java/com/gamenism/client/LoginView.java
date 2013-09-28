package com.gamenism.client;

import com.gamenism.client.service.UserService;
import com.gamenism.client.service.UserServiceAsync;
import com.gamenism.client.widgets.LoginForm;
import com.gamenism.model.User;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * User: halil
 * Date: 8/18/12
 * Time: 4:50 PM
 */
public class LoginView extends Composite {
    UserServiceAsync userService;

    @UiField
    LoginForm loginForm;
    @UiField
    Button signInButton;
    @UiField
    Button signUpButton;

    User user;

    @Override
    protected void onLoad() {
        super.onLoad();
    }

    @UiHandler("signInButton")
    public void onClickSignInButton(ClickEvent event) {
        userService.find(1l, new AsyncCallback<User>() {
            public void onFailure(Throwable caught) {
                Window.alert("User not found");
            }

            public void onSuccess(User result) {
                user = result;
                Window.alert("User found: " + user);
                driver.edit(user);
            }
        });
    }

//    public void onKeyPress(KeyPressEvent event) {
//        if (event.getCharCode() == 13) {
//            loginButtonClicked(null);
//        }
//    }

    private static LoginView me;
    public static LoginView getInstance() {
        if (me == null) {
            me = new LoginView();
        }
        return me;
    }

    private LoginView() {
        initWidget(ourUiBinder.createAndBindUi(this));
        userService = UserService.App.getInstance();
        driver.initialize(loginForm);

        userService.create(new User("halilkarakose@gmail.com", "xxxx"), new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert("User not created");
            }

            public void onSuccess(Void result) {
                Window.alert("User created");
            }
        });


        user = new User();
        driver.edit(user);
    }

    interface Driver extends SimpleBeanEditorDriver<User, LoginForm> {
    }

    Driver driver = GWT.create(Driver.class);

    interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }

    private static LoginViewUiBinder ourUiBinder = GWT.create(LoginViewUiBinder.class);
}