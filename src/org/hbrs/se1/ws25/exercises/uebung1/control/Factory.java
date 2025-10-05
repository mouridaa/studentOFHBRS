package org.hbrs.se1.ws25.exercises.uebung1.control;

public class Factory {

    public Translator createTranslator(){
        return new GermanTranslator();
    }

    public void create(){

    }

}
