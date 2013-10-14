package com.gamenism.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class FormPanelExample implements EntryPoint {

    public void onModuleLoad() {
        // Create a FormPanel and point it at a service.
        final FormPanel form = new FormPanel();
        form.setAction("j_spring_security_check");
        form.setMethod(FormPanel.METHOD_POST);
        form.setEncoding(FormPanel.ENCODING_MULTIPART);

        // Create a panel to hold all of the form widgets.
        VerticalPanel panel = new VerticalPanel();
        form.setWidget(panel);

        // Create a TextBox, giving it a name so that it will be submitted.
        final TextBox tb = new TextBox();
        tb.setName("j_username");
        panel.add(tb);

        // Create a TextBox, giving it a name so that it will be submitted.
        final TextBox tb2 = new TextBox();
        tb2.setName("j_password");
        panel.add(tb2);

        // Add a 'submit' button.
        panel.add(new Button("Submit", new ClickHandler() {
            public void onClick(ClickEvent event) {
                form.submit();
            }
        }));

        // Add an event handler to the form.
        form.addSubmitHandler(new FormPanel.SubmitHandler() {
            public void onSubmit(FormPanel.SubmitEvent event) {
                // This event is fired just before the form is submitted. We can take
                // this opportunity to perform validation.
                if (tb.getText().length() == 0) {
                    Window.alert("The text box must not be empty");
                    event.cancel();
                }
            }
        });

        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                String pageStr = event.getResults();
                if (pageStr.contains("login") == true) {
                    Window.Location.replace(GWT.getHostPageBaseURL() + "login.html?error=true");
                }
            }
        });
        RootPanel.get().add(form);
    }
}