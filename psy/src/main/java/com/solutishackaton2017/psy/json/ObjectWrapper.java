package com.solutishackaton2017.psy.json;

import java.util.Arrays;
import java.util.List;

public class ObjectWrapper {

	private List<Object> objects;
	
	public ObjectWrapper(Object... object) {
		objects = Arrays.asList(object);
	}

	public List<Object> getObjects() {
		return objects;
	}
}