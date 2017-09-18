package de.gedoplan.demos.vaadin_demo.ui.components;

import org.vaadin.elements.Element;
import org.vaadin.elements.ElementIntegration;

import com.vaadin.ui.TextField;

public class Html5InputElement extends TextField {

  private Element element;

  public Html5InputElement() {
    element = ElementIntegration.getRoot(this);
    element.addEventListener("change", array -> System.out.println(getValue()));
  }

  public void setInputType(InputType type) {
    element.setAttribute("type", type.name());
  }

  public InputType getInputType() {
    return InputType.valueOf(element.getAttribute("type"));
  }

}
