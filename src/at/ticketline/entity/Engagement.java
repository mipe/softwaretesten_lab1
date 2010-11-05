package at.ticketline.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Ein Engagement beschreibt die Mitwirkung eines Kuenstlers an einer
 * Veranstaltung. Neben der Nummer des Kuenstlers und den Daten fuer die
 * Veranstaltung werden noch die Funktion des Kuenstlers in der Veranstaltung
 * und seine Gage gespeichert.
 * 
 */
@Entity
public class Engagement extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2423452444675834992L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(max = 255)
	private String funktion;

	@Min(value = 0)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal gage;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KUENSTLER_ID")
	private Kuenstler kuenstler;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VERANSTALTUNG_ID")
	private Veranstaltung veranstaltung;

	/** full constructor */
	public Engagement(Integer id, String funktion, BigDecimal gage,
			Kuenstler kuenstler, Veranstaltung veranstaltung) {
		this.id = id;
		this.funktion = funktion;
		this.gage = gage;
		this.kuenstler = kuenstler;
		this.veranstaltung = veranstaltung;
	}

	/** default constructor */
	public Engagement() {
		// do nothing
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Schauspieler, Regie, etc.
	 */
	public String getFunktion() {
		return this.funktion;
	}

	public void setFunktion(String funktion) {
		this.funktion = funktion;
	}

	/**
	 * Gage >= 0
	 */
	public BigDecimal getGage() {
		return this.gage;
	}

	public void setGage(BigDecimal gage) {
		this.gage = gage;
	}

	public Kuenstler getKuenstler() {
		return this.kuenstler;
	}

	public void setKuenstler(Kuenstler kuenstler) {
		this.kuenstler = kuenstler;
	}

	public Veranstaltung getVeranstaltung() {
		return this.veranstaltung;
	}

	public void setVeranstaltung(Veranstaltung veranstaltung) {
		this.veranstaltung = veranstaltung;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Engagement [");
		if (this.id != null) {
			builder.append("id=").append(this.id);
		}
		if (this.funktion != null) {
			builder.append("funktion=").append(this.funktion).append(", ");
		}
		if (this.gage != null) {
			builder.append("gage=").append(this.gage).append(", ");
		}
		builder.append("]");
		return builder.toString();
	}
}
