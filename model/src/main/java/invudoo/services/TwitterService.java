package invudoo.services;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterService {
	public QueryResult search(Query query) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
	    QueryResult result = twitter.search(query);
		return result;
	}
}
