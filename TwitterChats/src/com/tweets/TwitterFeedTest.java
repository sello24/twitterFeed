package com.tweets;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TwitterFeedTest {
	
	  HashMap user_followers = new HashMap<String,ArrayList>();
	  HashMap tweetFeed = new HashMap<String,ArrayList>();
	  ArrayList<String> allUsers = new ArrayList<String>();



	@Test
	public void testProcessUsers() throws IOException {
		 BufferedReader users = new BufferedReader(new FileReader("user.txt"));
         BufferedReader tweets = new BufferedReader(new FileReader("tweet.txt"));
         assertEquals("ProcessedFollower", TwitterFeed.processUsers(user_followers, allUsers, users)); 
	}

	@Test
	public void testProcessTweets() throws IOException {
		 BufferedReader users = new BufferedReader(new FileReader("user.txt"));
         BufferedReader tweets = new BufferedReader(new FileReader("tweet.txt"));
         assertEquals("tweeted", TwitterFeed.processTweets(user_followers, tweetFeed, tweets)); 
	}
	@Test
	public void testProcessFinalTweets() throws FileNotFoundException {
			
		 BufferedReader users = new BufferedReader(new FileReader("user.txt"));
         BufferedReader tweets = new BufferedReader(new FileReader("tweet.txt"));
         assertEquals("processedTweet", TwitterFeed.processFinalTweets(tweetFeed, allUsers)); 
	}
}
