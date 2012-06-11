package invudoo.view;

import invudoo.services.TwitterService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.time.DateUtils;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.TwitterException;

@ManagedBean
@ViewScoped
public class Searcher {
	private String keywords;
	private Query query;
	private Date since, until;
	private String animo;
	private String noContiene;
	private QueryResult results;
	private List<Point> grafica;

	public Searcher() {
		query = new Query();
		query.setRpp(100);
		query.setLang("es");
		keywords="";
		noContiene = "";
		animo="";
		grafica = new ArrayList<Point>();
	}

	public void search() throws TwitterException {
		TwitterService service = new TwitterService();

		String[] palabras = noContiene.split(" ");
		String excluidas = "";
		for (String p : palabras)
			excluidas += " -" + p;
		query.setQuery(keywords + excluidas + animo);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (since != null)
			query.setSince(df.format(since));
		if (until != null)
			query.setUntil(df.format(DateUtils.addDays(until, 1)));
		setResults(service.search(query));
		String xHour = "";
		int tweetsInHour = 0;
		grafica = new ArrayList<Point>();
		for (Tweet tweet : results.getTweets()) {
			Date date = tweet.getCreatedAt();
			// sacamos la hora
			//int hour = date.getHours();
			Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			calendar.setTime(date);   // assigns calendar to given date 
			String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))+"/"+calendar.get(Calendar.DAY_OF_MONTH); 
			// tweet cambia de hora
			if (!hour.equals(xHour)) {
				// Guardamos: xHour,tweetsInHour
				if (!"".equals(xHour)) {
					grafica.add(new Point(xHour, tweetsInHour));
					System.out.println(xHour + "-"+tweetsInHour);
				}
				xHour = hour;
				tweetsInHour = 1;
			} else {
				tweetsInHour++;
			}
		}
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public QueryResult getResults() {
		return results;
	}

	public void setResults(QueryResult results) {
		this.results = results;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public String getAnimo() {
		return animo;
	}

	public void setAnimo(String animo) {
		this.animo = animo;
	}

	public String getNoContiene() {
		return noContiene;
	}

	public void setNoContiene(String noContiene) {
		this.noContiene = noContiene;
	}

	public List<Point> getGrafica() {
		return grafica;
	}

	public void setGrafica(List<Point> grafica) {
		this.grafica = grafica;
	}

}
