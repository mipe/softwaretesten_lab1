package at.ticketline.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Eine Bestellung dokumentiert den Verkauf von Artikeln fuer eine Veranstaltung
 * an einen Kunden. Fuer jeden gekauften Artikel muss eine eigene
 * BestellPosition angelegt werden. Die Bestellung enthaelt zusaetzliche
 * Informationen wie die Zahlungsart (Ticketcard, Kreditkarte,..), das Datum des
 * Verkaufs, ob die Bestellung bereits bezahlt und versandt wurde. Sollten
 * zusaetzliche Informationen zu einer Bestellung abgelegt werden, kann dies in
 * den Anmerkungen erfolgen, zB wenn der Kunde die Bestellung wieder
 * retourniert.
 * 
 * @see BestellPosition
 * @see Zahlungsart
 */
@Entity
public class Bestellung extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2047473610304940961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@NotNull
	private Date bestellzeitpunkt;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Zahlungsart zahlungsart;

	private boolean bezahlt = false;

	private boolean versandt = false;

	@Lob
	private String anmerkungen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KUNDE_ID")
	private Kunde kunde;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bestellung")
	private Set<BestellPosition> bestellPositionen = new HashSet<BestellPosition>();

	/** full constructor */
	public Bestellung(Integer id, Date bestellzeitpunkt,
			Zahlungsart zahlungsart, boolean bezahlt, boolean versandt,
			String anmerkungen, Kunde kunde,
			Set<BestellPosition> bestellPositionen) {
		this.id = id;
		this.bestellzeitpunkt = bestellzeitpunkt;
		this.zahlungsart = zahlungsart;
		this.bezahlt = bezahlt;
		this.versandt = versandt;
		this.anmerkungen = anmerkungen;
		this.kunde = kunde;
		this.bestellPositionen = bestellPositionen;
	}

	/** default constructor */
	public Bestellung() {
		this.bestellzeitpunkt = new Date();
	}

	/** minimal constructor */
	public Bestellung(Integer id, Kunde kunde, Zahlungsart zahlungsart,
			Set<BestellPosition> bestellPositionen) {
		this.id = id;
		this.bestellzeitpunkt = new Date();
		this.kunde = kunde;
		this.zahlungsart = zahlungsart;
		this.bestellPositionen = bestellPositionen;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBestellzeitpunkt() {
		return this.bestellzeitpunkt;
	}

	public void setBestellzeitpunkt(Date bestellzeitpunkt) {
		this.bestellzeitpunkt = bestellzeitpunkt;
	}

	/**
	 * z.B.: TicketCard, Kreditkarte, Nachname
	 */
	public Zahlungsart getZahlungsart() {
		return this.zahlungsart;
	}

	public void setZahlungsart(Zahlungsart zahlungsart) {
		this.zahlungsart = zahlungsart;
	}

	/**
	 * Wurde die Bestellung bereits bezahlt?
	 */
	public boolean isBezahlt() {
		return this.bezahlt;
	}

	public void setBezahlt(boolean bezahlt) {
		this.bezahlt = bezahlt;
	}

	/**
	 * Wurde die Bestellung bereits versandt?
	 */
	public boolean isVersandt() {
		return this.versandt;
	}

	public void setVersandt(boolean versandt) {
		this.versandt = versandt;
	}

	/**
	 * Anmerkungen zur Bestellung (zB bei Problemen)
	 */
	public String getAnmerkungen() {
		return this.anmerkungen;
	}

	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

	public Kunde getKunde() {
		return this.kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
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
		builder.append("Bestellung [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.anmerkungen != null) {
			builder.append("anmerkungen=").append(this.anmerkungen)
					.append(", ");
		}
		if (this.bestellzeitpunkt != null) {
			builder.append("bestellzeitpunkt=").append(this.bestellzeitpunkt)
					.append(", ");
		}
		builder.append("bezahlt=").append(this.bezahlt).append(", ");
		builder.append("versandt=").append(this.versandt).append(", ");
		if (this.zahlungsart != null) {
			builder.append("zahlungsart=").append(this.zahlungsart);
		}
		builder.append("]");
		return builder.toString();
	}

}
