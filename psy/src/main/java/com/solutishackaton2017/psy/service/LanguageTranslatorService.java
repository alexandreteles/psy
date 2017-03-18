package com.solutishackaton2017.psy.service;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

public class LanguageTranslatorService {

	private LanguageTranslatorService() {
		
	}
	
	public static LanguageTranslatorService newInstance() {
		return new LanguageTranslatorService();
	}
	
	public TranslationResult translate(String text, Language from, Language to) {
		LanguageTranslator translator = new LanguageTranslator();
		translator.setUsernameAndPassword("cb6de7fc-dcba-4088-9030-8c17463163f7", "Rej1QFPN3s2D");
		
		return translator.translate(text, from, to).execute();
	}
}