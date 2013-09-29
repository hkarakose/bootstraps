package com.gamenism.server;

import com.gamenism.client.service.UserService;
import com.gamenism.dao.ActiveRecord;
import com.gamenism.model.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 6:10 PM
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    ActiveRecord<User> activeRecord;

    public UserServiceImpl() {
        activeRecord = new ActiveRecord<User>(User.class);
    }

    public User find(Long employeeId) {
        User o = activeRecord.find(employeeId);
        return o;
    }

    public void create(User user) {
        activeRecord.persist(user);
    }

    public User findByEmail(String email) {
        return activeRecord.getSingleResultFromNamedQuery("findUserByEmail", email);
    }

}
