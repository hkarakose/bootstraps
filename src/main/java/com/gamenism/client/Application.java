package com.gamenism.client;

import com.gamenism.client.service.EmployeeService;
import com.gamenism.client.service.EmployeeServiceAsync;
import com.gamenism.client.service.SecurityService;
import com.gamenism.client.service.SecurityServiceAsync;
import com.gamenism.client.util.SecureAsyncCallback;
import com.gamenism.client.view.LoginView;
import com.gamenism.client.view.SignupView;
import com.gamenism.client.widgets.Layout;
import com.gamenism.model.Employee;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint, ValueChangeHandler<String> {
    //    private SecurityServiceAsync authService = SecurityService.App.getAuthenticationService();
    private RootPanel rootPanel = RootPanel.get();

    public static String username = null;
    public static Application me;

    public void onModuleLoad() {
        me = this;
        showHomePage();

//        retrieveUsername();

        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();

        //removed because of login form panel
//        Window.addWindowClosingHandler(new Window.ClosingHandler() {
//            public void onWindowClosing(Window.ClosingEvent event) {
//                event.setMessage("Ran out of history.  Now leaving application, is that OK?");
//            }
//        });

        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            public void onUncaughtException(Throwable e) {
                Throwable unwrap = unwrap(e);
                unwrap.printStackTrace();
            }

            public Throwable unwrap(Throwable e) {
                if (e instanceof UmbrellaException) {
                    UmbrellaException ue = (UmbrellaException) e;
                    if (ue.getCauses().size() == 1) {
                        return unwrap(ue.getCauses().iterator().next());
                    }
                }
                return e;
            }
        });
    }

//    public void retrieveUsername() {
//        authService.retrieveUsername(new AsyncCallback<String>() {
//            public void onFailure(Throwable caught) {
//                //Window.alert("Remote Procedure Call - Failure");
//            }
//
//            public void onSuccess(String result) {
//                username = result;
//                RootPanel.get().add(new HTML(username));
//            }
//        });
//    }

    private void showLoginPage() {
        clearAndAdd(LoginView.getInstance());
    }

    private void showUserArea() {
        rootPanel.clear();
        rootPanel.add(new HTML("<h1>Users Area</h1>"));
    }

    private void showSignUpPage() {
        clearAndAdd(new SignupView());
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = null;
        if (event.getValue() != null) {
            token = event.getValue().trim();
        }

        Views view;
        try {
            view = Views.valueOf(token);
        } catch (IllegalArgumentException e) {
            view = Views.HOME;
        }

        switch (view) {
            case SIGN_UP:
                showSignUpPage();
                break;
            case LOGIN:
                showLoginPage();
                break;
            case HOME:
                showHomePage();
                break;
            default:
                showHomePage();
                break;
            case USER_AREA:
                showUserArea();
                break;
        }
    }

    EmployeeServiceAsync employeeService;
    Button button;
    Label label;
    Anchor logoutButton;
    Layout layout;
    private void showHomePage() {
        RootPanel.get().clear();
        RootPanel.get().add(layout);

        layout.add(button);
        layout.add(logoutButton);
        layout.add(label);
    }

    private static void clearAndAdd(Widget widget) {
        RootPanel.get().clear();
        RootPanel.get().add(widget);
    }

    public Application() {
        button = new Button("Get employee");
        logoutButton = new Anchor("Logout", "/j_spring_security_logout");
        label = new Label();
        employeeService = EmployeeService.App.getInstance();
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                employeeService.create(new Employee(), new SecureAsyncCallback<Employee>() {
                    @Override
                    public void onSuccess(Employee result) {
                        label.setText(result.toString());
                    }
                });
            }
        });

        layout = new Layout();
    }
}
