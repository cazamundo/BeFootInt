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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.twitter_top_fragment, container,
				false);
		LinearLayout tweetContainer = (LinearLayout) view.findViewById(R.id.tweetContainer);
		
		RelativeLayout tweetView = (RelativeLayout) inflater.inflate(R.layout.tweet, null);
		tweetContainer.addView(tweetView);
		
		loopTweets(inflater,tweetContainer);
		
		return view;
	}

	private void loopTweets(LayoutInflater inflater, LinearLayout tweetContainer) {
		
		RelativeLayout tweetLayout;
		int j=7;
		for(int i=0;i<j;i++){
			tweetLayout = (RelativeLayout) inflater.inflate(R.layout.tweet, null);
			TextView tweetText2 = (TextView) tweetLayout.findViewById(R.id.tweetText);
			tweetText2.setText("dit is de zoveelste tekst:" + i);
			tweetContainer.addView(tweetLayout);
		}
	}
}
