package org.bootstrap.controller;

import org.bootstrap.model.ActiveRecord;
import org.bootstrap.model.Dummy;
import org.bootstrap.model.Manufacturer;
import org.bootstrap.util.MessageFactory;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: halil
 * Date: 9/18/13
 * Time: 9:31 PM
 */
@ManagedBean(name = "dummyController")
@SessionScoped
@Configurable
public class DummyController implements Serializable {
    private String name = "Dummies";
    private List<Dummy> allDummies;
    private Dummy dummy;
    private boolean dataVisible = false;
    private List<String> columns;
    private HtmlPanelGrid createPanelGrid;
    private HtmlPanelGrid editPanelGrid;
    private HtmlPanelGrid viewPanelGrid;
    private boolean createDialogVisible = false;
    private FacesContext facesContext = FacesContext.getCurrentInstance();

    @PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        //TODO update
        columns.add("yearOfManufacture");
        columns.add("name");
        columns.add("manufacturer");

        dummy = new Dummy();
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public Dummy getDummy() {
        return dummy;
    }

    public void setDummy(Dummy dummy) {
        this.dummy = dummy;
    }

    public List<Dummy> getAllDummies() {
        return allDummies;
    }

    public void setAllDummies(List<Dummy> allDummies) {
        this.allDummies = allDummies;
    }

    public boolean isDataVisible() {
        System.out.println("this: " + this + ", dataVisible = " + dataVisible);
        return dataVisible;
    }

    public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

