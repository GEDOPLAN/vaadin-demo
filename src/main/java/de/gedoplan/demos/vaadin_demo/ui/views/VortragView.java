package de.gedoplan.demos.vaadin_demo.ui.views;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;

import de.gedoplan.demos.vaadin_demo.business.vortrag.Vortrag;
import de.gedoplan.demos.vaadin_demo.business.vortrag.VortragService;
import de.gedoplan.demos.vaadin_demo.ui.VortragUpdateBroadcast;
import de.gedoplan.demos.vaadin_demo.ui.navigation.ViewIDs;

@CDIView(ViewIDs.vortraege)
public class VortragView extends HorizontalLayout implements View {
	
	@Inject
	private VortragService vortragService;
	
	@Inject
	private VortragForm formular;
	
	private Grid<Vortrag> tabelle;
	
	@Inject
	private VortragUpdateBroadcast broadcast;

	@PostConstruct
	private void init() {
		tabelle = new Grid<>(Vortrag.class);
		tabelle.setColumnOrder("titel","ort","datum","dauer","referent");
		tabelle.getColumn("beschreibung").setHidden(true);
		tabelle.getColumn("id").setHidden(true);
		tabelle.setDataProvider((f,o,l) -> vortragService.findAll().stream(), vortragService::count);
		tabelle.setSizeFull();
		tabelle.setSelectionMode(SelectionMode.SINGLE);
		tabelle.addSelectionListener(e -> e.getFirstSelectedItem().ifPresent(formular::setVortrag));
		addComponents(formular,tabelle);
		setExpandRatio(tabelle, 1);
		setWidth(100,Unit.PERCENTAGE);
		broadcast.register(this);
	}
	
	@PreDestroy
	private void clear() {
		broadcast.remove(this);
	}
	
	public void loadItems() {
		tabelle.getDataProvider().refreshAll();
	}
}
