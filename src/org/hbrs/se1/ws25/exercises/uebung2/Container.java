package org.hbrs.se1.ws25.exercises.uebung2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Container {

    List<Member> memberList;

    public Container() {
        this.memberList = new LinkedList<>();
    }

    public void addMember(Member member) throws ContainerException{
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

    //Geht auch mit while Schleife, normale for-schleife, Iterator, for-each-Methode?
    public void dump() {
        for (Member m : memberList) {
            System.out.println(m) ;
        }
    }

    public int size() {
        return  memberList.size();
    }


}
