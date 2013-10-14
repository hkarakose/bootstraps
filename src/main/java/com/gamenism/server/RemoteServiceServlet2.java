package com.gamenism.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: Admin
 * Date: 8/13/12
 * Time: 3:45 PM
 */
public abstract class RemoteServiceServlet2 extends RemoteServiceServlet {
    protected Logger logger = Logger.getLogger("com.maksiyazilim.chat");

    private String getSessionAttribute(String attributeName) {
        return String.valueOf(getSession().getAttribute(attributeName));
    }

    public HttpSession getSession() {
        HttpServletRequest request = getThreadLocalRequest();
        return request.getSession();
    }
}
