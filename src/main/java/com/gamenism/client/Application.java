package com.gamenism.client;

import com.gamenism.client.view.LoginView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(LoginView.getInstance());

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
