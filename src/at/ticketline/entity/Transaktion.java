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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Eine Transaktion dokumentiert eine aktuelle Reservierung oder einen Verkauf,
 * bzw. eine stornierte Reservierung oder einen stornierten Verkauf. Handelt es
 * sich um eine Reservierung, so wird eine Reservierungsnummer vergeben, die dem
 * Kunden mitgeteilt wird. Eine Reservierung wird zur Buchung sobald die Zahlung
 * erfolgt ist. Werden nicht alle Sitzplaetze der Reservierung bezahlt, so
 * muessen die Sitzplaetze wieder freigegeben werden. Wird eine Reservierung
 * oder Buchung komplett storniert, so muessen die zugeordneten Sitzplaetze
 * wieder freigegeben werden. Der Preis einer Transaktion berechnet sich aus der
 * Summe der einzelnen Sitzplaetze.
 * 
 */
@Entity
public class Transaktion extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1109025925662055935L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@NotNull
	private Date datumuhrzeit;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Transaktionsstatus status;

	@Min(value = 0)
	private Integer reservierungsnr;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Zahlungsart zahlungsart;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KUNDE_ID")
	private Kunde kunde;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MITARBEITER_ID")
	private Mitarbeiter mitarbeiter;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "transaktion")
	private Set<Platz> plaetze = new HashSet<Platz>();

	/** full constructor */
	public Transaktion(Integer id, Date datumuhrzeit,
			Transaktionsstatus status, Integer resnr, Zahlungsart zahlart,
			Kunde kunde, Mitarbeiter mitarbeiter, Set<Platz> plaetze) {
		this.id = id;
		this.datumuhrzeit = datumuhrzeit;
		this.status = status;
		this.reservierungsnr = resnr;
		this.zahlungsart = zahlart;
		this.kunde = kunde;
		this.mitarbeiter = mitarbeiter;
		this.plaetze = plaetze;
	}

	/** default constructor */
	public Transaktion() {
		// do nothing
	}

	/** minimal constructor */
	public Transaktion(Integer id, Date datumuhrzeit,
			Transaktionsstatus status, Zahlungsart zahlart, Kunde kunde,
			Mitarbeiter mitarbeiter, Set<Platz> plaetze) {
		this.id = id;
		this.datumuhrzeit = datumuhrzeit;
		this.status = status;
		this.zahlungsart = zahlart;
		this.kunde = kunde;
		this.mitarbeiter = mitarbeiter;
		this.plaetze = plaetze;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatumuhrzeit() {
		return this.datumuhrzeit;
	}

	public void setDatumuhrzeit(Date datumuhrzeit) {
		this.datumuhrzeit = datumuhrzeit;
	}

	public Transaktionsstatus getStatus() {
		return this.status;
	}

	public void setStatus(Transaktionsstatus status) {
		this.status = status;
	}

	/**
	 * Nummer mit der sich der Kunde die reservierten Karten abholt; 0 <
	 * Reservierungsnummer
	 * 
	 */
	public Integer getReservierungsnr() {
		return this.reservierungsnr;
	}

	public void setReservierungsnr(Integer resnr) {
		this.reservierungsnr = resnr;
	}

	/**
	 * z.B.: bar, TicketCard, VISA...
	 */
	public Zahlungsart getZahlungsart() {
		return this.zahlungsart;
	}

	public void setZahlungsart(Zahlungsart zahlart) {
		this.zahlungsart = zahlart;
	}

	public Kunde getKunde() {
		return this.kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Mitarbeiter getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public Set<Platz> getPlaetze() {
		return this.plaetze;
	}

	public void setPlaetze(Set<Platz> plaetze) {
		this.plaetze = plaetze;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaktion [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.datumuhrzeit != null) {
			builder.append("datumuhrzeit=").append(this.datumuhrzeit).append(
					", ");
		}
		if (this.reservierungsnr != null) {
			builder.append("reservierungsnr=").append(this.reservierungsnr)
					.append(", ");
		}
		if (this.status != null) {
			builder.append("status=").append(this.status.toString()).append(
					", ");
		}
		if (this.zahlungsart != null) {
			builder.append("zahlungsart=").append(this.zahlungsart);
		}
		builder.append("]");
		return builder.toString();
	}

}
