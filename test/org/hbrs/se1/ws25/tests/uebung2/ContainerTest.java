package org.hbrs.se1.ws25.tests.uebung2;

import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws25.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.exercises.uebung2.Container;
import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {

    private Container c; // Instanzvariable
    private Member m1;
    private Member m2;

    @BeforeEach
    public void setUp() {
        c = new Container();
        m1 = new ConcreteMember(5);
        m2 = new ConcreteMember(10);
    }

    @Test
    public void testEmptyContainer() {
        // Noch kein Member hinzugefügt
        assertEquals(0, c.size());
    }

    @Test
    public void testAddSingleMember() throws ContainerException {
        // Neuen Member hinzufügen
        c.addMember(m1);
        assertEquals(1, c.size());
    }

    @Test
    public void testAddTwoMembers() throws ContainerException {
        // Zwei Member hinzufügen
        c.addMember(m1);
        c.addMember(m2);
        assertEquals(2, c.size());
    }

    @Test
    public void testAddDuplicateMembers() throws ContainerException {
        // Vorhandenen Member hinzufügen → Exception
        c.addMember(m1);
        assertThrows(ContainerException.class, () -> c.addMember(m1));

        c.addMember(m2);
        assertThrows(ContainerException.class, () -> c.addMember(m2));
    }

    @Test
    public void testDeleteExistingMembers() throws ContainerException {
        // Member löschen
        c.addMember(m1);
        c.addMember(m2);

        c.deleteMember(5);
        assertEquals(1, c.size());

        c.deleteMember(10);
        assertEquals(0, c.size());
    }

    @Test
    public void testDeleteNonExistingMember() {
        // Nicht vorhandenen Member löschen → Exception
        assertEquals("Zur übergebenen ID ist kein Member-Objekt gespeichert", c.deleteMember(20));
    }

    @Test
    public void testAddNull() throws ContainerException {
        c.addMember(m1);
        assertEquals(1, c.size());
        c.addMember(null);
        assertEquals(1, c.size());
    }
}




