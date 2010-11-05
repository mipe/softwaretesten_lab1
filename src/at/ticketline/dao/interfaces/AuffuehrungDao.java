package at.ticketline.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import at.ticketline.dao.GenericDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.Platz;


public interface AuffuehrungDao extends GenericDao<Auffuehrung,Integer> {

	public List<Object[]> findByVeranstaltungId(Integer id);
	public List<Platz> findBest(Integer id);
	public List<Platz> findBest(Integer id, Integer count);
	public List<Platz> findBest(Integer id, Integer count, BigDecimal maxEinzelPreis);
	public List<Platz> findCheapest (Integer id);
	public List<Platz> findCheapest (Integer id, Integer count);
}
