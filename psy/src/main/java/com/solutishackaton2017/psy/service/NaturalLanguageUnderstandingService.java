package com.solutishackaton2017.psy.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.solutishackaton2017.psy.json.Entities;
import com.solutishackaton2017.psy.nlu.NaturalLanguageUnderstandingExample;

import twitter4j.Status;

public class NaturalLanguageUnderstandingService {

	private NaturalLanguageUnderstandingService() {
		
	}

	public static NaturalLanguageUnderstandingService newInstance() {
		return new NaturalLanguageUnderstandingService();
	}
	
	public AnalysisResults analyze(List<Status> statuses) throws FileNotFoundException {
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
		
		service.setUsernameAndPassword("5dfeadc1-efb6-4d69-9b30-209415ff3045", "enktAX2WcdPo");
		
		Reader reader = reader();
		
		Entities entities = new Gson().fromJson(reader, Entities.class);
		
		List<String> emotionTarget = new ArrayList<>();
		
		emotionTarget.addAll(entities.getDesgosto());
		emotionTarget.addAll(entities.getMedo());
		emotionTarget.addAll(entities.getRaiva());
		emotionTarget.addAll(entities.getTristeza());
		
		EmotionOptions emotionOptions = new EmotionOptions.Builder()
				.targets(emotionTarget)
				.build();
		
		List<String> sentimentTarget = new ArrayList<>();

		sentimentTarget.addAll(entities.getDesgosto());
		sentimentTarget.addAll(entities.getMedo());
		sentimentTarget.addAll(entities.getRaiva());
		sentimentTarget.addAll(entities.getTristeza());
		
		SentimentOptions sentimentOptions = new SentimentOptions.Builder()
				.targets(sentimentTarget)
				.build();
		
		Features features = new Features.Builder()
				.emotion(emotionOptions)
				.sentiment(sentimentOptions)
				.build();
		
		String text = statuses.stream()
				.filter(e -> !e.isRetweet())
				.map(e -> e.getText().replaceAll("[^(\\x20-\\x7F)]*", ""))
				.collect(Collectors.joining(" "));
		
		AnalyzeOptions options = new AnalyzeOptions.Builder()
				.features(features)
				.language(Language.PORTUGUESE.name())
				.text(text) 				
				.build();
		
		return service.analyze(options).execute();
	}
	
	private Reader reader() throws FileNotFoundException {
		String path = NaturalLanguageUnderstandingExample.class.getClassLoader().getResource("entites.json").getFile();
		
		File file = new File(path);
		
		return new FileReader(file);
	}
}