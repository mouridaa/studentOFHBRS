package org.hbrs.se1.ws25.exercises.uebung4.task2.model;

import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.UserStoryException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    @Test
    void testConstructorAndGetter() {
        UserStory us = new UserStory(1, "Titel", "Kriterium", 3, 3, 5, 2, "ProjektA");

        assertEquals(1, us.getID());
        assertEquals("Titel", us.getTitel());
        assertEquals(List.of("Kriterium"), us.getAkzeptanzkriterien());
        assertEquals(3, us.getMehrwert());
        assertEquals(3, us.getStrafe());
        assertEquals(5, us.getAufwand());
        assertEquals(2, us.getRisiko());
        assertEquals("ProjektA", us.getProject());
    }

    @Test
    void testAddAkzeptanzkriterium() {
        UserStory us = new UserStory(1);
        us.addAkzeptanzkriterium("Testkriterium");
        assertEquals(1, us.getAkzeptanzkriterien().size());
        assertEquals("Testkriterium", us.getAkzeptanzkriterien().get(0));
    }

    @Test
    void testSetMehrwertInvalid() {
        UserStory us = new UserStory(1);
        assertThrows(UserStoryException.class, () -> us.setMehrwert(0));
        assertThrows(UserStoryException.class, () -> us.setMehrwert(6));
    }

    @Test
    void testSetStrafeInvalid() {
        UserStory us = new UserStory(1);
        assertThrows(UserStoryException.class, () -> us.setStrafe(0));
        assertThrows(UserStoryException.class, () -> us.setStrafe(6));
    }

    @Test
    void testSetAufwandInvalid() {
        UserStory us = new UserStory(1);
        assertThrows(UserStoryException.class, () -> us.setAufwand(0));
    }

    @Test
    void testSetRisikoInvalid() {
        UserStory us = new UserStory(1);
        assertThrows(UserStoryException.class, () -> us.setRisiko(0));
        assertThrows(UserStoryException.class, () -> us.setRisiko(6));
    }

    @Test
    void testGetPrio() throws UserStoryException {
        UserStory us = new UserStory(1);
        us.setMehrwert(3);
        us.setStrafe(3);
        us.setAufwand(2);
        us.setRisiko(1);

        assertEquals(2.0, us.getPrio());
    }

    @Test
    void testEquals() {
        UserStory us1 = new UserStory(1);
        UserStory us2 = new UserStory(1);
        UserStory us3 = new UserStory(2);

        assertEquals(us1, us2);
        assertNotEquals(us1, us3);
    }

    @Test
    void testCompareTo() throws UserStoryException {
        UserStory a = new UserStory(1);
        a.setMehrwert(5);
        a.setStrafe(5);
        a.setAufwand(1);
        a.setRisiko(1);

        UserStory b = new UserStory(2);
        b.setMehrwert(1);
        b.setStrafe(1);
        b.setAufwand(5);
        b.setRisiko(5);

        // a hat höhere Prio → sollte vor b kommen → compareTo < 0
        assertTrue(a.compareTo(b) < 0);
    }
}