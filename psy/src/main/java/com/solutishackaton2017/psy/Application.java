package com.solutishackaton2017.psy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import spark.Spark;

public class Application {

	private static final String basePath = "C:\\Desenvolvimento\\IDE\\jee-neon\\workspace\\solutishackathon2017\\psy\\src\\main\\resources\\public\\";
	
	public static void main(String[] args) {
		Spark.staticFiles.location("/public");
		
		Spark.get("/", (request, response) -> renderContent("index.html"));
		
		Spark.get("/login", (request, response) -> renderContent("login.html"));
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