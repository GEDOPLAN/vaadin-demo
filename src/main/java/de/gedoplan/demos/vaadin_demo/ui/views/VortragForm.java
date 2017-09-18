package de.gedoplan.demos.vaadin_demo.ui.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import de.gedoplan.demos.vaadin_demo.business.vortrag.Vortrag;
import de.gedoplan.demos.vaadin_demo.business.vortrag.VortragService;
import de.gedoplan.demos.vaadin_demo.ui.VortragUpdateBroadcast;
import de.gedoplan.demos.vaadin_demo.ui.converter.LongDurationConverter;

public class VortragForm extends FormLayout {
	
	private TextField titel = new TextField("Titel");
	
	private TextArea beschreibung = new TextArea("Beschreibung");
	
	private DateTimeField datum = new DateTimeField("Termin");
	
	private TextField dauer = new TextField("Dauer");
	
	private TextField ort = new TextField("Ort");
	
	private TextField referent = new TextField("Referent");
	
	private BeanValidationBinder<Vortrag> binder = new BeanValidationBinder<>(Vortrag.class);
	private Vortrag vortrag = new Vortrag();
	
	@Inject
	private VortragService vortragService;
	@Inject
	private VortragUpdateBroadcast broadcast;

	@PostConstruct
	private void init() {
		binder.forMemberField(dauer).withNullRepresentation("").withConverter(new StringToLongConverter("Keine gültige Anzahl Minuten")).withConverter(new LongDurationConverter());
		binder.forMemberField(titel).withNullRepresentation("");
		binder.forMemberField(ort).withNullRepresentation("");
		binder.forMemberField(referent).withNullRepresentation("");
		binder.bindInstanceFields(this);
		Button speichern = new Button("Speichern", e -> speichern());
		Button reset = new Button("Verwerfen", e -> binder.readBean(vortrag));
		Button neu = new Button("Neuer Vortrag", e -> setVortrag(new Vortrag()));
		addComponents(titel,beschreibung,datum,dauer,ort,referent,speichern,reset,neu);
		setSizeUndefined();
	}
	
	public void setVortrag(Vortrag vortrag) {
		this.vortrag = vortrag;
		binder.readBean(vortrag);
	}
	
	public void speichern() {
		if(binder.writeBeanIfValid(vortrag)) {
			vortrag = vortragService.update(vortrag);
			broadcast.broadcast(view -> view.loadItems());
			setVortrag(new Vortrag());
		}		
	}
}
