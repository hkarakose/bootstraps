package com.gamenism.client.service;

import com.gamenism.model.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * User: halil
 * Date: 9/27/13
 * Time: 11:11 PM
 */
@RemoteServiceRelativePath("UserService")
public interface UserService extends RemoteService {
    User find(Long id);

    void create(User user);

    User findByEmail(String email);

    public static class App {
        private static final UserServiceAsync ourInstance = (UserServiceAsync) GWT.create(UserService.class);

        public static UserServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
