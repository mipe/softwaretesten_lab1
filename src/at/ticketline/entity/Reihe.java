package at.ticketline.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Jeder Saal eines Auffuehrungsortes besteht aus mehreren Reihen. Fuer jede
 * Reihe wird gespeichert, wie viele Plaetze sie hat, und welche Nummer der
 * erste Platz der Reihe hat. Ausserdem wird gespeichert ob es sich um eine
 * Reihe mit Sitzplaetzen oder mit Stehplaetzen handelt.
 * 
 */
@Entity
public class Reihe extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7882540772780561589L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	@Size(max = 255)
	@NotNull
	private String bezeichnung;

	@Column(nullable = false)
	@Min(value = 1)
	@NotNull
	private Integer startplatz = 1;

	@Column(nullable = false)
	@Min(value = 0)
	@NotNull
	private Integer anzplaetze;

	@Column(nullable = false)
	@NotNull
	private Boolean sitzplatz = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAAL_ID")
	private Saal saal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KATEGORIE_ID")
	private Kategorie kategorie;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reihe")
	private Set<Platz> plaetze = new HashSet<Platz>();

	/** full constructor */
	public Reihe(Integer id, String bezeichnung, Integer startplatz,
			Integer anzplaetze, Boolean sitzplatz, Saal saal,
			Kategorie kategorie, Set<Platz> plaetze) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.startplatz = startplatz;
		this.anzplaetze = anzplaetze;
		this.sitzplatz = sitzplatz;
		this.saal = saal;
		this.kategorie = kategorie;
		this.plaetze = plaetze;
	}

	/** default constructor */
	public Reihe() {
		// do nothing
	}

	/** minimal constructor */
	public Reihe(Integer id, String bezeichnung, Integer anzplaetze, Saal saal,
			Kategorie kategorie, Set<Platz> plaetze) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.anzplaetze = anzplaetze;
		this.saal = saal;
		this.kategorie = kategorie;
		this.plaetze = plaetze;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	/**
	 * erste Platznummer der Reihe
	 */
	public Integer getStartplatz() {
		return this.startplatz;
	}

	public void setStartplatz(Integer startplatz) {
		this.startplatz = startplatz;
	}

	/**
	 * Anzahl der Plaetze > 0
	 */
	public Integer getAnzplaetze() {
		return this.anzplaetze;
	}

	public void setAnzplaetze(Integer anzplaetze) {
		this.anzplaetze = anzplaetze;
	}

	/**
	 * Sitzplatz (TRUE) oder Stehplatz (FALSE)
	 */
	public Boolean isSitzplatz() {
		return this.sitzplatz;
	}

	public void setSitzplatz(Boolean sitzplatz) {
		this.sitzplatz = sitzplatz;
	}

	public Saal getSaal() {
		return this.saal;
	}

	public void setSaal(Saal saal) {
		this.saal = saal;
	}

	public Kategorie getKategorie() {
		return this.kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}

	public Set<Platz> getPlaetze() {
		return this.plaetze;
	}

	public void setBelegungen(Set<Platz> plaetze) {
		this.plaetze = plaetze;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reihe [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.anzplaetze != null) {
			builder.append("anzplaetze=").append(this.anzplaetze).append(", ");
		}
		if (this.bezeichnung != null) {
			builder.append("bezeichnung=").append(this.bezeichnung)
					.append(", ");
		}
		if (this.sitzplatz != null) {
			builder.append("sitzplatz=").append(this.sitzplatz).append(", ");
		}
		if (this.startplatz != null) {
			builder.append("startplatz=").append(this.startplatz);
		}
		builder.append("]");
		return builder.toString();
	}

}
