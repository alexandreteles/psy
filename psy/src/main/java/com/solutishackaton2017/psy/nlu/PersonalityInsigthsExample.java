package com.solutishackaton2017.psy.nlu;

import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.solutishackaton2017.psy.transformer.JsonTransformer;
import com.solutishackaton2017.psy.twitter.TwitterGet;

import spark.Spark;
import twitter4j.Status;

public class PersonalityInsigthsExample {
	public static void main(String[] args) {
		Spark.get("/", (request, response) -> { return execute(); }, new JsonTransformer());
	}
	
	public static Profile execute() throws RuntimeException, Exception {
		PersonalityInsights service = new PersonalityInsights(PersonalityInsights.VERSION_DATE_2016_10_19);
		
		service.setUsernameAndPassword("a782263b-6c2f-4263-9120-07955a3b704c", "rDgGacGkiLnl");
		
		TwitterGet twitter = new TwitterGet();
		
		Profile profile = service
				.getProfile(convertTweetsToPIContentItems(twitter.pegarLinhaDoTempo("garotadepressi9")))
				.execute();
		
		return profile;
	}
	
	public static String convertTweetsToPIContentItems(List<Status> tweets) throws Exception {
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