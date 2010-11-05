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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Jeder Auffuehrungsort muss mindestens einen Saal beherbergen. Auffuehrungen
 * finden in Saelen statt. Jeder Saal ist in Reihen eingeteilt.
 * 
 */
@Entity
public class Saal extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3634135667841924175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 80)
	@Size(max = 80)
	@NotNull
	private String bezeichnung;

	@Min(value = 0)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal kostenprotag;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORT_ID")
	private Ort ort;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "saal")
	private Set<Auffuehrung> auffuehrungen = new HashSet<Auffuehrung>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saal")
	private Set<Reihe> reihen = new HashSet<Reihe>();

	/** full constructor */
	public Saal(Integer id, String bezeichnung, BigDecimal kostenprotag,
			Ort ort, Set<Auffuehrung> auffuehrungen, Set<Reihe> reihen) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.kostenprotag = kostenprotag;
		this.ort = ort;
		this.auffuehrungen = auffuehrungen;
		this.reihen = reihen;
	}

	/** default constructor */
	public Saal() {
		// do nothing
	}

	/** minimal constructor */
	public Saal(Integer id, String bezeichnung, Set<Auffuehrung> auffuehrungen,
			Set<Reihe> reihen) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.auffuehrungen = auffuehrungen;
		this.reihen = reihen;
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
	 * Kosten pro Tag >= 0
	 */
	public BigDecimal getKostenprotag() {
		return this.kostenprotag;
	}

	public void setKostenprotag(BigDecimal kostenprotag) {
		this.kostenprotag = kostenprotag;
	}

	public Ort getOrt() {
		return this.ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public Set<Auffuehrung> getAuffuehrungen() {
		return this.auffuehrungen;
	}

	public void setAuffuehrungen(Set<Auffuehrung> auffuehrungen) {
		this.auffuehrungen = auffuehrungen;
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
		builder.append("Saal [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.bezeichnung != null) {
			builder.append("bezeichnung=").append(this.bezeichnung)
					.append(", ");
		}
		if (this.kostenprotag != null) {
			builder.append("kostenprotag=").append(this.kostenprotag);
		}
		builder.append("]");
		return builder.toString();
	}

}
