package com.gamenism.client.util;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

/**
 * User: halil
 * Date: 10/13/13
 * Time: 4:25 PM
 */
public abstract class SecureAsyncCallback<T> implements AsyncCallback<T> {
    int SC_UNAUTHORIZED = 401;
    int SC_FORBIDDEN = 403;

    public void onFailure(Throwable caught) {
        if (caught instanceof StatusCodeException) {
            StatusCodeException statusCodeException = (StatusCodeException) caught;
            int statusCode = statusCodeException.getStatusCode();
            if (statusCode == SC_FORBIDDEN) {
                //login required
                System.out.println("LOGIN REQUIRED");
                History.newItem("LOGIN");
            }
        } else {
            caught.printStackTrace();
        }
    }
}
