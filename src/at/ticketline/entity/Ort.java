package at.ticketline.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine Entitaet vom Typ Ort kann ein Auffuehrungsort, ein Kiosk oder eine
 * Verkaufsstelle sein. Handelt es sich um einen Auffuehrungsort (wie Kino,
 * Oper, usw), so kann der Ort auch eine Verkaufsstelle und/oder einen Kiosk
 * umfassen.
 * 
 */
@Entity
public class Ort extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1996868926110968597L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50)
	@Size(max = 50)
	@NotNull
	private String bezeichnung;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Ortstyp typ;

	@Embedded
	@Valid
	private Adresse adresse;

	@Column(length = 30)
	@Size(max = 30)
	private String telnr;

	@Column(length = 40)
	@Size(max = 40)
	private String besitzer;

	@Size(max = 255)
	private String oeffnungszeiten;

	private boolean kiosk;

	private boolean verkaufsstelle;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ort")
	private Set<Person> personen = new HashSet<Person>();
	public Set<Person> getPersonen() {
		return personen;
	}

	public void setPersonen(Set<Person> personen) {
		this.personen = personen;
	}

	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ort")
	private Set<Mitarbeiter> mitarbeiter = new HashSet<Mitarbeiter>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ort")
	private Set<Kunde> kunden = new HashSet<Kunde>();
*/
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ort")
	private Set<Saal> saele = new HashSet<Saal>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ort")
	@OrderBy("datum DESC")
	private Set<News> news = new HashSet<News>();

	/** full constructor */
	public Ort(Integer id, String bezeichnung, Ortstyp typ, Adresse adresse,
			String telnr, String besitzer, String oeffnungszeiten,
			boolean kiosk, boolean verkaufsstelle,
			/*Set<Mitarbeiter> mitarbeiter, */Set<Saal> saele, Set<News> news) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.typ = typ;
		this.adresse = adresse;
		this.telnr = telnr;
		this.besitzer = besitzer;
		this.oeffnungszeiten = oeffnungszeiten;
		this.kiosk = kiosk;
		this.verkaufsstelle = verkaufsstelle;
		//this.mitarbeiter = mitarbeiter;
		this.saele = saele;
		this.news = news;
	}

	/** default constructor */
	public Ort() {
		// do nothing
	}

	/** minimal constructor */
	public Ort(Integer id, String bezeichnung, Ortstyp typ, Adresse adresse,
			String plz, String bundesland, boolean kiosk,
			boolean verkaufsstelle, /* Set<Mitarbeiter> mitarbeiter,*/
			Set<Saal> saele) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.typ = typ;
		this.adresse = adresse;
		this.kiosk = kiosk;
		this.verkaufsstelle = verkaufsstelle;
		//this.mitarbeiter = mitarbeiter;
		this.saele = saele;
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

	public Ortstyp getOrtstyp() {
		return this.typ;
	}

	public void setOrtstyp(Ortstyp typ) {
		this.typ = typ;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * optionale Telefonnummer
	 */
	public String getTelnr() {
		return this.telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}

	/**
	 * z.B.: Kiba Kino GmbH
	 */
	public String getBesitzer() {
		return this.besitzer;
	}

	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

	/**
	 * z.B.: Fr. 18:00-23:00
	 */
	public String getOeffnungszeiten() {
		return this.oeffnungszeiten;
	}

	public void setOeffnungszeiten(String oeffnungszeiten) {
		this.oeffnungszeiten = oeffnungszeiten;
	}

	/**
	 * Ist Ort ein Kiosk? TRUE (= ja), FALSE (= nein)
	 */
	public boolean isKiosk() {
		return this.kiosk;
	}

	public void setKiosk(boolean kiosk) {
		this.kiosk = kiosk;
	}

	/**
	 * Ist Ort eine Verkaufsstelle? TRUE (= ja), FALSE (= nein)
	 */
	public boolean isVerkaufsstelle() {
		return this.verkaufsstelle;
	}

	public void setVerkaufsstelle(boolean verkaufsstelle) {
		this.verkaufsstelle = verkaufsstelle;
	}
/*
	public Set<Mitarbeiter> getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter(Set<Mitarbeiter> mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public Set<Kunde> getKunden() {
		return this.kunden;
	}

	public void setKunden(Set<Kunde> kunden) {
		this.kunden = kunden;
	}
*/
	
	
	
	public Set<Saal> getSaele() {
		return this.saele;
	}

	public void setSaele(Set<Saal> saele) {
		this.saele = saele;
	}

	public Set<News> getNews() {
		return this.news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ort [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.adresse != null) {
			builder.append("adresse=").append(this.adresse).append(", ");
		}
		if (this.besitzer != null) {
			builder.append("besitzer=").append(this.besitzer).append(", ");
		}
		if (this.bezeichnung != null) {
			builder.append("bezeichnung=").append(this.bezeichnung)
					.append(", ");
		}
		if (this.typ != null) {
			builder.append("ortstyp=").append(this.typ).append(", ");
		}
		builder.append("kiosk=").append(this.kiosk).append(", ");
		if (this.oeffnungszeiten != null) {
			builder.append("oeffnungszeiten=").append(this.oeffnungszeiten)
					.append(", ");
		}
		if (this.telnr != null) {
			builder.append("telnr=").append(this.telnr).append(", ");
		}
		builder.append("verkaufsstelle=").append(this.verkaufsstelle).append(
				"]");
		return builder.toString();
	}

}
