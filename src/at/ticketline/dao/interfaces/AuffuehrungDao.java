package at.ticketline.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import at.ticketline.dao.GenericDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.Platz;

public interface AuffuehrungDao extends GenericDao<Auffuehrung, Integer> {

	public List<Object[]> findByVeranstaltungId(Integer id);

	/**
	 * @param id ID der gesuchten Auff�hrung
	 * @return Liefert zu einer Auff�hrung mit der ID id eine Liste von Pl�tzen
	 *         f�r die gilt: 
	 *         - Sollten zur Auffuehrung keine Pl�tze vorhanden sein, wird eine
	 *           leere Liste zur�ckggegeben. Ansonsten wird genau ein Platz 
	 *           zur�ckgegeben.
	 *         - Der Platz ist der beste (= teuerste) Platz der Auff�hrung.
	 *           Sollte es mehrere Pl�tze geben, die dieser Bedingung
	 *           entsprechen, wird genau einer davon zur�ckgegeben. (Wobei es
	 *           keine Garantie gibt, welcher der besten Pl�tze ausgew�hlt
	 *           wird.)
	 */
	public List<Platz> findBest(Integer id);

	/**
	 * @param id ID der gesuchten Auff�hrung
	 * @param count Anzahl der gesuchten Pl�tze
	 * @return Liefert zu einer Auff�hrung mit der ID id eine Liste von Pl�tzen
	 *         f�r die gilt: 
	 *         - Sollten zu einer Auff�hrung mindestens count Pl�tze vorhanden
	 *           sein, so enth�lt die Liste genau count Pl�tze. Sind weniger als
	 *           count Pl�tze vorhanden, dann entspricht die Anzahl der
	 *           zur�ckgegebenen Pl�tze der Anzahl der vorhandenen Pl�tze.
	 *         - Die Pl�tze sind die besten (= teuersten) Pl�tze der Auff�hrung.
	 *           Sollte es mehr als count Pl�tze geben, die dieser Bedingung
	 *           entsprechen, werden genau count Pl�tze davon zur�ckgegeben.
	 *           (Wobei es keine Garantie gibt, welcher der besten Pl�tze
	 *           ausgew�hlt wird.)
	 *         - Abgesehen davon gibt es keine weiteren Zusicherungen, die
	 *           bez�glich der zur�ckgelieferten Pl�tze im Allgemeinen gelten.
	 *           (Insbesondere m�ssen die Pl�tze nicht zusammenh�ngend sein.)
	 *         - Ganz formal ausgedr�ckt: Wenn P die Menge aller Pl�tze der 
	 *           Auffuehrung ist und weiters Pa die Menge der zur�ckgegebenen
	 *           Pl�tze und Pn = P - Pa die Menge der nicht zur�ckgegebenen
	 *           Pl�tze ist, gilt:
	 *             - Es gibt kein n e Pn so dass: Es gibt ein a so dass:
	 *                 preis(n) > preis(a)
	 *             - |P| > count dann gilt: |Pa| = count   sonst:
	 *             - |P| <= count dann gilt: |Pa| = |P|
	 */
	public List<Platz> findBest(Integer id, Integer count);

