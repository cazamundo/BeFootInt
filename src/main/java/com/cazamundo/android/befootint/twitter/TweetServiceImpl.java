/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.twitter.api.ListOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import android.util.Log;

/**
 * @author cazamundo
 *
 */
public class TweetServiceImpl {
	
	private static final String TAG = "TweetServiceImpl";
	
	private Twitter twitter;
	
	private List<Tweet> tweets;

	public TweetServiceImpl(){
		Log.v(TAG, "CONSTRUCT");
		twitter = new TwitterTemplate();
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
	
	public List<Tweet> retrieveRecentList(){
		
		ListOperations list = twitter.listOperations();
		tweets = list.getListStatuses(88452545);
		
		if(tweets != null || tweets.isEmpty()){
			return tweets;
		}else{
			Log.v(TAG, "no tweets available");
		}

		return tweets;
	}
	
	

}
