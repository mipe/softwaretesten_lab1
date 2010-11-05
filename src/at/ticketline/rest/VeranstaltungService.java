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
import at.ticketline.dao.interfaces.VeranstaltungDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.Ort;
import at.ticketline.entity.Veranstaltung;

@Component
@Scope("request")
@Path("veranstaltung")
public class VeranstaltungService {
	
	private VeranstaltungDao veranstaltungDao;
	
	private AuffuehrungDao auffuehrungDao;

    @GET
    @Path("list")
    @Produces("application/json")
    public JSONArray getVeranstaltungen() throws Exception {
    	List<Veranstaltung> veranstaltungen = this.veranstaltungDao.findAll();
		JSONArray vs = new JSONArray();
		
    	for (Veranstaltung v : veranstaltungen) {
    		JSONObject veranstaltungJson = new JSONObject();
    		veranstaltungJson.put("id", v.getId());
    		veranstaltungJson.put("bezeichnung", v.getBezeichnung());
    		veranstaltungJson.put("dauer", v.getDauer());
    		vs.put(veranstaltungJson);
    	}
    	
    	return vs;
    }
    
    @GET
    @Produces("application/json")
    @Path("{id}")
	public JSONObject getVeranstaltung(@PathParam("id") Integer id) throws Exception {
    	JSONObject result = new JSONObject();
    	
    	Veranstaltung v = this.veranstaltungDao.findById(id);
    	result.put("id", v.getId());
    	result.put("bezeichnung", v.getBezeichnung());
    	result.put("inhalt", v.getInhalt());
    	result.put("hinweis", v.getHinweis());
    	
    	List<Object[]> auffuehrungen = this.auffuehrungDao.findByVeranstaltungId(id);
		JSONArray resultAuffuehrungen = new JSONArray();
    	
		for (Object[] auffuehrung : auffuehrungen) {
			JSONObject obj = new JSONObject();
			Auffuehrung a = (Auffuehrung)auffuehrung[0];
			//Ort o = (Ort)auffuehrung[1];
			obj.put("id", a.getId());
			obj.put("date", a.getDatumuhrzeit());
			obj.put("hinweis", a.getHinweis());
			obj.put("storniert", a.isStorniert());
			//obj.put("ort", o.getBezeichnung());
			resultAuffuehrungen.put(obj);
		}
		result.put("auffuehrungen", resultAuffuehrungen);
		
    	return result;
	}

	@Inject("veranstaltungDao")
	public void setVeranstaltungDao(VeranstaltungDao veranstaltungDao) {
		this.veranstaltungDao = veranstaltungDao;
	}

	public VeranstaltungDao getVeranstaltungDao() {
		return veranstaltungDao;
	}
	
	@Inject("auffuehrungDao")
	public void setAuffuehrungDao(AuffuehrungDao auffuehrungDao) {
		this.auffuehrungDao = auffuehrungDao;
	}
	
	public AuffuehrungDao getAuffuehrungDao() {
		return auffuehrungDao;
	}

}

