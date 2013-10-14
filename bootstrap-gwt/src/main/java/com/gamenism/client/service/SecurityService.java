package com.gamenism.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.io.IOException;

/**
 * User: halil
 * Date: 9/30/13
 * Time: 9:29 PM
 */
@RemoteServiceRelativePath("AuthenticationService")
public interface SecurityService extends RemoteService {
    String retrieveUsername();

    public static class App{
        private static final SecurityServiceAsync authenticationService = GWT.create(SecurityService.class);

        public static SecurityServiceAsync getAuthenticationService() {
            return authenticationService;
        }
    }
}
