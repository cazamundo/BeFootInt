/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

/**
 * @author cazamundo
 *
 */
public class TweetServiceImpl {
	
	private List<Tweet> tweets;

	TweetServiceImpl(){
		tweets = new ArrayList<Tweet>();
	}
	
	
	public List<Tweet> refreshTweets() {
		
		int nrOfTweets = 10;
		for (int i=0;i<nrOfTweets;i++){
			Date date = new Date();
			Tweet tweet = new Tweet(date.getTime(), "This is the tweetText, tweet number " + i, date, "Lukaku", "url", date.getTime(), date.getTime(), "EN", null);
			
			tweets.add(tweet);
		}
		
		return tweets;
		
	}
	
	

}
