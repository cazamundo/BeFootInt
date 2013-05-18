/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.Tweet;

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
		
		
		
		return tweets;
		
	}

}