    public HtmlPanelGrid getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }

    public void setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }

    public HtmlPanelGrid getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }

    public void setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }

    public HtmlPanelGrid getViewPanelGrid() {
        return populateViewPanel();
    }

    public void setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }

    public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();

        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

        OutputLabel yearOfManufactureCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        yearOfManufactureCreateOutput.setFor("yearOfManufactureCreateInput");
        yearOfManufactureCreateOutput.setId("yearOfManufactureCreateOutput");
        yearOfManufactureCreateOutput.setValue("Year Of Manufacture:");
        htmlPanelGrid.getChildren().add(yearOfManufactureCreateOutput);

        Spinner yearOfManufactureCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        yearOfManufactureCreateInput.setId("yearOfManufactureCreateInput");
        yearOfManufactureCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.yearOfManufacture}", Integer.class));
        yearOfManufactureCreateInput.setRequired(true);

        htmlPanelGrid.getChildren().add(yearOfManufactureCreateInput);

        Message yearOfManufactureCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        yearOfManufactureCreateInputMessage.setId("yearOfManufactureCreateInputMessage");
        yearOfManufactureCreateInputMessage.setFor("yearOfManufactureCreateInput");
        yearOfManufactureCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(yearOfManufactureCreateInputMessage);

        OutputLabel nameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameCreateOutput.setFor("nameCreateInput");
        nameCreateOutput.setId("nameCreateOutput");
        nameCreateOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameCreateOutput);

        InputText nameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nameCreateInput.setId("nameCreateInput");
        nameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.name}", String.class));
        nameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameCreateInput);

        Message nameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameCreateInputMessage.setId("nameCreateInputMessage");
        nameCreateInputMessage.setFor("nameCreateInput");
        nameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameCreateInputMessage);

        OutputLabel manufacturerCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        manufacturerCreateOutput.setFor("manufacturerCreateInput");
        manufacturerCreateOutput.setId("manufacturerCreateOutput");
        manufacturerCreateOutput.setValue("Manufacturer:");
        htmlPanelGrid.getChildren().add(manufacturerCreateOutput);

        AutoComplete manufacturerCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        manufacturerCreateInput.setId("manufacturerCreateInput");
        manufacturerCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.manufacturer}", Manufacturer.class));
        manufacturerCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{dummyController.completeManufacturer}", List.class, new Class[] { String.class }));
        manufacturerCreateInput.setDropdown(true);
        manufacturerCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(manufacturerCreateInput);

        Message manufacturerCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        manufacturerCreateInputMessage.setId("manufacturerCreateInputMessage");
        manufacturerCreateInputMessage.setFor("manufacturerCreateInput");
        manufacturerCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(manufacturerCreateInputMessage);

        return htmlPanelGrid;
    }

    public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();

        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

        OutputLabel yearOfManufactureEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        yearOfManufactureEditOutput.setFor("yearOfManufactureEditInput");
        yearOfManufactureEditOutput.setId("yearOfManufactureEditOutput");
        yearOfManufactureEditOutput.setValue("Year Of Manufacture:");
        htmlPanelGrid.getChildren().add(yearOfManufactureEditOutput);

        Spinner yearOfManufactureEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        yearOfManufactureEditInput.setId("yearOfManufactureEditInput");
        yearOfManufactureEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.yearOfManufacture}", Integer.class));
        yearOfManufactureEditInput.setRequired(true);

        htmlPanelGrid.getChildren().add(yearOfManufactureEditInput);

        Message yearOfManufactureEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        yearOfManufactureEditInputMessage.setId("yearOfManufactureEditInputMessage");
        yearOfManufactureEditInputMessage.setFor("yearOfManufactureEditInput");
        yearOfManufactureEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(yearOfManufactureEditInputMessage);

        OutputLabel nameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nameEditOutput.setFor("nameEditInput");
        nameEditOutput.setId("nameEditOutput");
        nameEditOutput.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameEditOutput);

        InputText nameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nameEditInput.setId("nameEditInput");
        nameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.name}", String.class));
        nameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nameEditInput);

        Message nameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nameEditInputMessage.setId("nameEditInputMessage");
        nameEditInputMessage.setFor("nameEditInput");
        nameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nameEditInputMessage);

        OutputLabel manufacturerEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        manufacturerEditOutput.setFor("manufacturerEditInput");
        manufacturerEditOutput.setId("manufacturerEditOutput");
        manufacturerEditOutput.setValue("Manufacturer:");
        htmlPanelGrid.getChildren().add(manufacturerEditOutput);

        AutoComplete manufacturerEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        manufacturerEditInput.setId("manufacturerEditInput");
        manufacturerEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.manufacturer}", Manufacturer.class));
        manufacturerEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{dummyController.completeManufacturer}", List.class, new Class[] { String.class }));
        manufacturerEditInput.setDropdown(true);
        manufacturerEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(manufacturerEditInput);

        Message manufacturerEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        manufacturerEditInputMessage.setId("manufacturerEditInputMessage");
        manufacturerEditInputMessage.setFor("manufacturerEditInput");
        manufacturerEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(manufacturerEditInputMessage);

        return htmlPanelGrid;
    }

    public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();

        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

        HtmlOutputText yearOfManufactureLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        yearOfManufactureLabel.setId("yearOfManufactureLabel");
        yearOfManufactureLabel.setValue("Year Of Manufacture:");
        htmlPanelGrid.getChildren().add(yearOfManufactureLabel);

        HtmlOutputText yearOfManufactureValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        yearOfManufactureValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.yearOfManufacture}", String.class));
        htmlPanelGrid.getChildren().add(yearOfManufactureValue);

        HtmlOutputText nameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameLabel.setId("nameLabel");
        nameLabel.setValue("Name:");
        htmlPanelGrid.getChildren().add(nameLabel);

        HtmlOutputText nameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nameValue.setId("nameValue");
        nameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.name}", String.class));
        htmlPanelGrid.getChildren().add(nameValue);

        HtmlOutputText manufacturerLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        manufacturerLabel.setId("manufacturerLabel");
        manufacturerLabel.setValue("Manufacturer:");
        htmlPanelGrid.getChildren().add(manufacturerLabel);

        HtmlOutputText manufacturerValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        manufacturerValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{dummyController.dummy.manufacturer}", String.class));
        htmlPanelGrid.getChildren().add(manufacturerValue);

        return htmlPanelGrid;
    }

    public List<Manufacturer> completeManufacturer(String query) {
        List<Manufacturer> suggestions = new ArrayList<Manufacturer>();
        for (Manufacturer manufacturer : Manufacturer.values()) {
            if (manufacturer.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(manufacturer);
            }
        }
        return suggestions;
    }

    public String onEdit() {
        return null;
    }

    public boolean isCreateDialogVisible() {
        return createDialogVisible;
    }

    public void setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }

    public void handleDialogClose(CloseEvent event) {
        reset();
    }

    public void reset() {
        dummy = null;
        createDialogVisible = false;
    }

    public String displayList() {
        createDialogVisible = false;
        findAll();
        return "dummy";
    }

    public String displayCreateDialog() {
        dummy = new Dummy();
        createDialogVisible = true;
        return "dummy";
    }

    public String persist() {
        String message = "";
        if (dummy.getId() != null) {
            dummy.merge();
            message = "message_successfully_updated";
        } else {
            dummy.persist();
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");

        FacesMessage facesMessage = MessageFactory.getMessage(message, "Dummy");
        facesContext.addMessage(null, facesMessage);
        reset();
        return findAll();
    }

    public String delete() {
        dummy.remove(Dummy.class);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Dummy");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAll();
    }

    ActiveRecord activeRecord = new ActiveRecord();
    public String findAll() {
        allDummies = activeRecord.findAll(Dummy.class);
        dataVisible = !allDummies.isEmpty();
        return null;
    }
}
