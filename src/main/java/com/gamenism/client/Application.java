package com.gamenism.client;

import com.gamenism.client.service.AuthenticationService;
import com.gamenism.client.service.AuthenticationServiceAsync;
import com.gamenism.client.view.LoginView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
    private AuthenticationServiceAsync authService = AuthenticationService.App.getAuthenticationService();

    public void onModuleLoad() {
        RootPanel.get().add(LoginView.getInstance());

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
}
