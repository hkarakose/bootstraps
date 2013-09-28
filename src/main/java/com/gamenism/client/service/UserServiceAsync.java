package com.gamenism.client.service;

import com.gamenism.model.Employee;
import com.gamenism.model.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface UserServiceAsync {
    void create(User user, AsyncCallback<Void> async);

    void find(Long id, AsyncCallback<User> async);

}
