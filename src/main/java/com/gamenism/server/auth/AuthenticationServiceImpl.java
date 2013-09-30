package com.gamenism.server.auth;

import com.gamenism.client.service.AuthenticationService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User: halil
 * Date: 9/30/13
 * Time: 9:31 PM
 */
public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {

    public String retrieveUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            System.out.println("Not logged in");
            return null;
        } else {
            return (String) authentication.getPrincipal();
        }
    }
}
