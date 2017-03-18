package com.solutishackaton2017.psy.service;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.Tone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class ToneAnalyzerService {

	private ToneAnalyzerService() {
		
	}
	
	public static ToneAnalyzerService newInstance() {
		return new ToneAnalyzerService();
	}
	
	public ToneAnalysis analyse(final String text) {
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("b023d4b2-696e-4fcb-84d1-0252abdc8db1", "UGuo3ZsalbeU");
		
		ToneOptions options = new ToneOptions.Builder()
				.addTone(Tone.EMOTION)
				.build();
		
		return service.getTone(text, options).execute();
	}
}