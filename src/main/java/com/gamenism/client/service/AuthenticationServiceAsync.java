package com.gamenism.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface AuthenticationServiceAsync {
    void retrieveUsername(AsyncCallback<String> async);
}
