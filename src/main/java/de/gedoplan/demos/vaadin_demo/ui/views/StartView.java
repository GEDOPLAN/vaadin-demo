package de.gedoplan.demos.vaadin_demo.ui.views;

import javax.annotation.PostConstruct;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.gedoplan.demos.vaadin_demo.ui.navigation.ViewIDs;

@CDIView(ViewIDs.start)
public class StartView extends VerticalLayout implements View {

	@PostConstruct
	private void init() {
		Label label = new Label("Startseite");
		label.setStyleName(ValoTheme.LABEL_H2);
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(label);
		setSizeFull();
	}
}
