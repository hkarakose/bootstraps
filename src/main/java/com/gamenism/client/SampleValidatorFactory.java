package com.gamenism.client;

import com.gamenism.model.Employee;
import com.gamenism.model.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import javax.validation.Validator;

public final class SampleValidatorFactory extends AbstractGwtValidatorFactory {

    /**
     * Validator marker for the Validation Sample project. Only the classes and groups listed
     * in the {@link GwtValidation} annotation can be validated.
     */
    @GwtValidation({User.class, Employee.class})
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }
}