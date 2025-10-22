package org.hbrs.se1.ws25.tests.uebung3;

import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        // Hier muss der Container einmalig als Singleton instanziiert werden.
        container = Container.getInstance();
    }

    @Test
    void testMongoDBNotImplementedSolution() {

        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<Member>());
        PersistenceException exception =
                assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, exception.getExceptionTypeType());
        assertEquals("MongoDB is not implemented!", exception.getMessage());
        // Set a strategy, which has not been implemented
        // container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );

        // Testing store
        // Hinweis: Beim Aufruf der Methoden store() oder load() muss eine Exception vom Typ
        // PersistenceException zurückgegeben werden. Der ExceptionType lautet ImplementationNotAvailable
        // Die Message (abrufbar mit der Methode e.getMessage() ) muss einen eindeutigen Text haben, z.B.:
        // assertEquals( e.getMessage() , "MongoDB is not implemented!"  );
    }

    @Test
    void testNoStrategeySet() {
        try{
            container.setPersistenceStrategy(null);
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType());
            assertEquals("Es wurde keine Strategie gesetzt", e.getMessage());
        }
        //try{
            // container.setPersistenceStrategie(null);
            // container.store();
        // } catch (PersistenceException e) {
            // Bitte auch hier die Message und den ExceptionType prüfen.
        // }
    }

    @Test
    void testWrongLocationOfFile() {
        try {
            PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<>();
            // Ein Directory ist KEINE gültige Datei → führt zu Fehler
            strat.setLocation("/Users/saschaalda/tmp");
            container.setPersistenceStrategy(strat);
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType());
            assertEquals("Die Location darf kein Verzeichnis sein: /Users/saschaalda/tmp", e.getMessage());
        }
    }


        // try {
           //  PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
            // FileStreams do not like directories, so try this out ;-)
            // strat.setLocation("/Users/saschaalda/tmp");
            // container.setPersistenceStrategie( strat );
            // container.store();

        // } catch (PersistenceException e) {
            // Auch hier diverse Assertions bringen, um die Nachricht und den ExceptionType zu testen.
        // }

    @Test
    void testStoreDeleteAndLoad() {
        try {
            // 1. Alles löschen
            container.deleteAllMembers();
            assertEquals(0, container.size());

            // 2. Strategie setzen
            PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<>();
            strat.setLocation("testdata/memberdata.ser"); // gültige Datei
            container.setPersistenceStrategy(strat);

            // 3. Neues Member-Objekt hinzufügen
            Member m1 = new Member() {
                @Override
                public Integer getID() { return 1; }
            };
            container.addMember(m1);
            assertEquals(1, container.size());

            // 4. Abspeichern
            container.store();

            // 5. Löschen
            container.deleteAllMembers();
            assertEquals(0, container.size());

            // 6. Laden
            container.load();
            List<Member> list = container.getCurrentList();

            // Nach Laden sollte wieder ein Element vorhanden sein
            assertEquals(1, list.size());
            assertEquals(1, list.get(0).getID());

        } catch (PersistenceException e) {
            e.getMessage();
        } catch (ContainerException e) {
            e.getMessage();
        }

        // Testen Sie folgenden RoundTrip:
        // 1. Lösche alle Member-Objekte (Sicher ist sicher! Dazu die Methode deleteAllMember implementieren!)
        // 2. Setzen der Strategie
        // 3. Hinzufügen eines Member-Objekts
        // 4. Abspeichern
        // 5. Löschen des Member-Objekts
        // 6. Laden
        // Die Zustandsänderungen mittels der size() bitte stets testen!
    }

}