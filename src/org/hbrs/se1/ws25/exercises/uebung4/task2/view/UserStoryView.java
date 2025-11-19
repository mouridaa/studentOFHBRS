package org.hbrs.se1.ws25.exercises.uebung4.task2.view;

import org.hbrs.se1.ws25.exercises.uebung4.task2.model.UserStory;

import java.util.List;
import java.util.Collections;

public class UserStoryView {

    public void dump (List<UserStory> userStoryList) {
        System.out.println("UserStory-Tool V1.0 by Mourida K.");
        System.out.println("Ausgabe aller User-Stories: ");
        //UserStory implementiert Comparable und wird nach ihrer Priorität sortiert
        //Die UserStory mit der höchsten Priorität kommt zuerst (So in eigener compareTo Methode definiert)
        Collections.sort(userStoryList);
        //Tabellenkopf
        System.out.printf("| %-5s | %-20s | %-25s | %-15s | %-15s |%n",
                "ID", "TITEL", "AKZEPTANZKRITERIEN", "PRIORISIERUNG", "PROJEKT");
        System.out.println("-------------------------------------------------------------------------------");

        // Liste von Objekten durchlaufen
        for (UserStory us : userStoryList) {
            System.out.printf("| %-5d | %-20s | %-25s | %-15s | %-15s |%n",
                    us.getID(),
                    us.getTitel(),
                    us.getAkzeptanzkriterien(),  // hier ggf. alle Kriterien zusammenfassen
                    us.getPrio(),
                    us.getProject());
        }
    }

    public void dump (List<UserStory> userStoryList, String projekt) {
        System.out.println("UserStory-Tool V1.0 by Mourida K.");
        System.out.println("Ausgabe aller User-Stories vom Projekt " + projekt + ": ");
        //UserStory implementiert Comparable und wird nach ihrer Priorität sortiert
        //Die UserStory mit der höchsten Priorität kommt zuerst (So in eigener compareTo Methode definiert)
        Collections.sort(userStoryList);
        // Tabellenkopf
        System.out.printf("| %-5s | %-20s | %-25s | %-15s | %-15s |%n",
                "ID", "TITEL", "AKZEPTANZKRITERIEN", "PRIORISIERUNG", "PROJEKT");
        System.out.println("-------------------------------------------------------------------------------");

        // Liste von Objekten durchlaufen und formatierte Zeilen ausgeben
        //Design Pattern: Filter Map Reduce
        userStoryList.stream()
                .filter(userStory -> userStory.getProject().equals(projekt))
                .forEach(us -> {
                    // Akzeptanzkriterien als String zusammenfassen, hier mit Komma
                    String akzept = String.join(", ", us.getAkzeptanzkriterien());

                    System.out.printf("| %-5d | %-20s | %-25s | %-15.2f | %-15s |%n",
                            us.getID(),
                            us.getTitel(),
                            akzept,
                            us.getPrio(),
                            us.getProject());
                });
        }

    public void dump (List<UserStory> userStoryList, double priorität) {
        System.out.println("UserStory-Tool V1.0 by Mourida K.");
        System.out.println("Ausgabe aller User-Stories mit einer Prio groesser als " + priorität + ": ");
        //UserStory implementiert Comparable und wird nach ihrer Priorität sortiert
        //Die UserStory mit der höchsten Priorität kommt zuerst (So in eigener compareTo Methode definiert)
        Collections.sort(userStoryList);
        // Tabellenkopf
        System.out.printf("| %-5s | %-20s | %-25s | %-15s | %-15s |%n",
                "ID", "TITEL", "AKZEPTANZKRITERIEN", "PRIORISIERUNG", "PROJEKT");
        System.out.println("-------------------------------------------------------------------------------");

        // Liste von Objekten durchlaufen und formatierte Zeilen ausgeben
        //Design Pattern: Filter Map Reduce
        userStoryList.stream()
                .filter(userStory -> userStory.getPrio() >= priorität)
                .forEach(us -> {
                    // Akzeptanzkriterien als String zusammenfassen, hier mit Komma
                    String akzept = String.join(", ", us.getAkzeptanzkriterien());

                    System.out.printf("| %-5d | %-20s | %-25s | %-15.2f | %-15s |%n",
                            us.getID(),
                            us.getTitel(),
                            akzept,
                            us.getPrio(),
                            us.getProject());
                });

//        //List<> ... = list.stream()
//        .filter
//
//                .collect(Collectors.toList)
    }

}
