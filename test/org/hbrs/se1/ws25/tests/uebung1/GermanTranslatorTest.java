package org.hbrs.se1.ws25.tests.uebung1;

import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GermanTranslatorTest {

    @Test
    public void aTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl 0 nicht möglich. Version: 1.0" , translator.translateNumber(0));
    }

    @Test
    public void bTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl -5 nicht möglich. Version: 1.0" , translator.translateNumber(-5));

    }

    @Test
    public void cTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("fünf" , translator.translateNumber(5));

    }

    @Test
    public void dTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl 15 nicht möglich. Version: 1.0" , translator.translateNumber(15));

    }

    @Test
    public void eTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("eins" , translator.translateNumber(1));

    }

    @Test
    public void fTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("zehn" , translator.translateNumber(10));

    }

    @Test
    public void gTest() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl 11 nicht möglich. Version: 1.0" , translator.translateNumber(11));

    }



}