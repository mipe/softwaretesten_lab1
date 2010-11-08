package at.ticketline.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import at.ticketline.dao.GenericDao;
import at.ticketline.entity.Auffuehrung;
import at.ticketline.entity.Platz;

public interface AuffuehrungDao extends GenericDao<Auffuehrung, Integer> {

	public List<Object[]> findByVeranstaltungId(Integer id);

	/**
	 * @param id ID der gesuchten Aufführung
	 * @return Liefert zu einer Aufführung mit der ID id eine Liste von Plätzen
	 *         für die gilt: 
	 *         - Sollten zur Auffuehrung keine Plätze vorhanden sein, wird eine
	 *           leere Liste zurückggegeben. Ansonsten wird genau ein Platz 
	 *           zurückgegeben.
	 *         - Der Platz ist der beste (= teuerste) Platz der Aufführung.
	 *           Sollte es mehrere Plätze geben, die dieser Bedingung
	 *           entsprechen, wird genau einer davon zurückgegeben. (Wobei es
	 *           keine Garantie gibt, welcher der besten Plätze ausgewählt
	 *           wird.)
	 */
	public List<Platz> findBest(Integer id);

	/**
	 * @param id ID der gesuchten Aufführung
	 * @param count Anzahl der gesuchten Plätze
	 * @return Liefert zu einer Aufführung mit der ID id eine Liste von Plätzen
	 *         für die gilt: 
	 *         - Sollten zu einer Aufführung mindestens count Plätze vorhanden
	 *           sein, so enthält die Liste genau count Plätze. Sind weniger als
	 *           count Plätze vorhanden, dann entspricht die Anzahl der
	 *           zurückgegebenen Plätze der Anzahl der vorhandenen Plätze.
	 *         - Die Plätze sind die besten (= teuersten) Plätze der Aufführung.
	 *           Sollte es mehr als count Plätze geben, die dieser Bedingung
	 *           entsprechen, werden genau count Plätze davon zurückgegeben.
	 *           (Wobei es keine Garantie gibt, welcher der besten Plätze
	 *           ausgewählt wird.)
	 *         - Abgesehen davon gibt es keine weiteren Zusicherungen, die
	 *           bezüglich der zurückgelieferten Plätze im Allgemeinen gelten.
	 *           (Insbesondere müssen die Plätze nicht zusammenhängend sein.)
	 *         - Ganz formal ausgedrückt: Wenn P die Menge aller Plätze der 
	 *           Auffuehrung ist und weiters Pa die Menge der zurückgegebenen
	 *           Plätze und Pn = P - Pa die Menge der nicht zurückgegebenen
	 *           Plätze ist, gilt:
	 *             - Es gibt kein n e Pn so dass: Es gibt ein a so dass:
	 *                 preis(n) > preis(a)
	 *             - |P| > count dann gilt: |Pa| = count   sonst:
	 *             - |P| <= count dann gilt: |Pa| = |P|
	 */
	public List<Platz> findBest(Integer id, Integer count);

