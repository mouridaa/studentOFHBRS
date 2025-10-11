package org.hbrs.se1.ws25.exercises.uebung1.factory;


import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws25.exercises.uebung1.control.Translator;

/**
 * Verwendung des design pattern "factory method Pattern"
 * Problem: Ojekte koennen inkosistent erzeugt werden
 * Loesung: Service-Klasse für die konsistente Erzeugung von Translater-Objekten
 */

public class TranslatorFactory {

    public static Translator createGermanTranslator(){
        GermanTranslator translator = new GermanTranslator();
        translator.setDate("Okt/2025");
        return translator;
    }

}
