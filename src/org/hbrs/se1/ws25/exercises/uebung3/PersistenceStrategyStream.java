package org.hbrs.se1.ws25.exercises.uebung3;

import org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceException;

import java.io.*;
import java.util.List;
import java.io.FileOutputStream; //Schreibt Bytes in eine Datei
import java.io.IOException;
import java.io.ObjectOutputStream; //Objekt in Bytes umwandeln

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help! Good source:
     * https://www.digitalocean.com/community/tutorials/objectoutputstream-java-write-object-file
     * (Last Access: Oct, 13th 2025)
     */
    public void save(List<E> member) throws PersistenceException {
        try {
            FileOutputStream fos = new FileOutputStream(location);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(member);
            oos.close();
        } catch (FileNotFoundException ffe) { // Fehler der auftritt, wenn mit dem Zugriff auf externe Ressourcen etwas schief läuft
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Die Location darf kein Verzeichnis sein: " +
                    location);
        } catch (IOException ioe) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, ioe.getMessage());
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        try{
            FileInputStream fis = new FileInputStream(location);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<E> member = null;
            Object obj = ois.readObject();
            if(obj instanceof List<?>) { // Prüfen, ob das Objekt eine Liste ist
                member = (List<E>) obj;
            }
            ois.close();
            return member;
        } catch(IOException ioe){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, ioe.getMessage());
        } catch(ClassNotFoundException cnfe) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, cnfe.getMessage());
        }
    }
}
