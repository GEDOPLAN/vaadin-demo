package de.gedoplan.demos.vaadin_demo.business.vortrag;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Vortrag implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String titel;
	
	private String beschreibung;
	
	@NotNull
	private LocalDateTime datum;
	
	@NotNull
	private Duration dauer;
	
	private String ort;
	
	private String referent;
	
	@Version
	private long version;

	public Long getId() {
		return id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	
	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public Duration getDauer() {
		return dauer;
	}

	public void setDauer(Duration dauer) {
		this.dauer = dauer;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getReferent() {
		return referent;
	}

	public void setReferent(String referent) {
		this.referent = referent;
	}
	
	
}
