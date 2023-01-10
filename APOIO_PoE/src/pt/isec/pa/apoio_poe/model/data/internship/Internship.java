package pt.isec.pa.apoio_poe.model.data.internship;

import pt.isec.pa.apoio_poe.model.data.Professor;

import java.io.Serializable;
import java.util.Objects;

public abstract class Internship implements Serializable {
    static int count = 1;
    private String ID;
    private String branchDestiny; //  (T1 + T2)
    private String title;
    private String company; // company that will receive the student (T1)
    private String professorResponsible; // professor responsible for this project (T2)
    private long studentNumber; // number of the student responsible for this internship
    private Professor associatedProfessor; // // in the state PROFESSOR_ASSIGNMENT, the internship will receive a professor
    private boolean associated; // if it's true, this internship is associated to a student

    // Constructor

    // used when we add by ourselves a new internship
    public Internship(String branchDestiny, String title, String company, String professorResponsible, long studentNumber, Professor associatedProfessor, boolean associated) {
        this.ID = "P" + count++;
        this.branchDestiny = branchDestiny;
        this.title = title;
        this.company = company;
        this.professorResponsible = professorResponsible;
        this.studentNumber = studentNumber;
        this.associatedProfessor = associatedProfessor;
        this.associated = associated;
    }

    // used when we add an internship using a .csv file
    public Internship(String ID, String branchDestiny, String title, String company, String professorResponsible, long studentNumber, Professor associatedProfessor, boolean associated) {
        this.ID = ID;
        this.branchDestiny = branchDestiny;
        this.title = title;
        this.company = company;
        this.professorResponsible = professorResponsible;
        this.studentNumber = studentNumber;
        this.associatedProfessor = associatedProfessor;
        this.associated = associated;
    }

    // Getter's and Setter's

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBranchDestiny() {
        return branchDestiny;
    }

    public void setBranchDestiny(String branchDestiny) {
        this.branchDestiny = branchDestiny;
    }

    public String getProfessorResponsible() {
        return professorResponsible;
    }

    public void setProfessorResponsible(String professorResponsible) {
        this.professorResponsible = professorResponsible;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Professor getAssociatedProfessor() {
        return associatedProfessor;
    }

    public void setAssociatedProfessor(Professor associatedProfessor) {
        this.associatedProfessor = associatedProfessor;
    }

    public boolean isAssociated() {
        return associated;
    }

    public void setAssociated(boolean associated) {
        this.associated = associated;
    }

    // ToString

    @Override
    public String toString() {
        return "ID: " + ID +
                ", \t\tRamo(s) de destino: " + branchDestiny +
                ", \t\tTítulo: " + title +
                ", \t\tEntidade de acolhimento: " + company +
                ", \t\tProfessor responsável: " + professorResponsible +
                ", \t\tNúmero de aluno: " + studentNumber +
                ", \t\tProfessor associado: " + associatedProfessor +
                ", \t\tEncontra-se associado a um estudante: " + associated;
    }

    // Equals + HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof T1) && !(o instanceof T2) && !(o instanceof T3)) return false;
        Internship that = (Internship) o;
        return ID.equals(that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

}
