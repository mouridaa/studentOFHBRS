package org.hbrs.se1.ws25.exercises.uebung4.task2.controller;

import org.hbrs.se1.ws25.exercises.uebung4.task2.model.Container;
import org.hbrs.se1.ws25.exercises.uebung4.task2.model.UserStory;
import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.PersistenceException;
import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.UserStoryException;
import org.hbrs.se1.ws25.exercises.uebung4.task2.view.UserStoryView;

import java.util.Scanner;

public class InputDialog {
    public void startEingabe() {

        //Abholung der Referenz der Referenz des Container-Objekts (Singeltion!)
        Container container = Container.getInstance();

        String strInput = null;

        //Initialisierung des Eingabe-View
        UserStoryView userStoryView = new UserStoryView();

        /*Der Scanner liest Tastatureingaben über System.in,
        zerlegt sie in Tokens und wandelt sie bei Bedarf in passende
        Datentypen um/
         */
        Scanner scanner = new Scanner(System.in);

        //Ausgabe des Programmtitels und der Versionsnummer
        System.out.println("UserStory-Tool V1.0 by Mourida K.");

        while (true) {

            System.out.print(">");
            //Liest die ganze Eingabe bis zum nächsten Zeilenumbruch
            strInput = scanner.nextLine();

            //Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");

            //Bei eingabe von "help": Anzeige aller möglichen Befehle
            if (strings[0].equals("help")) {
                System.out.println("Folgenden Befehle stehen zur Verfuegung: ");
                System.out.println("enter, store, load, dump, exit, help");

            } else if (strings[0].equals("enter")) {
                try {
                    // Nach eingabe von enter, wird eine User-Story Instanz erzeugt
                    UserStory userStory = new UserStory();

                    // Wenn nur enter eingegeben wird, werden alle Attribute abgefragt
                    if (strings.length == 1) {
                        System.out.println("Enter ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        userStory.setID(id);

                        System.out.println("Enter Titel: ");
                        String titel = scanner.nextLine();
                        userStory.setTitel(titel);

                        System.out.println("Enter Akzeptanzkriterium: ");
                        String akzept = scanner.nextLine();
                        userStory.addAkzeptanzkriterium(akzept);

                        System.out.println("Enter Mehrwert: ");
                        int mehrwert = Integer.parseInt(scanner.nextLine());
                        userStory.setMehrwert(mehrwert);

                        System.out.println("Enter Strafe: ");
                        int strafe = Integer.parseInt(scanner.nextLine());
                        userStory.setStrafe(strafe);

                        System.out.println("Enter Aufwand: ");
                        int aufwand = Integer.parseInt(scanner.nextLine());
                        userStory.setAufwand(aufwand);

                        System.out.println("Enter Risiko: ");
                        int risiko = Integer.parseInt(scanner.nextLine());
                        userStory.setRisiko(risiko);

                        System.out.println("Enter Projekt: ");
                        String projekt = scanner.nextLine();
                        userStory.setProject(projekt);
                    } else {
                        // Nur bestimmte Attribute abfragen
                        for (int i = 1; i < strings.length; i++) {
                            String param = strings[i];
                            switch (param.toLowerCase()) {
                                case "id":
                                    System.out.println("Enter ID: ");
                                    userStory.setID(Integer.parseInt(scanner.nextLine()));
                                    break;
                                case "titel":
                                    System.out.println("Enter Titel: ");
                                    userStory.setTitel(scanner.nextLine());
                                    break;
                                case "akzeptanzkriterium":
                                    System.out.println("Enter Akzeptanzkriterium: ");
                                    userStory.addAkzeptanzkriterium(scanner.nextLine());
                                    break;
                                case "mehrwert":
                                    System.out.println("Enter Mehrwert: ");
                                    userStory.setMehrwert(Integer.parseInt(scanner.nextLine()));
                                    break;
                                case "strafe":
                                    System.out.println("Enter Strafe: ");
                                    userStory.setStrafe(Integer.parseInt(scanner.nextLine()));
                                    break;
                                case "aufwand":
                                    System.out.println("Enter Aufwand: ");
                                    userStory.setAufwand(Integer.parseInt(scanner.nextLine()));
                                    break;
                                case "risiko":
                                    System.out.println("Enter Risiko: ");
                                    userStory.setRisiko(Integer.parseInt(scanner.nextLine()));
                                    break;
                                case "projekt":
                                    System.out.println("Enter Projekt: ");
                                    userStory.setProject(scanner.nextLine());
                                    break;
                                default:
                                    System.out.println("Unbekanntes Attribut: " + param);
                            }
                        }
                    }

                    // 3. Einmalig UserStory zum Container hinzufügen
                    container.addUserStory(userStory);

                } catch (NumberFormatException e) {
                    System.out.println("Bitte nur ganze Zahlen eingeben!");
                } catch (UserStoryException e) {
                    e.printStackTrace();
                } catch (ContainerException e) {
                    e.printStackTrace();
                }

            } else if (strings[0].equals("dump")) {
                // User-Stories eines bestimmten Projekts
                if (strings.length == 1) {
                    userStoryView.dump(container.getUserStoryList());
                } else {
                    if (strings.length == 3 && strings[1].equals("projekt")) {
                        String projekt = strings[2];
                        userStoryView.dump(container.getUserStoryList(), projekt);
                    } else {
                        // keine weiteren Parameter → alle ausgeben
                        userStoryView.dump(container.getUserStoryList());
                    }
                }

            } else if (strings[0].equals("store")) {
                try {
                    container.store();
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }

            } else if (strings[0].equals("load")) {
                try {
                    container.load();
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }

            } else if (strings[0].equals("exit")) {
                return;

            } else if(strings[0].equals("delete")) {
                System.out.println(container.deleteAllUserStories());
            }

            else {
                System.out.println("Diese Eingabe ist ungültig!");
                System.out.println("Mögliche Eingaben: ");
                System.out.println("enter, store, load, dump, exit, help");
            }

        }

    }
}