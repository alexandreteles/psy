package com.solutishackaton2017.psy.nlu;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.solutishackaton2017.psy.transformer.JsonTransformer;

import spark.Spark;

public class PersonalityInsigthsExample {
	public static void main(String[] args) {
		Spark.get("/", (request, response) -> { return execute(); }, new JsonTransformer());
	}
	
	public static Profile execute() {
		PersonalityInsights service = new PersonalityInsights(PersonalityInsights.VERSION_DATE_2016_10_19);
		
		service.setUsernameAndPassword("a782263b-6c2f-4263-9120-07955a3b704c", "rDgGacGkiLnl");
		
		String text = "Today is one of those days I woke up and I felt empty. Boyfriend tells me with an irritated undertone "
				+ "that he wants me to make the bed after I get out. And even that's enough to set me off, knowing I'm "
				+ "going to have yet another bad day. It's not his fault. Maybe he is tired of me, and I understand. I'm "
				+ "tired of myself too. Yesterday was spent sobbing at the computer. After keeping everything to myself, "
				+ "about that I dissociate, feel like I can't handle normal things like picking up the phone but eventually "
				+ "managed to get it under control when I talked to one of my internet friends. Yeah, I have a few of those "
				+ "actually. Or I don't. I have people who will play games with me but only 1 who will actually listen. "
				+ "Probably because I was there for him when he needed someone. But yesterday it was me who needed someone, "
				+ "that day it all came to me and it felt too heavy I couldn't keep it to myself. But as I have realised "
				+ "suffering that even thought talking works, I have to do it a lot, every day. And I can't rely on other "
				+ "people because it isn't fair to bring others down so I can get myself up. Today's the 'hangover' of the "
				+ "day before. The day where I wake up feeling like the world has ended and I am completely dead. Everything "
				+ "makes me cry, nothing excites me, I don't want to do anything. I am lucky actually, I live without paying "
				+ "rent so I know I'm not gonna get kicked out. I actually don't do anything, every day. That's right. Every "
				+ "single day I live a life of waking up, get on the computer, go to sleep. I am trying to get a job but I'm "
				+ "scared so what I'm trying to do is keep up with my healthy diet and exercise. Because that's really all "
				+ "you can do with depression, besides I guess having shock therapy or doing medication or something? It's "
				+ "just that even that is hard. Especially on days like these when I literary feel dead inside. It is so hard "
				+ "to just DO SOMETHING, to just make a juice, make myself food, pick up the clothing from the floor, pick up "
				+ "the weights to exercise. It is so hard. I can't believe why this is so hard. Sometimes I wonder what it's "
				+ "like for other people. How does it feel to think you're going to do something and just do it? It's gotta "
				+ "be freeing. Getting on with things. It's gotta feel great, I want that too, I want to touch the feeling of "
				+ "being truly free. But I can't, I'm stuck in a dark hole with concrete walls and today I can't even see the "
				+ "light above me.";
		
		Profile profile = service.getProfile(text).execute();
		
		return profile;
	}
}