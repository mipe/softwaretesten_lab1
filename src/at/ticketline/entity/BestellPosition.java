package at.ticketline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Eine Bestellung umfasst eine oder mehrere BestellPositionen. Jede
 * BestellPosition enthaelt den bestellten Artikel, sowie die bestellte Menge
 * des Artikels.
 * 
 */
@Entity
public class BestellPosition extends BaseEntity {

	private static final long serialVersionUID = -2047563610304940961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ARTIKEL_ID")
	private Artikel artikel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BESTELLUNG_ID")
	private Bestellung bestellung;

	@Column(nullable = false)
	@Min(value = 1)
	@NotNull
	private Integer menge = 1;

	/** full constructor */
	public BestellPosition(Integer id, Artikel artikel, Bestellung bestellung,
			Integer menge) {
		this.id = id;
		this.artikel = artikel;
		this.bestellung = bestellung;
		this.menge = menge;
	}

	/** default constructor */
	public BestellPosition() {
		// do nothing
	}

	/** minimal constructor */
	public BestellPosition(Integer id, Artikel artikel, Bestellung bestellung) {
		this.id = id;
		this.artikel = artikel;
		this.bestellung = bestellung;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Artikel getArtikel() {
		return this.artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Bestellung getBestellung() {
		return this.bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public Integer getMenge() {
		return this.menge;
	}

	public void setMenge(Integer menge) {
		this.menge = menge;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BestellPosition [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.menge != null) {
			builder.append("menge=").append(this.menge);
		}
		builder.append("]");
		return builder.toString();
	}
}
