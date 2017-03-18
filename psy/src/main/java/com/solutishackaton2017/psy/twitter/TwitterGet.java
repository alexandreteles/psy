package com.solutishackaton2017.psy.twitter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterGet {
	
	public List<Status> pegarLinhaDoTempo(String twitterID) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("sYoX8RxGnVR6d870IYNibNwm4");
		cb.setOAuthConsumerSecret("qnoBMXpvCAhy2uD3opNZ0bj0vuhRbLKsQKp71a13n1693i8v6Z");
		cb.setOAuthAccessToken("174270011-TLkpsjYCR8vvvlTI2X2VffaHfrhMYv4ta1D8qthZ");
		cb.setOAuthAccessTokenSecret("wYIGhRnr92b5JCQ5CPkFtU4XoWg4IJPu1TjbCDH2DJJFg");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		List<Status> statuses;
		int pageno = 1;
		try {
			Paging page = new Paging(pageno, 2000);
			statuses = twitter.getUserTimeline(twitterID, page);
		} catch (TwitterException e) {
			e.printStackTrace();
			return new ArrayList<Status>();
		}
		return statuses;
	}
}