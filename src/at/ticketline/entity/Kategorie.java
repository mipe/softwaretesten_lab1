package at.ticketline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine Reihe muss einer bestimmten Kategorie zugeordnet sein. Fuer jede
 * Auffuehrung in einem Saal kann man eine Preisklasse (Minimum, Standard,
 * Maximum) festlegen. Der Preis fuer die Karten berechnet sich dann aus den
 * Preisen in den Kategorien. Kategorien sind immutable, dh sie werden nach dem
 * Erstellen nicht mehr geaendert. Wird zum Beispiel der Preis geaendert, so
 * wird eine neue Kategorie erzeugt und den Reihen zugewiesen.
 * 
 */
@Entity
public class Kategorie extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3694953274460755957L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 30)
	@Size(max = 30)
	@NotNull
	private String bezeichnung;

	@Min(value = 0)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal preismin;

	@Min(value = 0)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal preisstd;

	@Min(value = 0)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal preismax;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "kategorie")
	private Set<Reihe> reihen = new HashSet<Reihe>();

	/** full constructor */
	public Kategorie(Integer id, String bezeichnung, BigDecimal preismin,
			BigDecimal preisstd, BigDecimal preismax, Set<Reihe> reihen) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.preismin = preismin;
		this.preisstd = preisstd;
		this.preismax = preismax;
		this.reihen = reihen;
	}

	/** default constructor */
	public Kategorie() {
		// do nothing
	}

	/** minimal constructor */
	public Kategorie(Integer id, String bezeichnung, BigDecimal preisstd) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.preisstd = preisstd;
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
	 * Minimalpreis dieser Kategorie >= 0
	 */
	public BigDecimal getPreismin() {
		return this.preismin;
	}

	public void setPreismin(BigDecimal preismin) {
		this.preismin = preismin;
	}

	/**
	 * Standardpreis dieser Kategorie >= 0
	 */
	public BigDecimal getPreisstd() {
		return this.preisstd;
	}

	public void setPreisstd(BigDecimal preisstd) {
		this.preisstd = preisstd;
	}

	/**
	 * Maximalpreis dieser Kategorie >= 0
	 */
	public BigDecimal getPreismax() {
		return this.preismax;
	}

	public void setPreismax(BigDecimal preismax) {
		this.preismax = preismax;
	}

	public Set<Reihe> getReihen() {
		return this.reihen;
	}

	public void setReihen(Set<Reihe> reihen) {
		this.reihen = reihen;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kategorie [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.bezeichnung != null) {
			builder.append("bezeichnung=").append(this.bezeichnung)
					.append(", ");
		}
		if (this.preismax != null) {
			builder.append("preismax=").append(this.preismax).append(", ");
		}
		if (this.preismin != null) {
			builder.append("preismin=").append(this.preismin).append(", ");
		}
		if (this.preisstd != null) {
			builder.append("preisstd=").append(this.preisstd);
		}
		builder.append("]");
		return builder.toString();
	}

}
