package com.solutishackaton2017.psy;

import spark.Spark;

public class Application {

	public static void main(String[] args) {
		Spark.staticFiles.location("/public");
		
		Spark.get("/", (request, response) -> {
	        response.type("text/html");
	        
			return "index.html";
		});
		
		Spark.get("/login", (request, response) -> {
	        response.type("text/html");
	        
			return "login.html";
		});
	}
}