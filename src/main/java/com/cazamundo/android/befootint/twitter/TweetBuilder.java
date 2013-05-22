/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

	private ArrayList<String> tweetText;
	private ArrayList<String> tweetUser;
	private ArrayList<String> tweetCreationDate;
	private ArrayList<String> tweetUrl;

	public TweetBuilder() {
		tweetFragment = new MainTweetFragment();
		tweetService = new TweetServiceImpl();
		args = new Bundle();
		tweetText = new ArrayList<String>();
		tweetUser = new ArrayList<String>();
		tweetCreationDate = new ArrayList<String>();
		tweetUrl = new ArrayList<String>();
	}
	
	public Fragment getFragment(){
		
		/**
		 * call TwitterService - do async call
		 */
		List<Tweet> tweets = tweetService.refreshTweets();

		// if response != null
		args = mapResponse(tweets);
		if (args != null) {
			tweetFragment.setArguments(args);
		}
		return tweetFragment;
	}

	public Fragment getFragmentOffline() {

		/**
		 * call TwitterService - do async call
		 */
		List<Tweet> tweets = tweetService.refreshTweets();

		// if response != null
		args = mapResponse(tweets);
		if (args != null) {
			tweetFragment.setArguments(args);
		}
		return tweetFragment;
	}

	private Bundle mapResponse(List<Tweet> tweets) {

		for (Tweet tweet : tweets) {
			if (tweet.getText() != null && tweet.getFromUser() != null
					&& tweet.getCreatedAt() != null
					&& tweet.getProfileImageUrl() != null) {
				tweetText.add(tweet.getText());
				tweetUser.add(tweet.getFromUser());
				tweetCreationDate.add(dateCreator(tweet.getCreatedAt()));
				
				tweetUrl.add(tweet.getProfileImageUrl());
			} else {
				System.out.println("tweet partly empty");
			}
		}

		args.putStringArrayList(MainTweetFragment.TWEET_TEXT, tweetText);
		args.putStringArrayList(MainTweetFragment.TWEET_USER, tweetUser);
		args.putStringArrayList(MainTweetFragment.TWEET_CREATION_DATE,
				tweetCreationDate);
		args.putStringArrayList(MainTweetFragment.TWEET_URL, tweetUrl);
		return args;
	}

	private String dateCreator(Date createdAt) {
		String date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(createdAt);
		
		// TODO calculate difference with current date
//		String currentDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
		
		return date;
	}

}