	/**
	 * @param id ID der gesuchten Auff�hrung
	 * @param count Anzahl der gesuchten Pl�tze
	 * @param maxEinzelPreis Maximaler Einzelpreis der Pl�tze
	 * @return Liefert zu einer Auff�hrung mit der ID id eine Liste von Pl�tzen
	 *         f�r die gilt: 
	 *         - Sollten zu einer Auff�hrung mindestens count Pl�tze mit einem
	 *           Preis <= maxEinzelPreis vorhanden sein, so enth�lt die Liste
	 *           genau count Pl�tze. Sind weniger als count Pl�tze mit einem
	 *           Preis <= maxEinzelPreis vorhanden, dann entspricht die Anzahl
	 *           der zur�ckgegebenen Pl�tze der Anzahl der vorhandenen Pl�tze.
	 *         - Die Pl�tze sind die besten (= teuersten) Pl�tze mit einem
	 *           Preis <= maxEinzelPreis der Auff�hrung. Sollte es mehr als
	 *           count Pl�tze mit einem Preis <= maxEinzelPreis geben, die
	 *           dieser Bedingung entsprechen, werden genau count Pl�tze
	 *           davon zur�ckgegeben. (Wobei es keine Garantie gibt, welcher der
	 *           besten Pl�tze ausgew�hlt wird.)
	 *         - Abgesehen davon gibt es keine weiteren Zusicherungen, die
	 *           bez�glich der zur�ckgelieferten Pl�tze im Allgemeinen gelten.
	 *           (Insbesondere m�ssen die Pl�tze nicht zusammenh�ngend sein.)
	 *         - Ganz formal ausgedr�ckt: Wenn P die Menge aller Pl�tze mit
	 *           einem Preis <= maxEinzelPreis der Auffuehrung ist und weiters
	 *           Pa die Menge der zur�ckgegebenen Pl�tze und Pn = P - Pa die
	 *           Menge der nicht zur�ckgegebenen Pl�tze ist, gilt:
	 *             - F�r alle a e Pa gilt:
	 *                 preis(a) <= maxEinzelPreis
	 *             - Es gibt kein n e Pn so dass: Es gibt ein a so dass:
	 *                 preis(n) > preis(a)
	 *             - |P| > count dann gilt: |Pa| = count   sonst:
	 *             - |P| <= count dann gilt: |Pa| = |P|
	 */
	public List<Platz> findBest(Integer id, Integer count,
			BigDecimal maxEinzelPreis);

	/**
	 * @param id ID der gesuchten Auff�hrung
	 * @return Liefert zu einer Auff�hrung mit der ID id eine Liste von Pl�tzen
	 *         f�r die gilt: 
	 *         - Sollten zur Auffuehrung keine Pl�tze vorhanden sein, wird eine
	 *           leere Liste zur�ckggegeben. Ansonsten wird genau ein Platz 
	 *           zur�ckgegeben.
	 *         - Der Platz ist der billigste Platz der Auff�hrung.
	 *           Sollte es mehrere Pl�tze geben, die dieser Bedingung
	 *           entsprechen, wird genau einer davon zur�ckgegeben. (Wobei es
	 *           keine Garantie gibt, welcher der besten Pl�tze ausgew�hlt
	 *           wird.)
	 */
	public List<Platz> findCheapest(Integer id);
	
	/**
	 * @param id ID der gesuchten Auff�hrung
	 * @param count Anzahl der gesuchten Pl�tze
	 * @return Liefert zu einer Auff�hrung mit der ID id eine Liste von Pl�tzen
	 *         f�r die gilt: 
	 *         - Sollten zu einer Auff�hrung mindestens count Pl�tze vorhanden
	 *           sein, so enth�lt die Liste genau count Pl�tze. Sind weniger als
	 *           count Pl�tze vorhanden, dann entspricht die Anzahl der
	 *           zur�ckgegebenen Pl�tze der Anzahl der vorhandenen Pl�tze.
	 *         - Die Pl�tze sind die billigsten Pl�tze der Auff�hrung.
	 *           Sollte es mehr als count Pl�tze geben, die dieser Bedingung
	 *           entsprechen, werden genau count Pl�tze davon zur�ckgegeben.
	 *           (Wobei es keine Garantie gibt, welcher der besten Pl�tze
	 *           ausgew�hlt wird.)
	 *         - Abgesehen davon gibt es keine weiteren Zusicherungen, die
	 *           bez�glich der zur�ckgelieferten Pl�tze im Allgemeinen gelten.
	 *           (Insbesondere m�ssen die Pl�tze nicht zusammenh�ngend sein.)
	 *         - Ganz formal ausgedr�ckt: Wenn P die Menge aller Pl�tze der 
	 *           Auffuehrung ist und weiters Pa die Menge der zur�ckgegebenen
	 *           Pl�tze und Pn = P - Pa die Menge der nicht zur�ckgegebenen
	 *           Pl�tze ist, gilt:
	 *             - Es gibt kein n e Pn so dass: Es gibt ein a so dass:
	 *                 preis(n) < preis(a)
	 *             - |P| > count dann gilt: |Pa| = count   sonst:
	 *             - |P| <= count dann gilt: |Pa| = |P|
	 */
	public List<Platz> findCheapest(Integer id, Integer count);
}
