package de.gedoplan.demos.vaadin_demo.ui;

import javax.inject.Inject;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.gedoplan.demos.vaadin_demo.ui.navigation.Menu;
import de.gedoplan.demos.vaadin_demo.ui.navigation.Navigation;

@CDIUI("ui")
@Push(PushMode.AUTOMATIC)
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents.js")
public class DemoUi extends UI {
	
	@Inject
	private Menu menu;
	
	@Navigation
	@Inject
	private javax.enterprise.event.Event<UI> startEvent;	

	private VerticalLayout mainContent;
	
	private VerticalLayout pageContent;
	
	@Override
	protected void init(VaadinRequest request) {
		pageContent = new VerticalLayout();
		pageContent.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		Label headline = new Label("Vaadin Demo");
		headline.setStyleName(ValoTheme.LABEL_H1);
		pageContent.addComponent(headline);
		pageContent.addComponent(menu);
		mainContent = new VerticalLayout();	
		pageContent.addComponent(mainContent);
		pageContent.setExpandRatio(mainContent, 1);
		setContent(pageContent);
		startEvent.fire(this);
	}
	
	
	public ComponentContainer getMainContent() {
		return mainContent;
	}

}
