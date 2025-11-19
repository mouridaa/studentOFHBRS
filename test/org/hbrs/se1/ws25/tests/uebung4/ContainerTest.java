package org.hbrs.se1.ws25.tests.uebung4;

import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.PersistenceException;
import org.hbrs.se1.ws25.exercises.uebung4.task2.model.Container;
import org.hbrs.se1.ws25.exercises.uebung4.task2.model.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws25.exercises.uebung4.task2.model.PersistenceStrategyStream;
import org.hbrs.se1.ws25.exercises.uebung4.task2.model.UserStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp()
    {
        container = Container.getInstance();
    }

    @Test
    void testNoStrategeySet() {
        try {
            container.setPersistenceStrategy(null);
            container.store();
        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals( "Strategy not initialized", e.getMessage() );
            assertEquals( PersistenceException.ExceptionType.NoStrategyIsSet ,
                    e.getExceptionTypeType()  );
        }
    }

    @Test
    void testMongoDBNotImplementedOldFashioned() {
        try {
            container.setPersistenceStrategy( new PersistenceStrategyMongoDB<UserStory>() );
            container.store();
        } catch (PersistenceException e) {
            assertEquals("MongoDB is not implemented!" , e.getMessage());
            assertEquals( e.getExceptionTypeType() ,
                    PersistenceException.ExceptionType.ImplementationNotAvailable );
        }
    }

    @Test
    void testMongoDBNotImplementedHipSolution() {
        // Set a strategy, which has not been implemented
        container.setPersistenceStrategy( new PersistenceStrategyMongoDB<UserStory>() );

        // Testing store
        PersistenceException e = assertThrows(
                PersistenceException.class , () -> container.store() );
        assertEquals("MongoDB is not implemented!"  , e.getMessage());
        assertEquals(  e.getExceptionTypeType() , PersistenceException.
                ExceptionType.ImplementationNotAvailable );

        // Do the same with load
        e = assertThrows( PersistenceException.class , () -> container.load() );
        assertEquals("MongoDB is not implemented!", e.getMessage());
        assertEquals(  e.getExceptionTypeType() , PersistenceException.
                ExceptionType.ImplementationNotAvailable );
    }

    @Test
    void testWrongLocationOfFile() {
        try {
            PersistenceStrategyStream<UserStory> strat = new PersistenceStrategyStream<UserStory>();

            // FileStreams do not like directories, so try this out ;-)
            strat.setLOCATION("/Users/saschaalda/tmp");
            container.setPersistenceStrategy( strat );
            container.store();

        } catch (PersistenceException e) {
            System.out.println("Message: " + e.getMessage() );
            assertEquals("Fehler beim Speichern der Datei!", e.getMessage());
            assertEquals(  PersistenceException.ExceptionType.SaveFailure  ,
                    e.getExceptionTypeType() ) ;
        }
    }

    @Test
    void testStoreDeleteAndLoad() {
        try {
            // Löschen aller Objekte, damit das Singleton leer ist
            container.deleteAllUserStories();

            container.setPersistenceStrategy( new PersistenceStrategyStream<UserStory>() );
            container.addUserStory(new UserStory(1));

            assertEquals( 1 , container.size() );
            container.store();

            container.deleteUserStory(1);
            assertEquals(0 , container.size() );

            container.load();
            assertEquals( 1 , container.size() );

        } catch (PersistenceException | ContainerException e) {
            System.out.println("Message: " + e.getMessage() );
        }
    }

    @Test
    void testStoreManyTime() {
        try {
            container.setPersistenceStrategy( new PersistenceStrategyStream<UserStory>() );
            container.addUserStory(new UserStory(1));
            container.addUserStory(new UserStory(12) ); ;
            container.addUserStory(new UserStory(13) );

            assertEquals( 3 , container.size() );
            container.store(); // overwriting existing ones!

            container.load();
            assertEquals( 3 , container.size() );

        } catch (PersistenceException | ContainerException e) {
            System.out.println("Message: " + e.getMessage() );
        }
    }

    @Test
    void testLoadOneTime() {
        try {
            container.setPersistenceStrategy( new PersistenceStrategyStream<UserStory>() );
            container.load();
            System.out.println("Größe des Files: " + container.size() );

        } catch (PersistenceException  e) {
            System.out.println("Message: " + e.getMessage() );
        }
    }

}