package com.solutishackaton2017.psy.twitter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterGet {
	
	private TwitterGet() {
		
	}
	
	public static TwitterGet newInstance() {
		return new TwitterGet();
	}
	
	public List<Status> pegarLinhaDoTempo(String twitterID) {
		ConfigurationBuilder cb = new ConfigurationBuilder()
				.setOAuthConsumerKey("sYoX8RxGnVR6d870IYNibNwm4")
				.setOAuthConsumerSecret("qnoBMXpvCAhy2uD3opNZ0bj0vuhRbLKsQKp71a13n1693i8v6Z")
				.setOAuthAccessToken("174270011-TLkpsjYCR8vvvlTI2X2VffaHfrhMYv4ta1D8qthZ")
				.setOAuthAccessTokenSecret("wYIGhRnr92b5JCQ5CPkFtU4XoWg4IJPu1TjbCDH2DJJFg");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		List<Status> statuses;
		
		try {
			Paging page = new Paging(1, 2000);
			statuses = twitter.getUserTimeline(twitterID, page);
		} catch (TwitterException e) {
			return new ArrayList<Status>();
		}
		
		return statuses;
	}
	
	public String converTweetsToText(List<Status> statuses) {
		return statuses.stream()
				.filter(status -> !status.isRetweet())
				.map(status -> status.getText().replaceAll("[^(\\x20-\\x7F)]*", ""))
				.collect(Collectors.joining(" "));
	}
	
	public String convertTweetsToPIContentItems(List<Status> tweets) throws Exception {
        StringWriter content = new StringWriter();
		JsonFactory factory = new JsonFactory();
		JsonGenerator gen = factory.createGenerator(content);
		gen.writeStartObject();
		gen.writeArrayFieldStart("contentItems");
        
        if (tweets.size() > 0) {
        	String userIdStr = Long.toString(tweets.get(0).getUser().getId());
        	
	        for (Status status : tweets) {
				// Add the tweet text to the contentItems
	        	gen.writeStartObject();
	        	gen.writeStringField("userid", userIdStr);
	        	gen.writeStringField("id", Long.toString(status.getId()));
	        	gen.writeStringField("sourceid", "twitter4j");
	        	gen.writeStringField("contenttype", "text/plain");
	        	gen.writeStringField("language", status.getLang());
	        	gen.writeStringField("content", status.getText().replaceAll("[^(\\x20-\\x7F)]*",""));
	        	gen.writeNumberField("created", status.getCreatedAt().getTime());
	        	gen.writeBooleanField("reply", (status.getInReplyToScreenName() != null));
	        	gen.writeBooleanField("forward", status.isRetweet());
	        	gen.writeEndObject();
	        }
        }
        
        gen.writeEndArray();
        gen.writeEndObject();
        gen.flush();

        return content.toString();
	}
}