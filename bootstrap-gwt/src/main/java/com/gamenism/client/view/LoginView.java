package com.gamenism.client.view;

import com.gamenism.client.Application;
import com.gamenism.client.Views;
import com.gamenism.client.service.SecurityService;
import com.gamenism.client.service.SecurityServiceAsync;
import com.gamenism.client.service.UserService;
import com.gamenism.client.service.UserServiceAsync;
import com.gamenism.client.util.LoginRequestBuilder;
import com.gamenism.client.widgets.LoginForm;
import com.gamenism.model.User;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import static com.gamenism.client.Views.SIGN_UP;

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
    @UiField
    Label loginResult;

    User user;

    @Override
    protected void onLoad() {
        super.onLoad();
    }

    @UiHandler("signInButton")
    public void onClickSignInButton(ClickEvent event) {
        user = driver.flush();
        LoginRequestBuilder requestBuilder = new LoginRequestBuilder(user);

        requestBuilder.send();
    }

    @UiHandler("signUpButton")
    public void onClickSignUpButton(ClickEvent event) {
        History.newItem(SIGN_UP.name());
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
        driver.edit(new User());
    }

    interface Driver extends SimpleBeanEditorDriver<User, LoginForm> {
    }

    Driver driver = GWT.create(Driver.class);

    interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }

    private static LoginViewUiBinder ourUiBinder = GWT.create(LoginViewUiBinder.class);
}