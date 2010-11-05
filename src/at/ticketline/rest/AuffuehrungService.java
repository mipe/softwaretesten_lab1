package at.ticketline.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.inject.Inject;

import at.ticketline.dao.interfaces.AuffuehrungDao;
import at.ticketline.entity.Auffuehrung;

@Component
@Scope("request")
@Path("auffuehrungen")
public class AuffuehrungService {
	
	private AuffuehrungDao auffuehrungDao;

	@GET
	@Produces("application/json")
	@Path("list")
	public JSONArray getAuffuehrungenByVeranstaltungId(@PathParam("id") Integer id) throws Exception {
//    	List<Auffuehrung> auffuehrungen = this.auffuehrungDao.findByVeranstaltungId(id);
//		JSONArray result = new JSONArray();
//    	
//		for (Auffuehrung a : auffuehrungen) {
//			JSONObject obj = new JSONObject();
//			obj.put("id", a.getId());
//			obj.put("date", a.getDatumuhrzeit());
//			obj.put("hinweis", a.getHinweis());
//			obj.put("storniert", a.isStorniert());
//			result.put(obj);
//		}
//		
//    	return result;
		return null;
	}

	
	@Inject("auffuehrungDao")
	public void setAuffuehrungDao(AuffuehrungDao auffuehrungDao) {
		this.auffuehrungDao = auffuehrungDao;
	}
	
	public AuffuehrungDao getAuffuehrungDao() {
		return auffuehrungDao;
	}
	
}
