package com.solutishackaton2017.psy.nlu;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.Tone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class ToneAnalyzerExample {

	public static void main(String[] args) {
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
	    service.setUsernameAndPassword("c3a3a2df-e4c6-4a29-811f-f0c31b85a783", "yZP171RzpRZa");

	    String text = "I know the times are difficult! Our sales have been "
	        + "disappointing for the past three quarters for our data analytics "
	        + "product suite. We have a competitive data analytics product "
	        + "suite in the industry. But we need to do our job selling it! "
	        + "We need to acknowledge and fix our sales challenges. "
	        + "We can’t blame the economy for our lack of execution! " 
	        + "We are missing critical sales opportunities. "
	        + "Our product is in no way inferior to the competitor products. "
	        + "Our clients are hungry for analytical tools to improve their "
	        + "business outcomes. Economy has nothing to do with it.";

	    ToneOptions options = new ToneOptions.Builder()
	    		.addTone(Tone.EMOTION)
	    		.build();
	    
	    // Call the service and get the tone
	    ToneAnalysis tone = service.getTone(text, options).execute();
	    System.out.println(tone);
	}
}