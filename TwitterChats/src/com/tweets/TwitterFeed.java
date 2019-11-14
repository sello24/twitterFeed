package com.tweets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Sello Mamorobela
 * 
 */

public class TwitterFeed{

   public static void main(String [] args){
      HashMap user_followers = new HashMap<String,ArrayList>();
      HashMap tweetFeed = new HashMap<String,ArrayList>();
      ArrayList<String> allUsers = new ArrayList<String>();
      
      try{

         BufferedReader users = new BufferedReader(new FileReader("user.txt"));
         BufferedReader tweets = new BufferedReader(new FileReader("tweet.txt"));
         
         processUsers(user_followers, allUsers, users);

         processTweets(user_followers, tweetFeed, tweets); 
             
       processFinalTweets(tweetFeed, allUsers);
          users.close();
          tweets.close();
      }
      catch (IOException e) {
          e.printStackTrace();   
      }
   }

public static String processFinalTweets(HashMap tweetFeed, ArrayList<String> allUsers) {
	  Collections.sort(allUsers);
	  String processed = "processedTweet";

	for (String key : allUsers) {
		System.out.println(key);
		if (tweetFeed.containsKey(key)) {
		       List<String> sortedtweetList = (ArrayList)tweetFeed.get(key);
		       for(String sortedfinalTweets: sortedtweetList){
		          System.out.println( sortedfinalTweets );
		        
		       }
		 		} 
	       else {
	    	   System.out.println();
	    	  
	         }
		
	  }
	return processed;
}
 
public static String processUsers(HashMap user_followers, ArrayList<String> allUsers, BufferedReader users)
		throws IOException {
	String userTweetLine;
	String follower;
	String userKey;
	String processed = null;
	while ((userTweetLine = users.readLine()) != null) {
	     String[] strArray =  userTweetLine.split(" follows |, ");
	     follower = strArray[0];
	 if (!allUsers.contains(follower)) {
	allUsers.add(follower);
	}
	     for(int i=1;i<strArray.length;i++){
	         userKey = strArray[i];
	          if(user_followers.containsKey(userKey)){
	             List<String> existingList = (ArrayList)user_followers.get(userKey);
	             if(!existingList.contains(follower)){
	                existingList.add(follower);
	             }
	             user_followers.put(userKey,existingList);
	             processed = "ProcessedFollower";
	          }
	          else{
	          
	             List<String> followersList = new ArrayList<String>();
	             followersList.add(follower);
	             user_followers.put(userKey,followersList);   
	             processed = "ProcessedFollower";
	          
	          }
	  if (!allUsers.contains(userKey)) {
		allUsers.add(userKey);
	  }

	      }
	  }
	return processed;
}

public static String processTweets(HashMap user_followers, HashMap tweetFeed, BufferedReader tweets) throws IOException {
	String tweet_line;
	String tweet;
	String tweetUser;
	String tweeted = null;
	while ((tweet_line = tweets.readLine()) != null) {
	       String[] tweetsArray = tweet_line.split(">");
	       tweetUser = tweetsArray[0];
	       tweet = tweetsArray[1];
	       if(tweetFeed.containsKey(tweetUser)){
	          List<String> existingTweets = (ArrayList)tweetFeed.get(tweetUser);
	          existingTweets.add("@"+tweetUser + ": " + tweet);
	          tweetFeed.put(tweetUser,existingTweets);
	          tweeted = "tweeted";
	          
	          
	       }
	       else{
	          List<String> userTweets = new ArrayList<String>();
	          userTweets.add("@"+tweetUser + ": " + tweet);
	          tweetFeed.put(tweetUser,userTweets);
	          tweeted = "tweeted";
	       
	       }
	       ArrayList<String> userFollowers = (ArrayList)user_followers.get(tweetUser); 
	       if(userFollowers != null){
	          for(String uFollower: userFollowers){
	            
	             if(tweetFeed.containsKey(uFollower)){
	                
	                List<String> folowerTweets = (ArrayList)tweetFeed.get(uFollower);
	                folowerTweets.add("@"+tweetUser + ": " + tweet);
	                tweetFeed.put(uFollower,folowerTweets);
	                tweeted = "tweeted";
	                
	                
	             }
	             else{
	                
	                List<String> folowerTweets1 = new ArrayList<String>();
	                folowerTweets1.add("@"+tweetUser + ": " + tweet);
	                tweetFeed.put(uFollower,folowerTweets1);
	                tweeted = "tweeted";
	             }
	             
	          
	          }
	          
	          tweeted = "tweeted";
	       }
	       
	      
	  }
	return tweeted;
}
}
