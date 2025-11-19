package org.hbrs.se1.ws25.exercises.uebung4.task2.model;

import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class Container {

    //Interne ArrayList um Objekte vom Typ UserStory abspeichern zu können
    private List<UserStory> userStoryList = null;

    //URL der Datei, in der die Objekte gespeichert werden sollen
    final static String LOCATION = "allStories.ser";

    //Referenz zur internen Persistenzstrategie
    private PersistenceStrategy<UserStory> persistenceStrategy;

    /*Statische Klassen-Variable, um die Referenz auf das Container
Objekt abspeichern zu können*/
    private static Container instance = null;

    //Private Containerklasse um Erzeugungen ausserhalb der Klasse zu vermeiden
    private Container() {
        userStoryList = new ArrayList<UserStory>();
    }

    //Design Pattern: Singleton-Pattern
    //Diese Variante ist thread-safe und belegt den Speicher nur bei Bedarf
    //Liefert ein Singelton zurück
    public static synchronized Container getInstance() {
        if (instance == null) {
            instance = new Container();
            System.out.println("Es wurde bereits ein Objekt vom Typ Container instanziert");
        }
        return instance;
    }

    //Methode um Persistenz-Strategie von aussen setzen zu können
    public void setPersistenceStrategy (PersistenceStrategy<UserStory> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    //Methode zum Liefern der Internen UserStory Liste
    public List<UserStory> getUserStoryList() {
        return this.userStoryList;
    }

    //Methode zum Hinzufügen von UserStories
    public void addUserStory(UserStory userStory) throws ContainerException {
        if (userStoryList.contains(userStory)) {
            System.out.println("Duplikat: " + userStory.toString());
            ContainerException ex = new ContainerException(ContainerException.ExceptionType.DuplicateUserStory);
            ex.addID(userStory.getID());
            throw ex;
        }
        userStoryList.add(userStory);
    }

    //Methode zum Löschen von UserStories mit einer bestimmten ID
    public String deleteUserStory(Integer id) {
        //getUserStory(id) wirft eine ContainerException die hier aufgefangen wird
        try {
            UserStory userStory = getUserStory(id);
            userStoryList.remove(userStory);
            return "User-Story mit der ID " + id + " konnte geloescht werden";
        } catch (ContainerException e) {
            e.printStackTrace();
            return "Zur übergebenen ID ist kein Member-Objekt gespeichert";
        }
    }

    public String deleteAllUserStories() {
       userStoryList.clear();
       return "Alle User-Stories wurden geloescht";
    }

    //Interne Methode um eine User-Story mit einer bestimmten ID zu ermitteln
    private UserStory getUserStory(Integer id) throws ContainerException {
        for (UserStory u :  userStoryList) {
            if (u.getID().equals(id)) {
                return u;
            }
        }
        ContainerException ex = new ContainerException(ContainerException.ExceptionType.NonExistentUserStory);
        ex.addID(id);
        throw ex;
    }

    //Methode um die Anzahl an UserStories in einem Container zu ermitteln
    public int size() {
        return userStoryList.size();
    }

    public void store() throws PersistenceException {
        if (this.persistenceStrategy == null)
            throw new PersistenceException(PersistenceException.
                    ExceptionType.NoStrategyIsSet,
                    "Strategy not initialized");

        try {
            this.persistenceStrategy.save(this.userStoryList);
        } catch ( UnsupportedOperationException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ImplementationNotAvailable
                    , "MongoDB is not implemented!" );
        }
    }

    public void load() throws PersistenceException {
        if (this.persistenceStrategy == null)
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Strategy not initialized");
        try {
            this.userStoryList = this.persistenceStrategy.load();
        } catch ( UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable
                    , "MongoDB is not implemented!" );
        }
    }

}





