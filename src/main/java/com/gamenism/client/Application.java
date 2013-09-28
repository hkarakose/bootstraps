package com.gamenism.client;

import com.gamenism.client.widgets.EmployeeForm;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get().add(LoginView.getInstance());
    }
}
