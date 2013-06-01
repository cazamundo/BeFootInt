/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.twitter.api.Tweet;

import com.cazamundo.android.befootint.fragments.top.MainTweetFragment;
import com.cazamundo.android.befootint.model.TweetDTO;

/**
 * @author cazamundo
 * 
 */
public class TweetBuilder {

	public List<TweetDTO> mapTweets(List<Tweet> tweets) {
		if (tweets != null) {

			List<TweetDTO> tweetDTOList = new ArrayList<TweetDTO>();

			for (Tweet tweet : tweets) {
				if (tweetValid(tweet)) {
					TweetDTO tweetDTO = new TweetDTO();
					tweetDTO.setText(tweet.getText());
					tweetDTO.setUserName(tweet.getFromUser());
					tweetDTO.setCreationDate(dateCreator(tweet.getCreatedAt()));
					tweetDTO.setImageUrl(tweet.getProfileImageUrl());
					tweetDTOList.add(tweetDTO);
				} else {
					// TODO add error
					System.out.println("tweet partly empty");
				}
			}
			return tweetDTOList;
		} else {
			// TODO add error
			return null;
		}
	}

	private boolean tweetValid(Tweet tweet) {
		if (tweet.getText() != null && tweet.getFromUser() != null
				&& tweet.getCreatedAt() != null
				&& tweet.getProfileImageUrl() != null) {
			return true;
		}
		return false;
	}

	private String dateCreator(Date createdAt) {
		String date = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.MEDIUM).format(createdAt);

		// TODO calculate difference with current date
		// String currentDate = DateFormat.getDateTimeInstance(DateFormat.SHORT,
		// DateFormat.SHORT).format(new Date());

		return date;
	}

}
