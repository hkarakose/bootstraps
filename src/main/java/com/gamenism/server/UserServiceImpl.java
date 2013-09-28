package com.gamenism.server;

import com.gamenism.client.service.UserService;
import com.gamenism.model.ActiveRecord;
import com.gamenism.model.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 6:10 PM
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    ActiveRecord activeRecord;

    public UserServiceImpl() {
        activeRecord = new ActiveRecord();
    }

    public User find(Long employeeId) {
        return activeRecord.find(User.class, employeeId);
    }

    public void create(User user) {
        activeRecord.persist(user);
    }
}
