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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Jedes Theaterstueck, jede Oper, jeder Kinofilm usw. des Systems wird in Form
 * einer Veranstaltung gespeichert. Von jeder Veranstaltung werden dann mehrere
 * Auffuehrungen in verschiedenen Saelen zu verschiedenen Zeiten verwaltet.
 * 
 */
@Entity
public class Veranstaltung extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2376468057851018229L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 30)
	@Size(max = 30)
	@NotNull
	private String bezeichnung;

	@Column(nullable = false, length = 30)
	@Size(max = 30)
	@NotNull
	private String kategorie;

	@Column(length = 30)
	@Size(max = 30)
	private String subkategorie;

	private Integer jahrerstellung;

	@Column(length = 30)
	@Size(max = 30)
	private String spracheton;

	@Column(length = 30)
	@Size(max = 30)
	private String spracheut;

	@Column(nullable = false)
	@Min(value = 0)
	@NotNull
	private Integer dauer;

	@Column(length = 30)
	@Size(max = 30)
	private String freigabe;

	@Column(length = 100)
	@Size(max = 100)
	private String abbildung;

	@Lob
	private String inhalt;

	@Size(max = 255)
	private String kritik;

	@Size(max = 255)
	private String bewertung;

	@Size(max = 255)
	private String hinweis;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "veranstaltung")
	private Set<Artikel> artikel = new HashSet<Artikel>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "veranstaltung")
	private Set<Auffuehrung> auffuehrungen = new HashSet<Auffuehrung>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "veranstaltung", cascade = CascadeType.ALL)
	private Set<Engagement> engagements = new HashSet<Engagement>();

	/** full constructor */
	public Veranstaltung(Integer id, String bezeichnung, String kategorie,
			String subkategorie, Integer jahrerstellung, String spracheton,
			String spracheut, Integer dauer, String freigabe, String abbildung,
			String inhalt, String kritik, String bewertung, String hinweis,
			Set<Artikel> artikel, Set<Auffuehrung> auffuehrungen,
			Set<Engagement> engagements) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.kategorie = kategorie;
		this.subkategorie = subkategorie;
		this.jahrerstellung = jahrerstellung;
		this.spracheton = spracheton;
		this.spracheut = spracheut;
		this.dauer = dauer;
		this.freigabe = freigabe;
		this.abbildung = abbildung;
		this.inhalt = inhalt;
		this.kritik = kritik;
		this.bewertung = bewertung;
		this.hinweis = hinweis;
		this.artikel = artikel;
		this.auffuehrungen = auffuehrungen;
		this.engagements = engagements;
	}

	/** default constructor */
	public Veranstaltung() {
		// do nothing
	}

	/** minimal constructor */
	public Veranstaltung(Integer id, String bezeichnung, String kategorie,
			Integer dauer, String inhalt, Set<Artikel> artikel,
			Set<Auffuehrung> auffuehrungen, Set<Engagement> engagements) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.kategorie = kategorie;
		this.dauer = dauer;
		this.inhalt = inhalt;
		this.artikel = artikel;
		this.auffuehrungen = auffuehrungen;
		this.engagements = engagements;
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

	public String getKategorie() {
		return this.kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	/**
	 * Unterteilung von Kategorie; z.B. Thriller, Drama
	 */
	public String getSubkategorie() {
		return this.subkategorie;
	}

	public void setSubkategorie(String subkategorie) {
		this.subkategorie = subkategorie;
	}

	/**
	 * Erstellungsjahr <= Heuer
	 */
	public Integer getJahrerstellung() {
		return this.jahrerstellung;
	}

	public void setJahrerstellung(Integer jahrerstellung) {
		this.jahrerstellung = jahrerstellung;
	}

	/**
	 * z.B.: Deutsch, Englisch
	 */
	public String getSpracheton() {
		return this.spracheton;
	}

	public void setSpracheton(String spracheton) {
		this.spracheton = spracheton;
	}

	/**
	 * Sprache des Untertitels; z.B.: Deutsch, English
	 */
	public String getSpracheut() {
		return this.spracheut;
	}

	public void setSpracheut(String spracheut) {
		this.spracheut = spracheut;
	}

	/**
	 * 0 < Dauer in Minuten <= 999
	 */
	public Integer getDauer() {
		return this.dauer;
	}

	public void setDauer(Integer dauer) {
		this.dauer = dauer;
	}

	/**
	 * Altersfreigabe; z.B.: ab 18
	 */
	public String getFreigabe() {
		return this.freigabe;
	}

	public void setFreigabe(String freigabe) {
		this.freigabe = freigabe;
	}

	/**
	 * Speicherort eines Bilds zur Veranstaltung
	 */
	public String getAbbildung() {
		return this.abbildung;
	}

	public void setAbbildung(String abbildung) {
		this.abbildung = abbildung;
	}

	/**
	 * kurze Inhaltsangabe zur Veranstaltung
	 */
	public String getInhalt() {
		return this.inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	/**
	 * kurze Kritik zur Veranstaltung
	 */
	public String getKritik() {
		return this.kritik;
	}

	public void setKritik(String kritik) {
		this.kritik = kritik;
	}

	/**
	 * kurze Bewertung zur Veranstaltung
	 */
	public String getBewertung() {
		return this.bewertung;
	}

	public void setBewertung(String bewertung) {
		this.bewertung = bewertung;
	}

	/**
	 * kurzer Hinweis zur Veranstaltung
	 */
	public String getHinweis() {
		return this.hinweis;
	}

	public void setHinweis(String hinweis) {
		this.hinweis = hinweis;
	}

	public Set<Artikel> getArtikel() {
		return this.artikel;
	}

	public void setArtikel(Set<Artikel> artikel) {
		this.artikel = artikel;
	}

	public Set<Auffuehrung> getAuffuehrungen() {
		return this.auffuehrungen;
	}

	public void setAuffuehrungen(Set<Auffuehrung> auffuehrungen) {
		this.auffuehrungen = auffuehrungen;
	}

	public Set<Engagement> getEngagements() {
		return this.engagements;
	}

	public void setEngagements(Set<Engagement> engagements) {
		this.engagements = engagements;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Veranstaltung [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.abbildung != null) {
			builder.append("abbildung=").append(this.abbildung).append(", ");
		}
		if (this.bewertung != null) {
			builder.append("bewertung=").append(this.bewertung).append(", ");
		}
		if (this.bezeichnung != null) {
			builder.append("bezeichnung=").append(this.bezeichnung)
					.append(", ");
		}
		if (this.dauer != null) {
			builder.append("dauer=").append(this.dauer).append(", ");
		}
		if (this.freigabe != null) {
			builder.append("freigabe=").append(this.freigabe).append(", ");
		}
		if (this.hinweis != null) {
			builder.append("hinweis=").append(this.hinweis).append(", ");
		}
		if (this.inhalt != null) {
			builder.append("inhalt=").append(this.inhalt).append(", ");
		}
		if (this.jahrerstellung != null) {
			builder.append("jahrerstellung=").append(this.jahrerstellung)
					.append(", ");
		}
		if (this.kategorie != null) {
			builder.append("kategorie=").append(this.kategorie).append(", ");
		}
		if (this.kritik != null) {
			builder.append("kritik=").append(this.kritik).append(", ");
		}
		if (this.spracheton != null) {
			builder.append("spracheton=").append(this.spracheton).append(", ");
		}
		if (this.spracheut != null) {
			builder.append("spracheut=").append(this.spracheut).append(", ");
		}
		if (this.subkategorie != null) {
			builder.append("subkategorie=").append(this.subkategorie);
		}
		builder.append("]");
		return builder.toString();
	}

}
