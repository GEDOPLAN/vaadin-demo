package de.gedoplan.demos.vaadin_demo.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;

import de.gedoplan.demos.vaadin_demo.ui.views.VortragView;

@ApplicationScoped
public class VortragUpdateBroadcast {

	private Collection<VortragView> clients = Collections.synchronizedCollection(new ArrayList<VortragView>());
	
	public void register(VortragView view) {
		clients.add(view);
	}
	
	public void remove(VortragView view) {
		clients.remove(view);
	}
	
	public void broadcast(Consumer<VortragView> consumer) {
		clients.stream().forEach(c -> c.getUI().access(() -> consumer.accept(c)));
	}
}
