package com.cazamundo.android.befootint.fragments.top;

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
		
		RelativeLayout tweetLayout;
		int j=15;
		for(int i=0;i<j;i++){
			tweetLayout = (RelativeLayout) inflater.inflate(R.layout.tweet, null);
			TextView tweetText2 = (TextView) tweetLayout.findViewById(R.id.tweetText);
			tweetText2.setText("dit is de zoveelste tekst:" + i);
			tweetContainer.addView(tweetLayout);
		}
	}
}
