package com.gamenism.server;


import org.hibernate.validator.HibernateValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.validation.Validation;

/**
 * Registers the validation provider
 */
@SuppressWarnings("serial")
public class BootstrapValidationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        Validation.byProvider(HibernateValidator.class).configure();
    }
}
