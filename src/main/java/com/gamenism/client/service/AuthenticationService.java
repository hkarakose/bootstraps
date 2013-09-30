package com.gamenism.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * User: halil
 * Date: 9/30/13
 * Time: 9:29 PM
 */
@RemoteServiceRelativePath("auth")
public interface AuthenticationService extends RemoteService {
    String retrieveUsername();

    public static class App{
        private static final AuthenticationServiceAsync authenticationService = GWT.create(AuthenticationService.class);

        public static AuthenticationServiceAsync getAuthenticationService() {
            return authenticationService;
        }
    }
}
