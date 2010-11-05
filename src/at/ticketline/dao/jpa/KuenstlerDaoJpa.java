package at.ticketline.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import at.ticketline.dao.GenericDaoJpa;
import at.ticketline.dao.interfaces.KuenstlerDao;
import at.ticketline.dao.querybuilder.ParameterExp;
import at.ticketline.dao.querybuilder.QueryBuilder;
import at.ticketline.entity.Kuenstler;

public class KuenstlerDaoJpa extends GenericDaoJpa<Kuenstler, Integer>
		implements KuenstlerDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Kuenstler> findByKuenstler(Kuenstler k) {
		if (k == null) {
			k = new Kuenstler();
		}
		if (k.getNachname() != null) {
			k.setNachname(k.getNachname().replace('*', '%').replace('?', '_')
					.toUpperCase());
		}
		if (k.getVorname() != null) {
			k.setVorname(k.getVorname().replace('*', '%').replace('?', '_')
					.toUpperCase());
		}

		ParameterExp nachname = new ParameterExp(
				"UPPER(k.nachname) LIKE :nachname", k.getNachname());
		ParameterExp vorname = new ParameterExp(
				"UPPER(k.vorname) LIKE :vorname", k.getVorname());
		ParameterExp geschlecht = new ParameterExp(
				"k.geschlecht = :geschlecht", k.getGeschlecht());

		QueryBuilder qb = QueryBuilder.build().select("k").from("Kuenstler k")
				.whereAnd(nachname, vorname, geschlecht).orderBy(
						"k.nachname ASC");
		this.log.info(qb.createQueryString());
		Query query = qb.createQuery(this.entityManager);

		return (List<Kuenstler>) query.getResultList();
	}

}
