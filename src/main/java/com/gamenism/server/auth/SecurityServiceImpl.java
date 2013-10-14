package com.gamenism.server.auth;

import com.gamenism.client.service.SecurityService;
import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.User;
import com.gamenism.server.RemoteServiceServlet2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: halil
 * Date: 9/30/13
 * Time: 9:31 PM
 */
public class SecurityServiceImpl extends RemoteServiceServlet2 implements SecurityService {
    ActiveRecord<User> activeRecord;
    private String authentication;

    public SecurityServiceImpl() {
        activeRecord = new ActiveRecord<User>(User.class);
    }

    public String retrieveUsername() {
        String authentication = getAuthentication();

        if (authentication == null) {
            System.out.println("Not logged in");
            return null;
        } else {
            return authentication;
        }
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
