package org.hbrs.se1.ws25.exercises.uebung4.task2.controller;

import org.hbrs.se1.ws25.exercises.uebung4.task2.model.Container;
import org.hbrs.se1.ws25.exercises.uebung4.task2.model.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args) {
        // Referenz auf das Container-Objekt holen
        Container container = Container.getInstance();

        // Strategie für Stream-Strategy erzeugen und in den Container einsetzen
        container.setPersistenceStrategy( new PersistenceStrategyStream() );

        // InputDialog zur Ein- und Ausgabe starten
        InputDialog dialog = new InputDialog();
        dialog.startEingabe();


//        UserStory u1 = new UserStory(1);
//
//        UserStoryView userStoryView = new UserStoryView();
//        try {
//            container.addUserStory(new UserStory(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//        userStoryView.dump(container.getUserStoryList());
//        //prüfen ob die contains methode funktioniert
//        System.out.println(container.getUserStoryList().contains(u1));
//
//
//        UserStory u2 = new UserStory(1);
//
//        try {
//            container.addUserStory(u2);
//            //prüfen, ob jetzt aufgrund der gleichen ID eine Exception geworfen wird
//        } catch (ContainerException e) {
//            e.printStackTrace();
//        }
//
//        userStoryView.dump(container.getUserStoryList());
//
//
//        try {
//            container.store();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//        try {
//            container.load();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //prüfen ob nach dem Laden der Objekte die contains methode immer noch funktioniert
//
//        System.out.println(container.getUserStoryList().contains(u1));
//        System.out.println(container.getUserStoryList().getFirst());
//        System.out.println(container.getUserStoryList().getFirst().equals(u1));
//
//
//        userStoryView.dump(container.getUserStoryList());
//
//
//        UserStory u3 = new UserStory(3);
//        try {
//            container.addUserStory(u3);
//        } catch (ContainerException e) {
//            e.printStackTrace();
//        }
//
//        userStoryView.dump(container.getUserStoryList());
//
//        try {
//            container.addUserStory(new UserStory(2));
//            container.addUserStory(new UserStory(4));
//        } catch (ContainerException e) {
//            e.printStackTrace();
//        }
//
//        userStoryView.dump(container.getUserStoryList());
//
//        //prüfen ob UserStory nach ID gelöscht werden kann
//        container.deleteUserStory(2);
//
//        userStoryView.dump(container.getUserStoryList());
//
//        //prüfen ob Strafe/Risiko/Mehrwert zwischen 1 und 5 liegen
//        try {
//            u1.setStrafe(6);
//        } catch (UserStoryException e) {
//            e.printStackTrace();
//        }
//
//        //prüfen ob Aufwand groesser als Null ist
//        try {
//            u1.setAufwand(0);
//        } catch (UserStoryException e) {
//            e.printStackTrace();
//        }


    }
}
