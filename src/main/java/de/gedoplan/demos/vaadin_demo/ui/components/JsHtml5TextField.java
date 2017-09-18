package de.gedoplan.demos.vaadin_demo.ui.components;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

@JavaScript("JsHtml5TextFieldConnector.js")
public class JsHtml5TextField extends AbstractJavaScriptComponent {

  public JsHtml5TextField() {
    addFunction("wertGeandert", new JavaScriptFunction() {
      @Override
      public void call(JsonArray arguments) {
        System.out.println("Wert geändert: " + arguments.getString(0));
      }
    });
  }

  public void setInputType(InputType type) {
    getState().inputType = type.name();
  }

  @Override
  public JsHtml5TextFieldState getState() {
    return (JsHtml5TextFieldState) super.getState();
  }

}
