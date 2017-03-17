package com.solutishackaton2017.psy.nlu;

import java.util.Arrays;

import com.ibm.watson.developer_cloud.language_translation.v2.model.Language;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.solutishackaton2017.psy.transformer.JsonTransformer;

import spark.Spark;

public class NaturalLanguageUnderstandingExample {
	
	public static void main(String[] args) {
		Spark.get("/", (request, response) -> { return execute(); }, new JsonTransformer());
	}
	
	public static AnalysisResults execute() {
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
		
		service.setUsernameAndPassword("5dfeadc1-efb6-4d69-9b30-209415ff3045", "enktAX2WcdPo");
		
		EmotionOptions emotionOptions = new EmotionOptions.Builder()
				.targets(Arrays.asList("receio", "ansioso"))
				.build();
		
		SentimentOptions sentimentOptions = new SentimentOptions.Builder()
				.targets(Arrays.asList("receio", "ansioso"))
				.build();
		
		Features features = new Features.Builder()
				.emotion(emotionOptions)
				.sentiment(sentimentOptions)
				.build();
		
		AnalyzeOptions options = new AnalyzeOptions.Builder()
				.features(features)
				.language(Language.PORTUGUESE.name())
				.text("receio, receio, receio, receio receio.")
				.build();
		
		return service.analyze(options).execute();
	}
}