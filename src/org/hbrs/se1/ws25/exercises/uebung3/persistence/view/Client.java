package org.hbrs.se1.ws25.exercises.uebung3.persistence.view;


import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.ConcreteMember;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.Container;

import java.util.List;

public class Client {

    public static void main(String[] args) {
        /* Antwort:
        * Hier wird das Strategy Pattern verwendet, ein Verhalten zu ändern, (z.B.
        * das Speichern und Laden) auszutauschen, ohne den Container selbst zu verändern
        *
        * */

        Container container = Container.getInstance();

        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);

        try {
            container.addMember(m1);
            container.addMember(m2);
            container.addMember(m3);
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }

        List<Member> members = container.getCurrentList();

        MemberView view = new MemberView();
        view.dump(members);

    }

}
