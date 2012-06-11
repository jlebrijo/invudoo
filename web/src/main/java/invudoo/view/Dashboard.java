package invudoo.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import twitter4j.QueryResult;
import twitter4j.TwitterException;

@ManagedBean
@ViewScoped
public class Dashboard {
	private QueryResult results;
	private List<Point> grafica;

	public Dashboard() throws TwitterException {
		Searcher searcher = new Searcher();
		searcher.setKeywords("iweekend");
		searcher.search();
		results = searcher.getResults();
		grafica = searcher.getGrafica();
	}

	public QueryResult getResults() {
		return results;
	}

	public void setResults(QueryResult results) {
		this.results = results;
	}

	public List<Point> getGrafica() {
		return grafica;
	}

	public void setGrafica(List<Point> grafica) {
		this.grafica = grafica;
	}
}
