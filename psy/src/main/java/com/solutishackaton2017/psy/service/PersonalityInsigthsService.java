package com.solutishackaton2017.psy.service;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

public class PersonalityInsigthsService {

	private PersonalityInsigthsService() {
		
	}
	
	public static PersonalityInsigthsService newInstance() {
		return new PersonalityInsigthsService();
	}
	
	public Profile profile(String contentItems) {
		PersonalityInsights service = new PersonalityInsights(PersonalityInsights.VERSION_DATE_2016_10_19);
		
		service.setUsernameAndPassword("a782263b-6c2f-4263-9120-07955a3b704c", "rDgGacGkiLnl");
		
		Profile profile = service.getProfile(contentItems).execute();
		
		return profile;
	}
}