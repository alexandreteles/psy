package com.solutishackaton2017.psy;

import com.solutishackaton2017.psy.transformer.JsonTransformer;

import spark.Spark;

public class Application {

	public static void main(String[] args) {
		Spark.get("/", (request, response) -> {
		    return "";
		}, new JsonTransformer());
	}
}