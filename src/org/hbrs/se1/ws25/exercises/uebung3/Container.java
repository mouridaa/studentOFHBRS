package org.hbrs.se1.ws25.exercises.uebung3;

import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private static Container instance;
    private List<Member> memberList;
    private PersistenceStrategy<Member> persistenceStrategy;

    private Container() {
        this.memberList = new ArrayList<>();
        this.persistenceStrategy = new PersistenceStrategyStream<>();
    }

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public List<Member> getCurrentList() {
        return memberList;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void addMember(Member member) throws ContainerException {
        if (memberList.contains(member)) {
            throw new ContainerException("Das Member-Objekt mti der ID " + member.getID() +
                    " ist bereits vorhanden!");
        }
        if (member == null) {
            throw new ContainerException("Das Member-Objekt nicht vorhanden!");
        }
        memberList.add(member);
    }

    //Nachteil dieser Art von Fehlerhandling ist, dass der Aufrufer aktiv prüfen muss, ob der Rückgabe-
    //wert einen Fehler signalisiert.
    public String deleteMember(Integer id) {
        for (Member m : memberList) {
            if (m.getID().equals(id)) {
                memberList.remove(m);
                return "Member wurde entfernt";
            }
        }
        return "Zur übergebenen ID ist kein Member-Objekt gespeichert";
    }

    public void deleteAllMembers() {
        memberList.clear();
    }

    public void store() throws PersistenceException {
        if(this.persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine " +
                    "Strategie gesetzt");
        }
        persistenceStrategy.save(memberList);
    }

    public void load() throws PersistenceException {
        if(this.persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine " +
                    "Strategie gesetzt");
        }
        persistenceStrategy.load();
    }



    public int size() {
        return  memberList.size();
    }


}
