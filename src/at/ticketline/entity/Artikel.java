package at.ticketline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Fuer jede Veranstaltung des Systems koennen Werbeartikel definiert werden.
 * Diese Artikel werden dann im Online-Shop und in den Ticketline-Kassen
 * verkauft. Jeder Artikel ist genau einer Kategorie zugeordnet.
 * 
 * @see ArtikelKategorie
 */
@Entity
public class Artikel extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5239407186115538897L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 30)
	@Size(max = 30)
	@NotNull
	private String kurzbezeichnung;

	@Lob
	private String beschreibung;

	@Column(nullable = false)
	@Min(value = 0)
	@Digits(integer = 8, fraction = 2)
	@NotNull
	private BigDecimal preis;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private ArtikelKategorie kategorie;

	@Size(max = 255)
	private String abbildung;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VERANSTALTUNG_ID", nullable = true)
	private Veranstaltung veranstaltung;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "artikel")
	private Set<BestellPosition> bestellPositionen = new HashSet<BestellPosition>();

	/** full constructor */
	public Artikel(Integer id, String kurzbezeichnung, String beschreibung,
			BigDecimal preis, ArtikelKategorie kategorie, String abbildung,
			Veranstaltung veranstaltung, Set<BestellPosition> bestellPositionen) {
		this.id = id;
		this.kurzbezeichnung = kurzbezeichnung;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.kategorie = kategorie;
		this.abbildung = abbildung;
		this.veranstaltung = veranstaltung;
		this.bestellPositionen = bestellPositionen;
	}

	/** default constructor */
	public Artikel() {
		// do nothing
	}

	/** minimal constructor */
	public Artikel(Integer id, String kurzbezeichnung, BigDecimal preis,
			ArtikelKategorie kategorie) {
		this.id = id;
		this.kurzbezeichnung = kurzbezeichnung;
		this.preis = preis;
		this.kategorie = kategorie;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Name des Artikels
	 */
	public String getKurzbezeichnung() {
		return this.kurzbezeichnung;
	}

	public void setKurzbezeichnung(String kurzbezeichnung) {
		this.kurzbezeichnung = kurzbezeichnung;
	}

	/**
	 * Beschreibung des Artikels
	 */
	public String getBeschreibung() {
		return this.beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Preis des Artikels; Preis >= 0
	 */
	public BigDecimal getPreis() {
		return this.preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	/**
	 * Kategorie des Artikels
	 */
	public ArtikelKategorie getKategorie() {
		return this.kategorie;
	}

	public void setKategorie(ArtikelKategorie kategorie) {
		this.kategorie = kategorie;
	}

	/**
	 * Speicherort eines Bilds des Artikels
	 */
	public String getAbbildung() {
		return this.abbildung;
	}

	public void setAbbildung(String abbildung) {
		this.abbildung = abbildung;
	}

	public Veranstaltung getVeranstaltung() {
		return this.veranstaltung;
	}

	public void setVeranstaltung(Veranstaltung veranstaltung) {
		this.veranstaltung = veranstaltung;
	}

	public Set<BestellPosition> getBestellPositionen() {
		return this.bestellPositionen;
	}

	public void setBestellPositionen(Set<BestellPosition> bestellPositionen) {
		this.bestellPositionen = bestellPositionen;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Artikel [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.abbildung != null) {
			builder.append("abbildung=").append(this.abbildung).append(", ");
		}
		if (this.beschreibung != null) {
			builder.append("beschreibung=").append(this.beschreibung).append(
					", ");
		}
		if (this.kategorie != null) {
			builder.append("kategorie=").append(this.kategorie).append(", ");
		}
		if (this.kurzbezeichnung != null) {
			builder.append("kurzbezeichnung=").append(this.kurzbezeichnung)
					.append(", ");
		}
		if (this.preis != null) {
			builder.append("preis=").append(this.preis);
		}
		builder.append("]");
		return builder.toString();
	}

}
