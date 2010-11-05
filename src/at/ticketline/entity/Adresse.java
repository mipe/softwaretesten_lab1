package at.ticketline.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Adresse implements Serializable {

	private static final long serialVersionUID = 6963420134559921476L;

	@Column(nullable = false, length = 80)
	@Size(max = 80)
	@NotNull
	private String strasse;

	@Column(nullable = false, length = 5)
	@Size(max = 5)
	@NotNull
	@Pattern(regexp = "^[0-9]{4,5}$")
	private String plz;

	@Column(nullable = false, length = 50)
	@Size(max = 50)
	@NotNull
	private String ort;

	@Column(length = 40)
	@Size(max = 40)
	private String land;

	public String getStrasse() {
		return this.strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return this.plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return this.land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adresse [");
		if (this.land != null) {
			builder.append("land=").append(this.land).append(", ");
		}
		if (this.ort != null) {
			builder.append("ort=").append(this.ort).append(", ");
		}
		if (this.plz != null) {
			builder.append("plz=").append(this.plz).append(", ");
		}
		if (this.strasse != null) {
			builder.append("strasse=").append(this.strasse);
		}
		builder.append("]");
		return builder.toString();
	}
}
