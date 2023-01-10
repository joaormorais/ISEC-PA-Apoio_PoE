package pt.isec.pa.apoio_poe.model.data.internship;

import pt.isec.pa.apoio_poe.model.data.Professor;

import java.io.Serializable;

public class T1 extends Internship implements Serializable {

    // Constructor

    // used when we add by ourselves a new internship
    public T1(String branchDestiny, String title, String company, long studentNumber, Professor associatedProfessor, boolean associated) {
        super(branchDestiny, title, company, null, studentNumber, associatedProfessor, associated);
    }

    // used when we add an internship using a .csv file
    public T1(String ID, String branchDestiny, String title, String company, long studentNumber, Professor associatedProfessor, boolean associated) {
        super(ID, branchDestiny, title, company, null, studentNumber, associatedProfessor, associated);
    }

    // Methods

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public void setID(String ID) {
        super.setID(ID);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public String getCompany() {
        return super.getCompany();
    }

    @Override
    public void setCompany(String company) {
        super.setCompany(company);
    }

    @Override
    public String getBranchDestiny() {
        return super.getBranchDestiny();
    }

    @Override
    public void setBranchDestiny(String branchDestiny) {
        super.setBranchDestiny(branchDestiny);
    }

    @Override
    public String getProfessorResponsible() {
        return super.getProfessorResponsible();
    }

    @Override
    public void setProfessorResponsible(String professorResponsible) {
        super.setProfessorResponsible(professorResponsible);
    }

    @Override
    public long getStudentNumber() {
        return super.getStudentNumber();
    }

    @Override
    public void setStudentNumber(long studentNumber) {
        super.setStudentNumber(studentNumber);
    }

}
