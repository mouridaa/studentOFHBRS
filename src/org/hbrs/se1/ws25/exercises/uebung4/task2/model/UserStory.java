package org.hbrs.se1.ws25.exercises.uebung4.task2.model;

import org.hbrs.se1.ws25.exercises.uebung4.task2.controller.exception.UserStoryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserStory implements Serializable, Comparable<UserStory> {

    private Integer id = 0;
    private String titel;
    private List<String> akzeptanzkriterien = new ArrayList<>();;
    private int mehrwert;
    private int strafe;
    private int aufwand;
    private int risiko;
    private String projekt;


    public UserStory () {
    }


    public UserStory (int id) {
        this.id = id;
    }

    public UserStory(int id, String titel, String akzeptanzkriterium, int mehrwert, int strafe, int aufwand, int risiko, String projekt) {
        this.id = id;
        this.titel = titel;
        this.akzeptanzkriterien.add(akzeptanzkriterium);
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risiko = risiko;
        this.projekt = projekt;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public void addAkzeptanzkriterium(String akzeptanzkriterien) {
        this.akzeptanzkriterien.add(akzeptanzkriterien);
    }

    public List<String> getAkzeptanzkriterien() {
        return akzeptanzkriterien;
    }

    public void setMehrwert(int mehrwert) throws UserStoryException {
        if (mehrwert < 1 || mehrwert > 5) {
            throw new UserStoryException("Mehrwert muss zwischen 1 und 5 liegen");
        }
        this.mehrwert = mehrwert;
    }

    public int getMehrwert() {
        return mehrwert;
    }

    public void setStrafe(int strafe) throws UserStoryException {
        if (mehrwert < 1 || mehrwert > 5) {
            throw new UserStoryException("Strafe muss zwischen 1 und 5 liegen");
        }
        this.strafe = strafe;
    }

    public int getStrafe() {
        return strafe;
    }

    public void setAufwand(int aufwand) throws UserStoryException {
        if (aufwand < 1) {
            throw new UserStoryException("Aufwand muss groesser als null sein");
        }
        this.aufwand = aufwand;
    }

    public int getAufwand() {
        return aufwand;
    }

    public void setRisiko(int risiko) throws UserStoryException {
        if (risiko < 1 || risiko > 5) {
            throw new UserStoryException("Risiko muss zwischen 1 und 5 liegen");
        }
        this.risiko = risiko;
    }

    public int getRisiko() {
        return risiko;
    }

    public double getPrio() {
        double prio = (mehrwert + strafe) / (double)(aufwand + risiko);
        return Math.round(prio * 100.0) / 100.0; // auf 2 Nachkommastellen runden
    }

    public void setProject(String project) {
        this.projekt = project;
    }

    public String getProject() {
        return projekt;
    }

    @Override
    public String toString() {
        String result = "UserStory [id = " + id + "]\n";
        result += "Titel: " + titel + "\n";
        result += "Akzeptanzkriterien:\n";

        for (String kriterien : akzeptanzkriterien) {
            result += " - " + kriterien + "\n";
        }

        result += "Priorisierung: " + getPrio() + "\n";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserStory) {
            UserStory other = (UserStory) obj;
            return this.id.equals(other.id);
        }
        return false;
    }


    /*Methode des Interface Comparable wir überschrieben, um UserStories
    * absteigend zu sortieren: Höchste Priorität kommt zuerst */
    @Override
    public int compareTo(UserStory other) {
        return Double.compare(other.getPrio(), this.getPrio());
    }
}

