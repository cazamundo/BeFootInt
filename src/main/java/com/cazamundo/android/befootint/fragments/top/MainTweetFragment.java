package com.cazamundo.android.befootint.fragments.top;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cazamundo.android.befootint.R;

public class MainTweetFragment extends Fragment {
	
	public static final String TWEET_TEXT = "tweet_text";
	public static final String TWEET_USER = "tweet_user";
	public static final String TWEET_CREATION_DATE = "tweet_creation_date";
	public static final String TWEET_URL = "tweet_url";
	
	private ArrayList<String> tweetText = new ArrayList<String>();
	private ArrayList<String> tweetUser = new ArrayList<String>();
	private ArrayList<String> tweetCreationDate = new ArrayList<String>();
	private ArrayList<String> tweetUrl = new ArrayList<String>();
	
	private LayoutInflater inflater;
	private ViewGroup container;
	
	private View mainView;
	private LinearLayout tweetContainer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.inflater = inflater;
		this.container = container;
		init();
		
		return mainView;
	}

	private void init() {
		mainView = inflater.inflate(R.layout.twitter_top_fragment, container,
				false);
		tweetContainer = (LinearLayout) mainView.findViewById(R.id.tweetContainer);
		
		loopTweets();		
	}

	private void loopTweets() {
		
		tweetText = getArguments().getStringArrayList(MainTweetFragment.TWEET_TEXT);
		tweetUser = getArguments().getStringArrayList(MainTweetFragment.TWEET_USER);
		tweetCreationDate = getArguments().getStringArrayList(MainTweetFragment.TWEET_CREATION_DATE);
		
		/**
		 * TODO: check how to do with url image
		 */
		tweetUrl = getArguments().getStringArrayList(MainTweetFragment.TWEET_URL);
		
		RelativeLayout tweetLayout;
		for(int i=0;i<tweetText.size();i++){
			tweetLayout = (RelativeLayout) inflater.inflate(R.layout.tweet, null);
			
			//set tweetText field
			TextView tweetTextField = (TextView) tweetLayout.findViewById(R.id.tweetText);
			tweetTextField.setText(tweetText.get(i));
			
			//set creationDate field
			TextView creationDateField = (TextView) tweetLayout.findViewById(R.id.creationDate);
			creationDateField.setText(tweetCreationDate.get(i));
			
			//set userName field
			TextView userNameField = (TextView) tweetLayout.findViewById(R.id.userName);
			userNameField.setText(tweetUser.get(i));
			
			//set icon field
			/**
			 * TODO: how to get icon from twitter?
			 */
			
			tweetContainer.addView(tweetLayout);
		}
	}
}
