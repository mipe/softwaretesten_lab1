package at.ticketline.dao.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import at.ticketline.dao.GenericDaoJpa;
import at.ticketline.dao.interfaces.AuffuehrungDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.Platz;
import at.ticketline.entity.PlatzStatus;

public class AuffuehrungDaoJpa extends GenericDaoJpa<Auffuehrung, Integer>
		implements AuffuehrungDao {


	@SuppressWarnings("unchecked")
	public List<Object[]> findByVeranstaltungId(Integer id) {
		return (List<Object[]>) this.entityManager.createNamedQuery("Auffuehrung.findByVeranstaltung").setParameter("id", id).getResultList();
	}

	public List<Platz> findBest(Integer id) {
		Platz best = null;
		List<Platz> bestList = new ArrayList<Platz>();

		for (Platz p : this.findById(id).getPlaetze()) {
			if (p.better(best))
				best = p;
		}

		bestList.add(best);
		return bestList;
	}

	public List<Platz> findBest(Integer id, Integer count) {
		List<Platz> bestList = new ArrayList<Platz>();

		for (Platz p : this.findById(id).getPlaetze()) {
			/*
			 * Durchwandere bestList von hinten nach vorne und bestimme die
			 * Position, an der p eingefügt werden soll
			 */
			int i = bestList.size() - 1;
			while ((i >= 0) && p.better(bestList.get(i))) {
				i--;
			}

			bestList.add(p);

			if (bestList.size() > count) {
				bestList.remove(count);
			}
		}

		return bestList;
	}

	public List<Platz> findBest(Integer id, Integer count,
			BigDecimal maxEinzelPreis) {
		List<Platz> filterList = new ArrayList<Platz>();

		for (Platz p : this.findById(id).getPlaetze()) {
			if (p.getKategorie().getPreisstd().compareTo(maxEinzelPreis) <= 0) {
				filterList.add(p);
			}
		}

		List<Platz> bestList = new ArrayList<Platz>();

		for (Platz p : filterList) {
			/*
			 * Durchwandere bestList von hinten nach vorne und bestimme die
			 * Position, an der p eingefügt werden soll
			 */
			int i = bestList.size() - 1;
			while ((i >= 0) && p.better(bestList.get(i))) {
				i--;
			}

			bestList.add(p);

			if (bestList.size() > count) {
				bestList.remove(count);
			}
		}

		return bestList;
	}

	public List<Platz> findCheapest(Integer id) {
		Platz cheapest = null;
		List<Platz> cheapestList = new ArrayList<Platz>();

		for (Platz p : this.findById(id).getPlaetze()) {
			if (p.cheaper(cheapest))
				cheapest = p;
		}

		cheapestList.add(cheapest);
		return cheapestList;
	}

	@Override
	public List<Platz> findCheapest(Integer id, Integer count) {
		List<Platz> cheapestList = new ArrayList<Platz>();
		
		for (Platz p : this.findById(id).getPlaetze()) {
			/*
			 * Durchwandere bestList von hinten nach vorne und bestimme die
			 * Position, an der p eingefügt werden soll
			 */
			int i = cheapestList.size() - 1;
			while ((i >= 0) && p.cheaper(cheapestList.get(i))) {
				i--;
			}

			cheapestList.add(p);

			if (cheapestList.size() > count) {
				cheapestList.remove(count);
			}
		}

		return cheapestList;
	}
}
