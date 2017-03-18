package com.solutishackaton2017.psy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.solutishackaton2017.psy.service.LanguageTranslatorService;
import com.solutishackaton2017.psy.service.ToneAnalyzerService;
import com.solutishackaton2017.psy.transformer.JsonTransformer;
import com.solutishackaton2017.psy.twitter.TwitterGet;

import spark.Spark;
import twitter4j.Status;

public class Application {

	private static final String basePath = "C:\\Desenvolvimento\\IDE\\jee-neon\\workspace\\solutishackathon2017\\psy\\src\\main\\resources\\public\\";
	
	public static void main(String[] args) {
		Spark.staticFiles.location("/public");
		
		Spark.get("/", (request, response) -> renderContent("index.html"));
		
		Spark.get("/analisar", (request, response) -> renderContent("analisar.html"));
		
		Spark.post("/efetuarAnalise", (request, response) ->  {
			String twitterHandle = request.body();
			
			TwitterGet twitterGet = TwitterGet.newInstance();
			
			List<Status> statuses = twitterGet.pegarLinhaDoTempo(twitterHandle.split("=")[1]);
			
			LanguageTranslatorService translatorService = LanguageTranslatorService.newInstance();
			
			String text = translatorService
					.translate(twitterGet.converTweetsToText(statuses), Language.PORTUGUESE, Language.ENGLISH)
					.getFirstTranslation();
			
			ToneAnalyzerService toneAnalyserService = ToneAnalyzerService.newInstance();
			
			ToneAnalysis analysis = toneAnalyserService.analyse(text);
			
			System.out.println(analysis);
			
			return analysis;
		}, new JsonTransformer()) ;
		
		Spark.get("/dashboard", (request, response)-> {
			return renderContent("dashboard.html");
		});
	}
	
	private static String renderContent(String htmlFile) {
	    try {
	        Path path = Paths.get(basePath + htmlFile);
	        return new String(Files.readAllBytes(path), Charset.defaultCharset());
	    } catch (IOException e) {
	        // Add your own exception handlers here.
	    }
	    return null;
	}
}