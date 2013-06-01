package com.cazamundo.android.befootint.fragments.top;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.Tweet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cazamundo.android.befootint.R;
import com.cazamundo.android.befootint.model.TweetDTO;
import com.cazamundo.android.befootint.twitter.TweetBuilder;
import com.cazamundo.android.befootint.twitter.TweetServiceImpl;

public class MainTweetFragment extends Fragment {

	private LayoutInflater inflater;
	private ViewGroup container;

	private View mainView;
	private LinearLayout tweetContainer;
	
	private TweetBuilder tweetBuilder;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.inflater = inflater;
		this.container = container;
		init();
		initialiseTweets();

		return mainView;
	}
/*
 * Builds the container of the mainTweetFragment
 */
	private void init() {
		mainView = inflater.inflate(R.layout.twitter_top_fragment, container,
				false);
		tweetContainer = (LinearLayout) mainView
				.findViewById(R.id.tweetContainer);
	}
	
	/*
	 * Searches the last tweets via Async TweetService
	 */
	public void initialiseTweets(){
		tweetBuilder = new TweetBuilder();
		new FetchTimelineTask().execute();
//		tweetBuilder = new TweetBuilder(this);
//		tweetBuilder.initTweets();
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class FetchTimelineTask extends AsyncTask<Void, Void, List<Tweet>> {

		@Override
		protected void onPreExecute() {
			// before the network request begins, show a progress indicator
			System.out.println("preExecute");
			// showProgressDialog("Fetching timeline...");
		}

		@Override
		protected List<Tweet> doInBackground(Void... params) {
			try {
				// return twitter.timelineOperations().getHomeTimeline();
				TweetServiceImpl tweetService = new TweetServiceImpl();
				return tweetService.refreshTweets();
//				return twitter.listOperations().getListStatuses(88452545);
			} catch (Exception e) {
				// Log.e(TAG, e.getLocalizedMessage(), e);
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<Tweet> tweets) {
			// after the network request completes, hide the progress indicator
			// dismissProgressDialog();
			showResult(tweetBuilder.mapTweets(tweets));
		}
	}

	public void showResult(List<TweetDTO> tweets) {
		if (tweets != null) {

			RelativeLayout tweetLayout;
			for (TweetDTO tweet:tweets){
				tweetLayout = (RelativeLayout) inflater.inflate(R.layout.tweet,
						null);
				// set tweetText field
				TextView tweetTextField = (TextView) tweetLayout
						.findViewById(R.id.tweetText);
				tweetTextField.setText(tweet.getText());

				// set creationDate field
				TextView creationDateField = (TextView) tweetLayout
						.findViewById(R.id.creationDate);
				creationDateField.setText(tweet.getCreationDate());

				// set userName field
				TextView userNameField = (TextView) tweetLayout
						.findViewById(R.id.userName);
				userNameField.setText(tweet.getUserName());

				// set icon field
				/**
				 * TODO: how to get icon from twitter?
				 */

				tweetContainer.addView(tweetLayout);
			}
			
		}		
		
	}
}
