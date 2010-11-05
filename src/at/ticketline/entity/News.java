package at.ticketline.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Zu jedem Ort können Neuigkeiten mit einem Datum, Titel und Text gespeichert
 * werden.
 * 
 */
@Entity
public class News extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@NotNull
	private Date datum;

	@Column(nullable = false, length = 100)
	@Size(max = 100)
	@NotNull
	private String titel;

	@Lob
	private String text;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORT_ID", nullable = true)
	private Ort ort;

	/** full constructor */
	public News(Integer id, Date datum, String titel, String text, Ort ort) {
		this.id = id;
		this.datum = datum;
		this.titel = titel;
		this.text = text;
		this.ort = ort;
	}

	/** default constructor */
	public News() {
		// do nothing
	}

	/** minimal constructor */
	public News(Integer id, Date datum, String titel) {
		this.id = id;
		this.datum = datum;
		this.titel = titel;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Ort getOrt() {
		return this.ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("News [");
		if (this.id != null) {
			builder.append("id=").append(this.id).append(", ");
		}
		if (this.datum != null) {
			builder.append("datum=").append(this.datum).append(", ");
		}
		if (this.ort != null) {
			builder.append("ort=").append(this.ort).append(", ");
		}
		if (this.titel != null) {
			builder.append("titel=").append(this.titel);
		}
		builder.append("]");
		return builder.toString();
	}
}
