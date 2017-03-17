package com.solutishackaton2017.psy;

import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.solutishackaton2017.psy.transformer.JsonTransformer;

import spark.Spark;

public class Application {

	public static void main(String[] args) {
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("user", "password");
		
		LanguageTranslator translator = new LanguageTranslator();
		translator.setUsernameAndPassword("user", "password");
		
		
		String text = "O poeta é um fingidor"
		+"Finge tão completamente"
+"Que chega a fingir que é dor"
+"A dor que deveras sente."

+"E os que lêem o que escreve,"
+"Na dor lida sentem bem,"
+"Não as duas que ele teve,"
+"Mas só a que eles não têm"

+"E assim nas calhas da roda"
+"Gira, a entreter a razão,"
+"Esse comboio de corda"
+"Que se chama o coração.";
		
		
	TranslationResult result = translator.translate(text, Language.PORTUGUESE, Language.ENGLISH).execute();
		
		
		ToneAnalysis tone = service.getTone(result.getFirstTranslation(), null).execute();
		System.out.println(tone);
		
		
		Spark.get("/", (request, response) -> {
		    return tone;
		}, new JsonTransformer());
	}
}