package com.gamenism.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * User: halil
 * Date: 10/14/13
 * Time: 10:00 PM
 */
public class Layout extends Composite {
    @UiField
    VerticalPanel verticalPanel;

    public void add(Widget button) {
        verticalPanel.add(button);
    }
    interface LayoutUiBinder extends UiBinder<Widget, Layout> {
    }
    private static LayoutUiBinder ourUiBinder = GWT.create(LayoutUiBinder.class);

    public Layout() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}