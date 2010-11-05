package at.ticketline.dao.interfaces;

import java.util.List;

import at.ticketline.dao.GenericDao;
import at.ticketline.entity.Kuenstler;

public interface KuenstlerDao extends GenericDao<Kuenstler,Integer> {

	public List<Kuenstler> findByKuenstler(Kuenstler query);
}
