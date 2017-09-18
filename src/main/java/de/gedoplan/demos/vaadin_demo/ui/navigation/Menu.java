package de.gedoplan.demos.vaadin_demo.ui.navigation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.ui.MenuBar;

public class Menu extends MenuBar {
	
	@Navigation
	@Inject
	private javax.enterprise.event.Event<String> navigationEvent;

	@PostConstruct
	private void init() {
		addItem("Start", item -> navigationEvent.fire(ViewIDs.start));
		addItem("Vorträge", item -> navigationEvent.fire(ViewIDs.vortraege));
		addItem("Countries", item -> navigationEvent.fire(ViewIDs.dataProvider));
		addItem("Eigene Komponenten", item -> navigationEvent.fire(ViewIDs.customComponents));
	}
}
