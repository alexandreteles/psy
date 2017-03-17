package com.solutishackaton2017.psy;

import spark.Spark;

public class Application {

	public static void main(String[] args) {
		Spark.get("/", (request, response) -> {
		    return "";
		});
	}
}