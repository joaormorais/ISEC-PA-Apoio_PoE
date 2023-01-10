package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Professor implements Comparable<Professor>, Cloneable, Serializable {
    private String name;
    private String email;
    private boolean associated; // if it's true, the professor is associated to an internship
    private int nAssociations;

    // Constructor

    public Professor(String name, String email, boolean associated) {
        this.name = name;
        this.email = email;
        this.associated = associated;
        this.nAssociations = 0;
    }

    // Clone method

    @Override
    protected Professor clone() {
        //return new Professor(name, email, associated);
        try {
            return (Professor) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // Getter's and Setter's

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAssociated() {
        return associated;
    }

    public void setAssociated(boolean associated) {
        this.associated = associated;
    }

    public int getnAssociations() {
        return nAssociations;
    }

    public void addnAssociations() {
        this.nAssociations++;
    }

    public void subnAssociations() {
        this.nAssociations--;
    }

    // ToString

    @Override
    public String toString() {
        return "Nome: " + name +
                ", \t\tEmail: " + email;
    }

    // Equals + HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // returns true if we are comparing the same object
        if (o == null || getClass() != o.getClass())
            return false; // returns false if it comes false or the object is from a different class
        Professor professor = (Professor) o;
        return email.equals(professor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    // Compare Method

    @Override
    public int compareTo(Professor o) {
        return (int) (this.nAssociations - o.nAssociations);
    }

}
