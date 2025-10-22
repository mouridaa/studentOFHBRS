package org.hbrs.se1.ws25.exercises.uebung3.persistence;

import org.hbrs.se1.ws25.exercises.uebung2.Member;
import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    public ConcreteMember(int id) {

        this.id = id;
    }

    @Override
    public Integer getID() {

        return this.id;
    }

    @Override
    public String toString() {

        return "Member (ID = " + this.id + " )";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConcreteMember) {
            ConcreteMember other = (ConcreteMember) obj;
            return this.id == other.id;
        }
        return false;
    }




}
