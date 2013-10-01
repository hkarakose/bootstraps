package com.gamenism.client;

import com.gamenism.client.service.AuthenticationService;
import com.gamenism.client.service.AuthenticationServiceAsync;
import com.gamenism.client.view.LoginView;
import com.gamenism.client.view.SignupView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import static com.gamenism.client.Views.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint, ValueChangeHandler<String> {

    private AuthenticationServiceAsync authService = AuthenticationService.App.getAuthenticationService();

    public void onModuleLoad() {
        showHomePage();

        authService.retrieveUsername(
                new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        Window.alert("Remote Procedure Call - Failure");
                    }

                    public void onSuccess(String result) {
                        Window.alert(result);
                    }
                }
        );

        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();

        Window.addWindowClosingHandler(new Window.ClosingHandler() {
            public void onWindowClosing(Window.ClosingEvent event) {
                event.setMessage("Ran out of history.  Now leaving application, is that OK?");
            }
        });

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

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = null;
        if (event.getValue() != null) token = event.getValue().trim();
        if ((token == null) || (token.equals(""))) {
        } else if (token.equals(SIGN_UP.name())) {
            clearAndAdd(new SignupView());
        } else {
            showHomePage();
        }
    }

    private void showHomePage() {
        clearAndAdd(LoginView.getInstance());
    }

    private static void clearAndAdd(Widget widget) {
        RootPanel.get().clear();
        RootPanel.get().add(widget);
    }
}
