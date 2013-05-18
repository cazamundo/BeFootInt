/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.Tweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cazamundo.android.befootint.fragments.top.MainTweetFragment;

/**
 * @author cazamundo
 *
 */
public class TweetBuilder {
	
	private Fragment tweetFragment;
	private TweetServiceImpl tweetService;
	private Bundle args;
	
	public TweetBuilder(){
		tweetFragment = new MainTweetFragment();
		tweetService = new TweetServiceImpl();
		args = new Bundle();
		
//		tweetFragment.setArguments(args);
	}

	public Fragment getFragment() {
		
		ArrayList<String> tweetText = new ArrayList<String>();
		tweetText.add("test");
		
		args.putStringArrayList(MainTweetFragment.TWEET_TEXT, tweetText);
		
		tweetService.refreshTweets();
		
		
		
		return tweetFragment;
	}

}
