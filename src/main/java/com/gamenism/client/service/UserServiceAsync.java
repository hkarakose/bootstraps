package com.gamenism.client.service;

import com.gamenism.model.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
    void create(User user, AsyncCallback<Void> async);

    void find(Long id, AsyncCallback<User> async);

    void findByEmail(String email, AsyncCallback<User> async);

}
