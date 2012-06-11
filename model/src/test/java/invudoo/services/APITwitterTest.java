package invudoo.services;

import invudoo.services.TwitterService;

import java.util.List;

import org.junit.Test;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class APITwitterTest {
	TwitterService service = new TwitterService();

	@Test
	public void timeLine() throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		List<Status> statuses = twitter.getHomeTimeline();
		System.out.println("Showing friends timeline.");
		for (Status status : statuses)
			System.out.println(status.getUser().getName() + ":"
					+ status.getText());
	}

	@Test
	public void search() throws Exception {
		String keywords = "iweekend";
		Query query = new Query(keywords);
		query.setRpp(100);
		QueryResult result = service.search(query);
		for (Tweet tweet : result.getTweets())
			System.out.println(tweet.getFromUser() + ":" + tweet.getText());
	}
}
