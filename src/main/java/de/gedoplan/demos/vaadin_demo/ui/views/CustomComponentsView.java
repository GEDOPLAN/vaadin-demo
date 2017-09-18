package de.gedoplan.demos.vaadin_demo.ui.views;

import javax.annotation.PostConstruct;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.gedoplan.demos.vaadin_demo.ui.components.Html5InputElement;
import de.gedoplan.demos.vaadin_demo.ui.components.InputType;
import de.gedoplan.demos.vaadin_demo.ui.components.JsHtml5TextField;
import de.gedoplan.demos.vaadin_demo.ui.components.JsHtml5TextFieldExtension;
import de.gedoplan.demos.vaadin_demo.ui.components.ListboxComponent;
import de.gedoplan.demos.vaadin_demo.ui.navigation.ViewIDs;

@CDIView(ViewIDs.customComponents)
public class CustomComponentsView extends VerticalLayout implements View {

	@PostConstruct
	private void init() {

		addComponent(new Label("JavaScript Component Demo"));
		JsHtml5TextField jsHtml5TextField = new JsHtml5TextField();
		addComponent(jsHtml5TextField);
		ComboBox<InputType> comboBox2 = new ComboBox<>("Input Typ");
		comboBox2.setItems(InputType.values());
		comboBox2.addValueChangeListener(e -> jsHtml5TextField.setInputType(e.getValue()));
		addComponent(comboBox2);
		addComponent(new Label("<hr/>", ContentMode.HTML));

		addComponent(new Label("JavaScript Extension Demo"));
		TextField textField = new TextField();
		JsHtml5TextFieldExtension jsHtml5TextFieldExtension = new JsHtml5TextFieldExtension();
		jsHtml5TextFieldExtension.extend(textField);
		addComponent(textField);
		ComboBox<InputType> comboBox3 = new ComboBox<>("Input Typ");
		comboBox3.setItems(InputType.values());
		comboBox3.addValueChangeListener(e -> jsHtml5TextFieldExtension.setInputType(e.getValue()));
		addComponent(comboBox3);
		addComponent(new Label("<hr/>", ContentMode.HTML));

		addComponent(new Label("Elements Addon Demo"));
		Html5InputElement html5InputElement = new Html5InputElement();
		addComponent(html5InputElement);
		ComboBox<InputType> comboBox4 = new ComboBox<>("Input Typ");
		comboBox4.setItems(InputType.values());
		comboBox4.addValueChangeListener(e -> html5InputElement.setInputType(e.getValue()));
		addComponent(comboBox4);
		addComponent(new Label("<hr/>", ContentMode.HTML));

		addComponent(new Label("Elements Addon Demo2"));
		ListboxComponent listboxComponent = new ListboxComponent(false, "Erstes Item", "Zweites Item", "Drittes Item", "Viertes Item");
	    listboxComponent.setSelected("Zweites Item");
		listboxComponent.addValueChangeListener(e -> System.out.println(e));
	    addComponent(listboxComponent);
	    addComponent(new Button("Vorheriges", e -> listboxComponent.selectPrevious()));
	    addComponent(new Button("Nächstes", e -> listboxComponent.selectNext()));


		// JsCanvas jsCanvas = new JsCanvas();
		// jsCanvas.setWidth(300,Unit.PIXELS);
		// jsCanvas.setHeight(300,Unit.PIXELS);
		// verticalLayout.addComponent(jsCanvas);
		// jsCanvas.setColor("#FF0000");
		// jsCanvas.drawLine(new Point(50, 50), new Point(250, 250));
		// verticalLayout.addComponent(new Button("df",e -> jsCanvas.drawLine(new
		// Point(50, 50), new Point(100, 100))));

	}

}