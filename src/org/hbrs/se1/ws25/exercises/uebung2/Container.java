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
        if (member != null) {
            memberList.add(member);
        }
    }

    //Nachteil dieser Art von Fehlerhandling ist, dass
    public String deleteMember(Integer id) {
        for (Member m : memberList) {
            if (m.getID() == id) {
                memberList.remove(m);
                return "Member wurde entfernt";
            }
        }
        return "Zur übergebenen ID ist kein Member-Objekt gespeichert";
    }

    public void dump() {
        for (Member m : memberList) {
            System.out.println(m) ;
        }
    }

    public int size() {
        return  memberList.size();
    }


}
