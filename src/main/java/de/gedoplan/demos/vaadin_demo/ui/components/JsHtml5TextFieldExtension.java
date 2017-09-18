package de.gedoplan.demos.vaadin_demo.ui.components;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.TextField;

import elemental.json.JsonArray;

@JavaScript("extensions.js")
public class JsHtml5TextFieldExtension extends AbstractJavaScriptExtension {

  public void extend(TextField textField) {
    super.extend(textField);
    addFunction("wertGeandert", new JavaScriptFunction() {
      @Override
      public void call(JsonArray arguments) {
        System.out.println("Wert geändert: " + arguments.getString(0));
      }
    });

  }

  @Override
  protected Class<? extends ClientConnector> getSupportedParentType() {

    return TextField.class;
  }

  @Override
  public JsHtml5TextFieldExtensionState getState() {

    return (JsHtml5TextFieldExtensionState) super.getState();
  }

  public void setInputType(InputType inputType) {
    getState().inputType = inputType.name();
  }
}
