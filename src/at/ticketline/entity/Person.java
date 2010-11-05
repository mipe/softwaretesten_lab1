package at.ticketline.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * 
 * Person ist eine abstrakte Superklasse fuer Mitarbeiter und Kunden.
 * 
 */
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYP", discriminatorType = DiscriminatorType.STRING)
public abstract class Person extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7257677923410786298L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(nullable = false, length = 50)
	@Size(max = 50)
	@NotNull
	protected String nachname;

	@Column(nullable = false, length = 30)
	@Size(max = 30)
	@NotNull
	protected String vorname;

	@Column(length = 30)
	@Size(max = 30)
	protected String titel;

	@Enumerated(EnumType.ORDINAL)
	@NotNull
	protected Geschlecht geschlecht;

	@Temporal(TemporalType.DATE)
	@Past
	protected Date geburtsdatum;

	@Embedded
	@Valid
	protected Adresse adresse;

	@Size(max = 255)
	protected String telnr;

	@Size(max = 255)
	@Email
	protected String email;

	@Pattern(regexp = "^[0-9]{5}$")
	protected String blz;

	@Pattern(regexp = "^[0-9]{3,16}$")
	protected String kontonr;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORT_ID")
	protected Ort ort;

	/** full constructor */
	public Person(Integer id, String nname, String vname, String titel,
			Geschlecht geschlecht, Date geburtsdatum,
			Adresse adresse, String telnr, String email, Ort ort) {
		this.id = id;
		this.nachname = nname;
		this.vorname = vname;
		this.titel = titel;
		this.geschlecht = geschlecht;
		this.geburtsdatum = geburtsdatum;
		this.adresse = adresse;
		this.telnr = telnr;
		this.email = email;
		this.ort = ort;
	}

	/** default constructor */
	public Person() {
		// do nothing
	}

	/** minimal constructor */
	public Person(Integer id, String nname, String vname,
			Geschlecht geschlecht, Adresse adresse, Ort ort) {
		this.id = id;
		this.nachname = nname;
		this.vorname = vname;
		this.geschlecht = geschlecht;
		this.adresse = adresse;
		this.ort = ort;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Nachname des Kartenbesitzers
	 */
	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Vorname des Kartenbesitzers
	 */
	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Anrede des Kartenbesitzers
	 */
	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * Geschlecht der Person
	 */
	public Geschlecht getGeschlecht() {
		return this.geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Date getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * Telefonnummer des Kartenbesitzers
	 */
	public String getTelnr() {
		return this.telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}

	/**
	 * Email-Adresse der Person
	 */
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBlz() {
		return this.blz;
	}

	public void setBlz(String blz) {
		this.blz = blz;
	}

	public String getKontonr() {
		return this.kontonr;
	}

	public void setKontonr(String kontonr) {
		this.kontonr = kontonr;
	}

	public Ort getOrt() {
		return this.ort;
	}

	public void setOrtverk(Ort ort) {
		this.ort = ort;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.adresse != null) {
			builder.append("adresse=").append(this.adresse).append(", ");
		}
		if (this.blz != null) {
			builder.append("blz=").append(this.blz).append(", ");
		}
		if (this.email != null) {
			builder.append("email=").append(this.email).append(", ");
		}
		if (this.geburtsdatum != null) {
			builder.append("geburtsdatum=").append(this.geburtsdatum).append(
					", ");
		}
		if (this.geschlecht != null) {
			builder.append("geschlecht=").append(this.geschlecht).append(", ");
		}
		if (this.kontonr != null) {
			builder.append("kontonr=").append(this.kontonr).append(", ");
		}
		if (this.nachname != null) {
			builder.append("nachname=").append(this.nachname).append(", ");
		}
		if (this.ort != null) {
			builder.append("ort=").append(this.ort).append(", ");
		}
		if (this.telnr != null) {
			builder.append("telnr=").append(this.telnr).append(", ");
		}
		if (this.titel != null) {
			builder.append("titel=").append(this.titel).append(", ");
		}
		if (this.vorname != null) {
			builder.append("vorname=").append(this.vorname);
		}
		builder.append("]");
		return builder.toString();
	}

}
