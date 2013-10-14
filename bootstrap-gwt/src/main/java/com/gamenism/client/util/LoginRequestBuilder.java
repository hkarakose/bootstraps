package com.gamenism.client.util;

import com.gamenism.model.User;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.History;

import static com.gamenism.client.Views.*;
import static com.google.gwt.http.client.RequestBuilder.POST;

/**
 * User: halil
 * Date: 10/13/13
 * Time: 10:53 PM
 */
public class LoginRequestBuilder {
    private RequestBuilder requestBuilder;
    private Response loginResponse;
    private boolean loginSuccessful;

    public LoginRequestBuilder(User user) {
        requestBuilder = new RequestBuilder(POST, "/j_spring_security_check");
        requestBuilder.setHeader("Content-Type", "application/x-www-form-urlencoded");
        requestBuilder.setRequestData("j_username=" + user.getEmail() + "&j_password=" + user.getPassword()
                + "&_spring_security_remember_me=" + user.isRememberMe());

        requestBuilder.setCallback(new RequestCallback() {
            public void onResponseReceived(Request request, Response response) {
                loginResponse = response;
                System.out.println("requestBuilder.getRequestData() = " + requestBuilder.getRequestData());
                if (200 == response.getStatusCode()) {
                    loginSuccessful = true;
                    History.newItem(HOME.name());
                } else {
                    System.out.println("response.getStatusCode() = " + response.getStatusCode());
                    loginSuccessful = false;
                }
            }

            public void onError(Request request, Throwable exception) {
                loginSuccessful = false;
            }
        });
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public Response getLoginResponse() {
        return loginResponse;
    }

    public void send() {
        try {
            requestBuilder.send();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
}