	/**
	 * @param id ID der gesuchten Aufführung
	 * @param count Anzahl der gesuchten Plätze
	 * @param maxEinzelPreis Maximaler Einzelpreis der Plätze
	 * @return Liefert zu einer Aufführung mit der ID id eine Liste von Plätzen
	 *         für die gilt: 
	 *         - Sollten zu einer Aufführung mindestens count Plätze mit einem
	 *           Preis <= maxEinzelPreis vorhanden sein, so enthält die Liste
	 *           genau count Plätze. Sind weniger als count Plätze mit einem
	 *           Preis <= maxEinzelPreis vorhanden, dann entspricht die Anzahl
	 *           der zurückgegebenen Plätze der Anzahl der vorhandenen Plätze.
	 *         - Die Plätze sind die besten (= teuersten) Plätze mit einem
	 *           Preis <= maxEinzelPreis der Aufführung. Sollte es mehr als
	 *           count Plätze mit einem Preis <= maxEinzelPreis geben, die
	 *           dieser Bedingung entsprechen, werden genau count Plätze
	 *           davon zurückgegeben. (Wobei es keine Garantie gibt, welcher der
	 *           besten Plätze ausgewählt wird.)
	 *         - Abgesehen davon gibt es keine weiteren Zusicherungen, die
	 *           bezüglich der zurückgelieferten Plätze im Allgemeinen gelten.
	 *           (Insbesondere müssen die Plätze nicht zusammenhängend sein.)
	 *         - Ganz formal ausgedrückt: Wenn P die Menge aller Plätze mit
	 *           einem Preis <= maxEinzelPreis der Auffuehrung ist und weiters
	 *           Pa die Menge der zurückgegebenen Plätze und Pn = P - Pa die
	 *           Menge der nicht zurückgegebenen Plätze ist, gilt:
	 *             - Für alle a e Pa gilt:
	 *                 preis(a) <= maxEinzelPreis
	 *             - Es gibt kein n e Pn so dass: Es gibt ein a so dass:
	 *                 preis(n) > preis(a)
	 *             - |P| > count dann gilt: |Pa| = count   sonst:
	 *             - |P| <= count dann gilt: |Pa| = |P|
	 */
	public List<Platz> findBest(Integer id, Integer count,
			BigDecimal maxEinzelPreis);

	/**
	 * @param id ID der gesuchten Aufführung
	 * @return Liefert zu einer Aufführung mit der ID id eine Liste von Plätzen
	 *         für die gilt: 
	 *         - Sollten zur Auffuehrung keine Plätze vorhanden sein, wird eine
	 *           leere Liste zurückggegeben. Ansonsten wird genau ein Platz 
	 *           zurückgegeben.
	 *         - Der Platz ist der billigste Platz der Aufführung.
	 *           Sollte es mehrere Plätze geben, die dieser Bedingung
	 *           entsprechen, wird genau einer davon zurückgegeben. (Wobei es
	 *           keine Garantie gibt, welcher der besten Plätze ausgewählt
	 *           wird.)
	 */
	public List<Platz> findCheapest(Integer id);
	
	/**
	 * @param id ID der gesuchten Aufführung
	 * @param count Anzahl der gesuchten Plätze
	 * @return Liefert zu einer Aufführung mit der ID id eine Liste von Plätzen
	 *         für die gilt: 
	 *         - Sollten zu einer Aufführung mindestens count Plätze vorhanden
	 *           sein, so enthält die Liste genau count Plätze. Sind weniger als
	 *           count Plätze vorhanden, dann entspricht die Anzahl der
	 *           zurückgegebenen Plätze der Anzahl der vorhandenen Plätze.
	 *         - Die Plätze sind die billigsten Plätze der Aufführung.
	 *           Sollte es mehr als count Plätze geben, die dieser Bedingung
	 *           entsprechen, werden genau count Plätze davon zurückgegeben.
	 *           (Wobei es keine Garantie gibt, welcher der besten Plätze
	 *           ausgewählt wird.)
	 *         - Abgesehen davon gibt es keine weiteren Zusicherungen, die
	 *           bezüglich der zurückgelieferten Plätze im Allgemeinen gelten.
	 *           (Insbesondere müssen die Plätze nicht zusammenhängend sein.)
	 *         - Ganz formal ausgedrückt: Wenn P die Menge aller Plätze der 
	 *           Auffuehrung ist und weiters Pa die Menge der zurückgegebenen
	 *           Plätze und Pn = P - Pa die Menge der nicht zurückgegebenen
	 *           Plätze ist, gilt:
	 *             - Es gibt kein n e Pn so dass: Es gibt ein a so dass:
	 *                 preis(n) < preis(a)
	 *             - |P| > count dann gilt: |Pa| = count   sonst:
	 *             - |P| <= count dann gilt: |Pa| = |P|
	 */
	public List<Platz> findCheapest(Integer id, Integer count);
}
