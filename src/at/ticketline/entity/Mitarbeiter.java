package at.ticketline.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * Im Mitarbeiter werden die Stammdaten der Mitarbeiter gespeichert. Jeder
 * Mitarbeiter ist einem Ort zugeordnet.
 * 
 */
@Entity
@DiscriminatorValue(value = "M")
public class Mitarbeiter extends Person implements Serializable {

	private static final long serialVersionUID = 3949640748689103253L;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Berechtigung berechtigung;

	@Column(nullable = false, length = 50)
	@Size(max = 50)
	@NotNull
	private String username;

	@Column(nullable = false, length = 20)
	@Size(max = 20)
	@NotNull
	private String passwort;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mitarbeiter")
	private Set<Transaktion> mtransaktionen = new HashSet<Transaktion>();

	/** full constructor */
	public Mitarbeiter(Integer id, String nname, String vname, String titel,
			Geschlecht geschlecht, Date geburtsdatum,
			Adresse adresse, String telnr, String email, Ort ortverk,
			String blz, String kontonr, Berechtigung berechtigung,
			String username, String passwort, Set<Transaktion> transaktionen) {
		super(id, nname, vname, titel, geschlecht, geburtsdatum, adresse,
				telnr, email, ortverk);
		this.blz = blz;
		this.kontonr = kontonr;
		this.berechtigung = berechtigung;
		this.username = username;
		this.passwort = passwort;
		this.mtransaktionen = transaktionen;
	}

	/** default constructor */
	public Mitarbeiter() {
		// do nothing
	}

	/** minimal constructor */
	public Mitarbeiter(Integer id, String nname, String vname,
			Geschlecht geschlecht, Adresse adresse, Ort ort, String blz,
			String kontonr, Berechtigung berechtigung, String username,
			String passwort) {
		super(id, nname, vname, geschlecht, adresse, ort);
		this.blz = blz;
		this.kontonr = kontonr;
		this.berechtigung = berechtigung;
		this.username = username;
		this.passwort = passwort;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Berechtigung getBerechtigung() {
		return this.berechtigung;
	}

	public void setBerechtigung(Berechtigung berechtigung) {
		this.berechtigung = berechtigung;
	}

	public Set<Transaktion> getTransaktionen() {
		return this.mtransaktionen;
	}

	public void setTransaktionen(Set<Transaktion> transaktionen) {
		this.mtransaktionen = transaktionen;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mitarbeiter [");
		if (this.berechtigung != null) {
			builder.append("berechtigung=").append(this.berechtigung).append(
					", ");
		}
		if (this.passwort != null) {
			builder.append("passwort=").append(this.passwort).append(", ");
		}
		if (this.username != null) {
			builder.append("username=").append(this.username);
		}
		builder.append("]");
		return builder.toString();
	}

}
