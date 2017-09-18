package de.gedoplan.demos.vaadin_demo.ui.views;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ErrorView extends VerticalLayout implements View {

	@PostConstruct
	private void init() {
		Label errorMessage = new Label("Ansicht nicht gefunden !");
		errorMessage.addStyleName(ValoTheme.LABEL_FAILURE);
		addComponent(errorMessage);
		setSizeFull();
	}
}
