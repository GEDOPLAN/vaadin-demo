package de.gedoplan.demos.vaadin_demo.ui.components;

import org.vaadin.elements.Element;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Tag;
import org.vaadin.elements.UpdatedBy;

@Tag("paper-listbox")
public interface ListboxElement extends Element {

  static ListboxElement create() {
    return Elements.create(ListboxElement.class);
  }

  public void setMulti(boolean multi);

  @UpdatedBy("iron-select")
  @UpdatedBy("iron-deselect")
  public String getSelected();
  
  public void setSelected(String s);

  @Tag("paper-item")
  public interface Item extends Element {

  }
}
