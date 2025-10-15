package org.hbrs.se1.ws25.exercises.uebung2;

public class ConcreteMember implements Member {

    int id;

    public ConcreteMember(int id) {
            this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    public String toString() {
        return "Member (ID = " + this.id + " )";
    }

    public boolean equals(Object obj) {
        if (obj instanceof ConcreteMember) {
            ConcreteMember other = (ConcreteMember) obj;
            return this.id == other.id;
        }
        return false;
    }




}
