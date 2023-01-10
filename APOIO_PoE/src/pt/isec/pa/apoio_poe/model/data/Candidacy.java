package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Candidacy implements Cloneable,Serializable {
    private long studentNumber; // number of the student responsible for this candidacy
    private List<String> internshipId;

    // Constructor

    public Candidacy(long studentNumber, List<String> internshipId) {
        this.studentNumber = studentNumber;
        this.internshipId = internshipId;
    }

    // Clone method

    @Override
    protected Candidacy clone() {
        return new Candidacy(studentNumber, internshipId);
    }

    // Getter's and Setter's

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public List<String> getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(List<String> internshipId) {
        this.internshipId = internshipId;
    }

    // ToString

    @Override
    public String toString() {
        return "Número de estudante: " + studentNumber +
                ", \t\tEstágio/projeto: " + internshipId;
    }

    // Equals + HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidacy candidacy = (Candidacy) o;
        return studentNumber == candidacy.studentNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNumber);
    }
}
