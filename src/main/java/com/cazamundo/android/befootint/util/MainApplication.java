/**
 * 
 */
package com.cazamundo.android.befootint.util;

import org.springframework.security.crypto.encrypt.AndroidEncryptors;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.sqlite.SQLiteConnectionRepository;
import org.springframework.social.connect.sqlite.support.SQLiteConnectionRepositoryHelper;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import com.cazamundo.android.befootint.R;

/**
 * @author cazamundo
 * 
 */
public class MainApplication extends Application {
	
	protected static final String TAG = MainApplication.class
			.getSimpleName();
	
	private ConnectionFactoryRegistry connectionFactoryRegistry;

	private ConnectionRepository connectionRepository;

	private SQLiteOpenHelper repositoryHelper;

	// ***************************************
	// Application Methods
	// ***************************************
	@Override
	public void onCreate() {
		this.connectionFactoryRegistry = new ConnectionFactoryRegistry();
		this.connectionFactoryRegistry
				.addConnectionFactory(new TwitterConnectionFactory(
						getTwitterConsumerToken(),
						getTwitterConsumerTokenSecret()));

		// set up the database and encryption
		this.repositoryHelper = new SQLiteConnectionRepositoryHelper(this);
		this.connectionRepository = new SQLiteConnectionRepository(
				this.repositoryHelper, this.connectionFactoryRegistry,
				AndroidEncryptors.text("password", "5c0744940b5c369b"));
	}

	// ***************************************
	// Private methods
	// ***************************************
	private String getTwitterConsumerToken() {
		return getString(R.string.twitter_consumer_key);
	}

	private String getTwitterConsumerTokenSecret() {
		return getString(R.string.twitter_consumer_key_secret);
	}

	// ***************************************
	// Public methods
	// ***************************************

	public ConnectionRepository getConnectionRepository() {
		return this.connectionRepository;
	}

	public TwitterConnectionFactory getTwitterConnectionFactory() {
		return (TwitterConnectionFactory) this.connectionFactoryRegistry.getConnectionFactory(Twitter.class);
	}

}
