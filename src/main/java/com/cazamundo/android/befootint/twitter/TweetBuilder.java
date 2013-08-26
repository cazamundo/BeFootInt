/**
 * 
 */
package com.cazamundo.android.befootint.twitter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.twitter.api.Tweet;

import android.os.Environment;
import android.util.Log;

import com.cazamundo.android.befootint.model.TweetDTO;

/**
 * @author cazamundo
 * 
 */
public class TweetBuilder {

	private static final String TAG = "TweetBuilder";

	public TweetBuilder() {
		Log.v(TAG, "CONSTRUCT");
	}

	public List<TweetDTO> mapTweets(List<Tweet> tweets) {
		if (tweets != null) {

			List<TweetDTO> tweetDTOList = new ArrayList<TweetDTO>();

			for (Tweet tweet : tweets) {
				if (tweetValid(tweet)) {
					writeTweetTofile(tweet);
					TweetDTO tweetDTO = new TweetDTO();
					tweetDTO.setText(tweet.getText());
					tweetDTO.setUserName(tweet.getFromUser());
					tweetDTO.setCreationDate(dateCreator(tweet.getCreatedAt()));
					tweetDTO.setImageUrl(tweet.getProfileImageUrl());
					tweetDTOList.add(tweetDTO);
				} else {
					// TODO add error
					Log.e(TAG + " mapTweets", "tweet partly empty");
				}
			}
			return tweetDTOList;
		} else {
			// TODO add error
			Log.e(TAG + " mapTweets", "No tweets available");
			return null;
		}
	}

	private void writeTweetTofile(Tweet tweet) {
		ObjectMapper obMapper = new ObjectMapper();
		try {
//			getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

//			obMapper.writeValue(
//					new File(
//							"/Users/cazamundo/Documents/Development/mobile/_trash_trials/test.json"),
//					tweet);
			if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED){
				obMapper.writeValue(new File(
						Environment
								.getExternalStorageDirectory()+ "/json/", "testje.json"),
						tweet);
			}else{
				obMapper.writeValue(new File(
						Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/json/test.json"),
						tweet);
			}
			
			readFile();
			
			
			
		} catch (JsonGenerationException e) {
			Log.e(TAG, "json generation exception");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ObjectWriter ow = new ObjectWriter(obMapper,
		// obMapper.copySerializationConfig());

	}

	private void readFile() {
		ObjectMapper obMapper = new ObjectMapper();
//		Tweet tweeter = obMapper.readValue(root, Tweet.class);
//		 getExternalFilesDir()
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
