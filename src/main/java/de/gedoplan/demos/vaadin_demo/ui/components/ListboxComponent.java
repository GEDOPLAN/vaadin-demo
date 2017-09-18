package de.gedoplan.demos.vaadin_demo.ui.components;

import java.util.Arrays;

import org.vaadin.elements.AbstractElementComponent;
import org.vaadin.elements.Element;
import org.vaadin.elements.ElementIntegration;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Root;

import com.vaadin.annotations.HtmlImport;

import de.gedoplan.demos.vaadin_demo.ui.components.ListboxElement.Item;

@HtmlImport("vaadin://bower_components/paper-listbox/paper-listbox.html")
@HtmlImport("vaadin://bower_components/paper-item/paper-item.html")
public class ListboxComponent extends AbstractElementComponent {

  public static interface ValueChangeListener {
    void valueChange(String option);
  }

  private ListboxElement element;
  private String[] items;

  public ListboxComponent(boolean multi, String... items) {
    element = ListboxElement.create();
    element.setMulti(multi);
    this.items = items;
    if (items != null) {
      for (String item : items) {
        Element e = Elements.create(Item.class);
        e.setInnerText(item);
        element.appendChild(e);
        e.setAttribute("role", "listitem");
      }
    }

    Root root = ElementIntegration.getRoot(this);
    root.appendChild(element);
  }

  public void selectNext() {
    element.eval("e.selectNext()");
  }

  public void selectPrevious() {
    element.eval("e.selectPrevious()");
  }

  public String getSelected() {
    return items[Integer.parseInt(element.getSelected())];
  }
  
  public void setSelected(String item) {
	  element.setSelected(String.valueOf(Arrays.asList(items).indexOf(item)));
  }

  public void addValueChangeListener(ValueChangeListener listener) {
    element.addEventListener("iron-select",
        args -> listener.valueChange(getSelected()));
  }

}
