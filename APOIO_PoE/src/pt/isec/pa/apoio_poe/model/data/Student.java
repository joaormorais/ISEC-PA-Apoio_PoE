package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Comparable<Student>, Cloneable, Serializable {
    private String name;
    private String email;
    private long number; // student number
    private String course; // course acronym
    private String branch; // branch acronym
    private double grade;
    private boolean internship; // possibility of accessing internships in addition to projects
    private Internship internshipAssociated; // in the state INTERNSHIP_ASSIGNMENT, the student will receive an internship
    //public static final String TEXT_GREEN = "\u001B[32m";
    //public static final String TEXT_RESET = "\u001B[0m";

    // Constructor

    public Student(String name, String email, long number, String course, String branch, double grade, boolean internship, Internship internshipAssociated) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.course = course;
        this.branch = branch;
        this.grade = grade;
        this.internship = internship;
        this.internshipAssociated = internshipAssociated;
    }

    // Clone method

    @Override
    protected Student clone() {
        //return new Student(name, email, number, course, branch, grade, internship, internshipAssociated);
        try {
            return (Student) super.clone();
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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean isInternship() {
        return internship;
    }

    public void setInternship(boolean internship) {
        this.internship = internship;
    }

    public Internship getInternshipAssociated() {
        return internshipAssociated;
    }

    public void setInternshipAssociated(Internship internshipAssociated) {
        this.internshipAssociated = internshipAssociated;
    }

    // ToString

    /*@Override
    public String toString() {
        return TEXT_GREEN + "Nome: " + TEXT_RESET + name +
                TEXT_GREEN + ", Email: " + TEXT_RESET + email +
                TEXT_GREEN + ", Número de aluno: " + TEXT_RESET + number +
                TEXT_GREEN + ", Sigla de curso: " + TEXT_RESET + course +
                TEXT_GREEN + ", Sigla do ramo: " + TEXT_RESET + branch +
                TEXT_GREEN + ", Classificação: " + TEXT_RESET + grade +
                TEXT_GREEN + ", Possibilidade de aceder a estágios além de projetos: " + TEXT_RESET + internship +
                TEXT_GREEN + ", Estágio/projeto associado: " + TEXT_RESET + internshipAssociated;
    }*/

    @Override
    public String toString() {
        return "Nome: " + name +
                ", \t\tEmail: " + email +
                ", \t\tNúmero de aluno: " + number +
                ", \t\tSigla de curso: " + course +
                ", \t\tSigla do ramo: " + branch +
                ", \t\tClassificação: " + grade +
                ", \t\tPossibilidade de aceder a estágios além de projetos: " + internship +
                ", \t\tEstágio/projeto associado: " + internshipAssociated;
    }

    // Equals + HashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return number == student.number || email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, number);
    }

    // Compare Method

    @Override
    public int compareTo(Student o) {
        return (int) (this.grade - o.grade);
    }

}
