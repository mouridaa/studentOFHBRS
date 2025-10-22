package org.hbrs.se1.ws25.exercises.uebung3.persistence.view;

import org.hbrs.se1.ws25.exercises.uebung2.Member;

import java.util.List;

public class MemberView {
    //Geht auch mit while Schleife, normale for-schleife, Iterator, for-each-Methode
    public void dump(List<Member> members) {
        for (Member m : members) {
            System.out.println(m) ;
        }
    }
}
