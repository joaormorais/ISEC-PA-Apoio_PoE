package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.data.internship.T1;
import pt.isec.pa.apoio_poe.model.data.internship.T2;
import pt.isec.pa.apoio_poe.model.data.internship.T3;
import pt.isec.pa.apoio_poe.model.memento.IMemento;
import pt.isec.pa.apoio_poe.model.memento.IOriginator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PoEManagementData implements Serializable, IOriginator {
    // data
    private List<Student> students;
    private List<Professor> professors;
    private List<Internship> internships;
    private List<Candidacy> candidacies;
    // flags
    private boolean flag;
    private boolean flagClosedConfiguration;
    private boolean flagClosedCandidacyConfiguration;
    private boolean flagClosedInternshipAssignment;
    // aux data
    List<String> ramoDA = new ArrayList<>(); // every possibility where DA exists
    List<String> ramoSI = new ArrayList<>(); // every possibility where SI exists
    List<String> ramoRAS = new ArrayList<>(); // every possibility where RAS exists
    List<String> everyBranch = new ArrayList<>(); // every branch
    List<String> everyCourse = new ArrayList<>(); // every course


    // Constructor

    public PoEManagementData() {
        this.students = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.internships = new ArrayList<>();
        this.candidacies = new ArrayList<>();
        this.flag = false;
        this.flagClosedConfiguration = false;
        this.flagClosedCandidacyConfiguration = false;
        this.flagClosedInternshipAssignment = false;

        this.ramoDA.add("DA");
        this.ramoDA.add("DA|SI");
        this.ramoDA.add("SI|DA");
        this.ramoDA.add("DA|RAS");
        this.ramoDA.add("RAS|DA");
        this.ramoDA.add("DA|SI|RAS");
        this.ramoDA.add("DA|RAS|SI");
        this.ramoDA.add("SI|DA|RAS");
        this.ramoDA.add("SI|RAS|DA");
        this.ramoDA.add("RAS|DA|SI");
        this.ramoDA.add("RAS|SI|DA");

        this.ramoSI.add("SI");
        this.ramoSI.add("DA|SI");
        this.ramoSI.add("SI|DA");
        this.ramoSI.add("SI|RAS");
        this.ramoSI.add("RAS|SI");
        this.ramoSI.add("DA|SI|RAS");
        this.ramoSI.add("DA|RAS|SI");
        this.ramoSI.add("SI|DA|RAS");
        this.ramoSI.add("SI|RAS|DA");
        this.ramoSI.add("RAS|DA|SI");
        this.ramoSI.add("RAS|SI|DA");

        this.ramoRAS.add("RAS");
        this.ramoRAS.add("DA|RAS");
        this.ramoRAS.add("RAS|DA");
        this.ramoRAS.add("SI|RAS");
        this.ramoRAS.add("RAS|SI");
        this.ramoRAS.add("DA|SI|RAS");
        this.ramoRAS.add("DA|RAS|SI");
        this.ramoRAS.add("SI|DA|RAS");
        this.ramoRAS.add("SI|RAS|DA");
        this.ramoRAS.add("RAS|DA|SI");
        this.ramoRAS.add("RAS|SI|DA");

        this.everyBranch.add("DA");
        this.everyBranch.add("SI");
        this.everyBranch.add("RAS");
        this.everyBranch.add("DA|SI");
        this.everyBranch.add("SI|DA");
        this.everyBranch.add("DA|RAS");
        this.everyBranch.add("RAS|DA");
        this.everyBranch.add("SI|RAS");
        this.everyBranch.add("RAS|SI");
        this.everyBranch.add("DA|SI|RAS");
        this.everyBranch.add("DA|RAS|SI");
        this.everyBranch.add("SI|DA|RAS");
        this.everyBranch.add("SI|RAS|DA");
        this.everyBranch.add("RAS|DA|SI");
        this.everyBranch.add("RAS|SI|DA");

        this.everyCourse.add("LEI");
        this.everyCourse.add("LEI-PL");
        this.everyCourse.add("LEI-CE");

    }

    // Getter's and Setter's (flags)

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlagClosedConfiguration() {
        return flagClosedConfiguration;
    }

    public void setFlagClosedConfiguration(boolean flagClosedConfiguration) {
        this.flagClosedConfiguration = flagClosedConfiguration;
    }

    public boolean isFlagClosedCandidacyConfiguration() {
        return flagClosedCandidacyConfiguration;
    }

    public void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration) {
        this.flagClosedCandidacyConfiguration = flagClosedCandidacyConfiguration;
    }

    public boolean isFlagClosedInternshipAssignment() {
        return flagClosedInternshipAssignment;
    }

    public void setFlagClosedInternshipAssignment(boolean flagClosedInternshipAssignment) {
        this.flagClosedInternshipAssignment = flagClosedInternshipAssignment;
    }


    // Methods
    //--------------------------------------------------------------

    //      ConfigurationState (start)

    public boolean closeConfiguration() {

        // every possibility where DA exists
        /*List<String> ramoDA = new ArrayList<>();
        ramoDA.add("DA");
        ramoDA.add("DA|SI");
        ramoDA.add("SI|DA");
        ramoDA.add("DA|RAS");
        ramoDA.add("RAS|DA");
        ramoDA.add("DA|SI|RAS");
        ramoDA.add("DA|RAS|SI");
        ramoDA.add("SI|DA|RAS");
        ramoDA.add("SI|RAS|DA");
        ramoDA.add("RAS|DA|SI");
        ramoDA.add("RAS|SI|DA");*/

        // every possibility where SI exists
        /*List<String> ramoSI = new ArrayList<>();
        ramoSI.add("SI");
        ramoSI.add("DA|SI");
        ramoSI.add("SI|DA");
        ramoSI.add("SI|RAS");
        ramoSI.add("RAS|SI");
        ramoSI.add("DA|SI|RAS");
        ramoSI.add("DA|RAS|SI");
        ramoSI.add("SI|DA|RAS");
        ramoSI.add("SI|RAS|DA");
        ramoSI.add("RAS|DA|SI");
        ramoSI.add("RAS|SI|DA");*/

        // every possibility where RAS exists
        /*List<String> ramoRAS = new ArrayList<>();
        ramoRAS.add("RAS");
        ramoRAS.add("DA|RAS");
        ramoRAS.add("RAS|DA");
        ramoRAS.add("SI|RAS");
        ramoRAS.add("RAS|SI");
        ramoRAS.add("DA|SI|RAS");
        ramoRAS.add("DA|RAS|SI");
        ramoRAS.add("SI|DA|RAS");
        ramoRAS.add("SI|RAS|DA");
        ramoRAS.add("RAS|DA|SI");
        ramoRAS.add("RAS|SI|DA");*/


        int DA = 0, SI = 0, RAS = 0;

        for (Internship i : internships) {
            if (ramoDA.contains(i.getBranchDestiny()))
                DA++;

            if (ramoSI.contains(i.getBranchDestiny()))
                SI++;

            if (ramoRAS.contains(i.getBranchDestiny()))
                RAS++;

            if (i.getBranchDestiny() == null) { // we assume that if it's null, it's open for any branch of the course
                DA++;
                SI++;
                RAS++;
            }
        }

        int studentsSizeDA = 0, studentsSizeSI = 0, studentsSizeRAS = 0;

        for (Student i : students) {
            if (ramoDA.contains(i.getBranch()))
                studentsSizeDA++;

            if (ramoSI.contains(i.getBranch()))
                studentsSizeSI++;

            if (ramoRAS.contains(i.getBranch()))
                studentsSizeRAS++;
        }


        return DA >= studentsSizeDA && SI >= studentsSizeSI && RAS >= studentsSizeRAS && students.size() != 0;

    }

    //      ConfigurationState (end)

    //      ManageStudents (start)

    public int addStudent(Student s) {

        if (students.size() > 0) {

            // we can't add a student that already exists
            for (Student i : students)
                if (i.equals(s))
                    return -1;
        }

        // verify if the branch exists
        /*List<String> everyBranch = new ArrayList<>();
        everyBranch.add("DA");
        everyBranch.add("SI");
        everyBranch.add("RAS");
        everyBranch.add("DA|SI");
        everyBranch.add("SI|DA");
        everyBranch.add("DA|RAS");
        everyBranch.add("RAS|DA");
        everyBranch.add("SI|RAS");
        everyBranch.add("RAS|SI");
        everyBranch.add("DA|SI|RAS");
        everyBranch.add("DA|RAS|SI");
        everyBranch.add("SI|DA|RAS");
        everyBranch.add("SI|RAS|DA");
        everyBranch.add("RAS|DA|SI");
        everyBranch.add("RAS|SI|DA");*/

        if (!everyBranch.contains(s.getBranch()))
            return -2;

        // verify if the course exists
        /*List<String> everyCourse = new ArrayList<>();
        everyCourse.add("LEI");
        everyCourse.add("LEI-PL");
        everyCourse.add("LEI-CE");*/

        if (!everyCourse.contains(s.getCourse()))
            return -3;

        // verify if the grade is between 0 ans 1
        if (s.getGrade() < 0 || s.getGrade() > 1)
            return -4;

        if (String.valueOf(s.getNumber()).length() != 10)
            return -5;

        // we want a number that starts like 20... (ex: 2004..., 2020...) (not ex: 1234...)
        if (Long.toString(s.getNumber()).charAt(0) != '2' && Long.toString(s.getNumber()).charAt(1) != '0')
            return -5;

        students.add(s);
        return 1;

    }

    public boolean exportStudents(String filename) {

        if (students.size() == 0)
            return false;

        PrintWriter pw = null;
        File file;
        Scanner fileReader = null;
        List<String> returnValue = new ArrayList<>();
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");
        ;

        try {
            //pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\joaom\\Desktop\\João-Morais-2019134282\\APOIO_PoE\\students2.csv")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Student i : students)
                pw.println(+i.getNumber() + "," + i.getName() + "," + i.getEmail() + "," + i.getCourse() + "," + i.getBranch() + "," + i.getGrade() + "," + i.isInternship());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }

        return true;

    }

    public List<Student> getAllStudentsClone() {

        return students.stream().map(Student::clone).toList();

    }

    //      ManageStudents (end)

    //      ManageProfessors (start)

    public boolean addProfessor(Professor p) {

        if (professors.size() > 0) {

            // we can't add a professor that already exists
            for (Professor i : professors)
                if (i.equals(p))
                    return false;
        }

        professors.add(p);
        return true;

    }

    public boolean exportProfessors(String filename) {

        if (professors.size() == 0) {
            return false;
        }

        PrintWriter pw = null;
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\joaom\\Desktop\\João-Morais-2019134282\\APOIO_PoE\\professors2.csv")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Professor i : professors)
                pw.println(i.getName() + "," + i.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }

        return true;

    }

    public List<Professor> getAllProfessorsClone() {

        return professors.stream().map(Professor::clone).toList();

    }

    //      ManageProfessors (end)

    //      ManageInternship (start)

    public int addInternship(Internship it) {

        if (internships.size() > 0) {

            // we can't add an internship that already exists (studentNumber || ID)
            for (Internship j : internships)
                if (it.equals(j) || (j.getStudentNumber() == it.getStudentNumber() && j.getStudentNumber() != 0)) // o equals não funciona para o ID??????
                    return -1;

        }

        boolean foundStudent = false;

        if (it.getStudentNumber() != 0)
            for (Student i : students)
                if (i.getNumber() == it.getStudentNumber()) {
                    foundStudent = true;
                    break;
                }

        if (!foundStudent && it.getStudentNumber() != 0)
            return -2;


        boolean foundProfessor = false;

        if (it.getProfessorResponsible() != null)
            for (Professor i : professors)
                if (i.getEmail().equals(it.getProfessorResponsible())) {
                    foundProfessor = true;
                    break;
                }

        if (!foundProfessor && it.getProfessorResponsible() != null)
            return -3;

        // verify if the branch exists
        /*List<String> everyBranch = new ArrayList<>();
        everyBranch.add("DA");
        everyBranch.add("SI");
        everyBranch.add("RAS");
        everyBranch.add("DA|SI");
        everyBranch.add("SI|DA");
        everyBranch.add("DA|RAS");
        everyBranch.add("RAS|DA");
        everyBranch.add("SI|RAS");
        everyBranch.add("RAS|SI");
        everyBranch.add("DA|SI|RAS");
        everyBranch.add("DA|RAS|SI");
        everyBranch.add("SI|DA|RAS");
        everyBranch.add("SI|RAS|DA");
        everyBranch.add("RAS|DA|SI");
        everyBranch.add("RAS|SI|DA");*/

        if (it.getBranchDestiny() != null && !everyBranch.contains(it.getBranchDestiny()))
            return -4;

        if (it.getStudentNumber() != 0) {

            if (String.valueOf(it.getStudentNumber()).length() != 10)
                return -5;

            // we want a number that starts like 20... (ex: 2004..., 2020...) (not ex: 1234...)
            if (Long.toString(it.getStudentNumber()).charAt(0) != '2' && Long.toString(it.getStudentNumber()).charAt(1) != '0')
                return -5;

        }

        internships.add(it);
        return 1;

    }

    public boolean exportInternships(String filename) {

        if (internships.size() == 0) {
            return false;
        }

        PrintWriter pw = null;
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\joaom\\Desktop\\João-Morais-2019134282\\APOIO_PoE\\internships2.csv")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Internship i : internships)
                if (i instanceof T1)
                    pw.println("T1," + i.getID() + "," + i.getBranchDestiny() + "," + i.getTitle() + "," + i.getCompany() + "," + i.getProfessorResponsible() + "," + i.getStudentNumber());
                else if (i instanceof T2)
                    pw.println("T2," + i.getID() + "," + i.getBranchDestiny() + "," + i.getTitle() + "," + i.getCompany() + "," + i.getProfessorResponsible() + "," + i.getStudentNumber());
                else if (i instanceof T3)
                    pw.println("T3," + i.getID() + "," + i.getBranchDestiny() + "," + i.getTitle() + "," + i.getCompany() + "," + i.getProfessorResponsible() + "," + i.getStudentNumber());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }

        return true;

    }

    public List<Internship> getAllInternshipsClone() {

        return Collections.unmodifiableList(internships);

    }

    //      ManageInternship (end)

    //      ManageCandidacy (start)

    public int addCandidacy(Candidacy c) {

        if (candidacies.size() > 0) {

            // we can't add a candidacy that already exists
            for (Candidacy i : candidacies)
                if (i.equals(c))
                    return -1;

        }

        if (c.getStudentNumber() == 0)
            return -2;

        boolean foundStudent = false;

        for (Student i : students)
            if (i.getNumber() == c.getStudentNumber()) {
                foundStudent = true;
                break;
            }

        if (!foundStudent && c.getStudentNumber() != 0)
            return -2;

        if (String.valueOf(c.getStudentNumber()).length() != 10)
            return -3;

        // we want a number that starts like 20... (ex: 2004..., 2020...) (not ex: 1234...)
        if (Long.toString(c.getStudentNumber()).charAt(0) != '2' && Long.toString(c.getStudentNumber()).charAt(1) != '0')
            return -3;

        for (Internship i : internships)
            if (i.getStudentNumber() == c.getStudentNumber())
                return -4;

        for (Internship i : internships)
            if (c.getInternshipId().contains(i.getID()))
                if (i.getStudentNumber() != 0)
                    return -5;

        candidacies.add(c);
        return 1;

    }

    public boolean exportCandidacies(String filename) {

        if (candidacies.size() == 0)
            return false;

        PrintWriter pw = null;
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\joaom\\Desktop\\João-Morais-2019134282\\APOIO_PoE\\candidacies2.csv")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Candidacy i : candidacies)
                pw.println(i.getStudentNumber() + "," + i.getInternshipId());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }

        return true;

    }

    public List<Student> getStudentsWithAutoProposedInternship() {

        List<Student> aux = new ArrayList<>();
        List<Long> studentNumbers = new ArrayList<>();

        for (Internship i : internships)
            if (i instanceof T3)
                if (i.getStudentNumber() != 0)
                    studentNumbers.add(i.getStudentNumber());

        for (Student i : getAllStudentsClone()) // we do not want a reference to the original object
            if (studentNumbers.contains(i.getNumber()))
                aux.add(i);

        return aux;

    }

    public List<Student> getStudentsWithCandidacy() {

        List<Student> aux = new ArrayList<>();
        List<Long> studentNumbers = new ArrayList<>();

        for (Candidacy i : candidacies)
            studentNumbers.add(i.getStudentNumber());

        for (Student i : getAllStudentsClone()) // we do not want a reference to the original object
            if (studentNumbers.contains(i.getNumber()))
                aux.add(i);

        return aux;

    }

    public List<Student> getStudentsWithoutCandidacy() {

        List<Student> aux = new ArrayList<>();
        List<Long> studentNumbers = new ArrayList<>();

        for (Candidacy i : candidacies)
            studentNumbers.add(i.getStudentNumber());

        for (Student i : getAllStudentsClone()) // we do not want a reference to the original object
            if (!studentNumbers.contains(i.getNumber()))
                aux.add(i);

        return aux;

    }

    public List<Internship> getInternshipsWithFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {

        List<Internship> aux = new ArrayList<>();

        if (!filter1 && !filter2 && !filter3 && !filter4)
            return new ArrayList<>(getAllInternshipsClone());

        if (filter1)
            for (Internship i : getAllInternshipsClone())
                if (i instanceof T3)
                    aux.add(i);

        if (filter2) {

            for (Internship i : getAllInternshipsClone())
                if (i.getProfessorResponsible() != null && !aux.contains(i)) // we don't want to add the same internship/project twice, that's why we do the second verification
                    aux.add(i);

        }

        if (filter3) {

            List<String> internshipIDWithCandidacy;

            for (Candidacy i : candidacies) {
                internshipIDWithCandidacy = i.getInternshipId();
                for (Internship j : getAllInternshipsClone())
                    if (internshipIDWithCandidacy.contains(j.getID()) && !aux.contains(j))
                        aux.add(j);
            }

        }

        if (filter4) {

            List<String> everyCandidacyInternshipsID = new ArrayList<>();

            for (Candidacy i : candidacies)
                everyCandidacyInternshipsID.addAll(i.getInternshipId());

            for (Internship i : getAllInternshipsClone())
                if (!everyCandidacyInternshipsID.contains(i.getID()) && !aux.contains(i))
                    aux.add(i);

        }

        return aux;

    }

    public List<Candidacy> getAllCandidaciesClone() {
        return candidacies.stream().map(Candidacy::clone).toList();
    }

    //      ManageCandidacy (end)

    //      InternshipAssignmentState (start)

    public boolean autoInternshipAssignment1() {

        // first automatically attribution

        boolean changes = false;

        for (Internship i : internships)
            if (i.getStudentNumber() != 0) {
                for (Student j : students)
                    if (i.getStudentNumber() == j.getNumber() && j.getInternshipAssociated() == null) { // if this is a pre-associated internship
                        j.setInternshipAssociated(i);
                        i.setAssociated(true);
                        changes = true;
                        break;
                    }
            }

        return changes;

    }

    public int autoInternshipAssignment2() {

        int result = -1;

        // second automatically attribution

        // we are just going to search for students that have a candidacy
        List<Long> studentsThatHaveCandidacies = new ArrayList<>();
        for (Candidacy i : candidacies)
            studentsThatHaveCandidacies.add(i.getStudentNumber());

        // first we have to sort every student by their grade
        List<Student> sortedStudents = new ArrayList<>(); // we first receive every student that doesn't have an internship associated (a reference to the original array because we want to change them)
        for (Student i : students)
            if (i.getInternshipAssociated() == null && studentsThatHaveCandidacies.contains(i.getNumber())) // if he doesn't have an internship associated and has a candidacy
                sortedStudents.add(i);
        Collections.sort(sortedStudents); // and then we sort every student by their grades

        if (sortedStudents.isEmpty())
            return -1;

        for (int i = 0; i < sortedStudents.size(); i++) {
            if (i + 1 < sortedStudents.size()) { // if we have a student ahead of the current one...
                if (sortedStudents.get(i).getGrade() == sortedStudents.get(i + 1).getGrade()) { // first we see if the student ahead has the same grade ass this one
                    if (searchForFinalDrawPossibility(sortedStudents.get(i), sortedStudents.get(i + 1))) // we search for the final condition to see if there is a real draw condition
                        result = -2; // we send this result so the interface knows that we have to solve this draw condition
                }
            }
        }

        if (result == -2)
            return result;

        // if there is no draw condition, we are just going to associate an internship to the student

        List<String> auxInternshipWithProposalFromStudent = new ArrayList<>(); // we need to know which internships the student is interested in
        for (Student i : sortedStudents) {

            for (Candidacy j : candidacies)
                if (j.getStudentNumber() == i.getNumber()) {
                    auxInternshipWithProposalFromStudent = j.getInternshipId(); // now we already have the ID's from the internships that the student is interested in
                    break;
                }

            for (String r : auxInternshipWithProposalFromStudent) { // now we are going to associate the internship to the student
                for (Internship w : internships) {
                    if (!w.isAssociated() && w.getID().equals(r)) { // if we find an internship that is not associated to anyone and the student is interested in...
                        if (!((w instanceof T1 || w instanceof T3) && !i.isInternship())) { // if we get an internship where the student has permission to get associated to it...
                            if (i.getInternshipAssociated() == null) {
                                i.setInternshipAssociated(w); // the association itself
                                w.setAssociated(true); // indicates that this internship is now associated to someone
                                result = 1;
                            }
                        }
                    }
                }
            }

        }

        return result;

    }

    public boolean searchForFinalDrawPossibility(Student s1, Student s2) {

        List<String> InternshipWithProposalFromStudent1 = new ArrayList<>(); // we need to know which internships (ID) the student is interested + available in

        for (Internship i : internships)
            if (!i.isAssociated()) // if the internship is  available
                for (Candidacy j : getAllCandidaciesClone())
                    if (s1.getNumber() == j.getStudentNumber()) // when we get to the candidacy from the student that we want
                        for (String r : j.getInternshipId()) // we loop through every ID that the student proposed to
                            if (r.equals(i.getID()))
                                InternshipWithProposalFromStudent1.add(r); // we add the ID that it's available + got proposed by the student

        List<String> InternshipWithProposalFromStudent2 = new ArrayList<>(); // we need to know which internships (ID) the student is interested + available in

        for (Internship i : internships)
            if (!i.isAssociated()) // if the internship is  available
                for (Candidacy j : getAllCandidaciesClone())
                    if (s2.getNumber() == j.getStudentNumber()) // when we get to the candidacy from the student that we want
                        for (String r : j.getInternshipId()) // we loop through every ID that the student proposed to
                            if (r.equals(i.getID()))
                                InternshipWithProposalFromStudent2.add(r); // we add the ID that it's available + got proposed by the student

        // if the first proposal is the same internship, we have a draw condition
        return InternshipWithProposalFromStudent1.get(0).equals(InternshipWithProposalFromStudent2.get(0));

    }

    public List<Student> getStudentsInDrawCondition() {

        // este código está +/- repetido na função autoInternshipAssignment2(), mas eu não consigo fazer de outra forma...

        // we are just going to search for students that have a candidacy
        List<Long> studentsThatHaveCandidacies = new ArrayList<>();
        for (Candidacy i : candidacies)
            studentsThatHaveCandidacies.add(i.getStudentNumber());

        // first we have to sort every student by their grade
        List<Student> sortedStudents = new ArrayList<>(); // we first receive every student that doesn't have an internship associated (a reference to the original array because we want to change them)
        for (Student i : getAllStudentsClone()) // we are going to return students, so we don't want a reference to the original array
            if (i.getInternshipAssociated() == null && studentsThatHaveCandidacies.contains(i.getNumber())) // if he doesn't have an internship associated and has a candidacy
                sortedStudents.add(i);
        Collections.sort(sortedStudents); // and then we sort every student by their grades

        if (sortedStudents.isEmpty())
            return null;

        List<Student> twoStudents = new ArrayList<>(2); // these are the two student that the user will decide which one will receive an internship first

        for (int i = 0; i < sortedStudents.size(); i++) {
            if (i + 1 < sortedStudents.size()) { // if we have a student ahead of the current one...
                if (sortedStudents.get(i).getGrade() == sortedStudents.get(i + 1).getGrade()) { // first we see if the student ahead has the same grade ass this one
                    if (searchForFinalDrawPossibility(sortedStudents.get(i), sortedStudents.get(i + 1))) {// we search for the final condition to see if there is a real draw condition
                        twoStudents.add(sortedStudents.get(i));
                        twoStudents.add(sortedStudents.get(i + 1));
                        return twoStudents; // we return the two students that are in a draw condition
                    }
                }
            }
        }

        return null;

    }

    public List<Internship> getFirstAndSecondStudentInternships(Student s) {

        List<Internship> aux = new ArrayList<>();
        List<String> idsFromInternship = new ArrayList<>();

        for (Candidacy i : candidacies)
            if (i.getStudentNumber() == s.getNumber()) {
                idsFromInternship = i.getInternshipId();
                break;
            }

        for (Internship i : getAllInternshipsClone())
            if (idsFromInternship.contains(i.getID()))
                aux.add(i); // we are going to send the internships that the student has proposed to, even the ones that are already associated to someone

        return aux; // we are not sending a reference to the original object!
    }

    public boolean associateStudentWithInternship(Student s, Internship it) {

        for (Student i : students)
            if (i.equals(s) /*&& i.getInternshipAssociated() == null && !it.isAssociated()*/) {
                i.setInternshipAssociated(it); // the association itself
                for (Internship j : internships)
                    if (j.equals(it))
                        j.setAssociated(true); // make sure that now the internship is marked as "associated to someone"
                return true;
            }

        return false;

    }

    public boolean removeAssociationStudentInternship(Student s) {

        for (Student i : students)
            if (i.equals(s)) {

                // é para colocar?
                /*if(i.getInternshipAssociated().getAssociatedProfessor()!=null){ // we also want to remove the association from the professor, if it exists
                    i.getInternshipAssociated().getAssociatedProfessor().setAssociated(false); // now, if there was a professor, he doesn't belong to the internship anymore
                    i.getInternshipAssociated().setAssociatedProfessor(null); // the internship now doesn't have a professor
                }*/

                i.getInternshipAssociated().setAssociated(false);
                i.setInternshipAssociated(null); // now the student doesn't have an internship associated to him
                return true;
            }

        return false;
    }

    public boolean removeEveryInternshipAssociation() {

        boolean changes = false;

        for (Student i : students)
            if (i.getInternshipAssociated() != null)
                if (!(i.getInternshipAssociated() instanceof T3))
                    if (!(i.getInternshipAssociated() instanceof T2 && i.getInternshipAssociated().getStudentNumber() != 0)) {

                        // é para colocar?
                        /*if (i.getInternshipAssociated().getAssociatedProfessor() != null) { // we also want to remove the association from the professor, if it exists
                            i.getInternshipAssociated().getAssociatedProfessor().setAssociated(false); // now, if there was a professor, he doesn't belong to the internship anymore
                            i.getInternshipAssociated().setAssociatedProfessor(null); // the internship now doesn't have a professor
                        }*/

                        i.getInternshipAssociated().setAssociated(false); // now the internship doesn't have the flag saying that it belongs tto a student
                        i.setInternshipAssociated(null); // now the student doesn't have an internship associated to him
                        changes = true;
                    }

        return changes;

    }

    public List<Internship> getFreeInternships(Student s) {

        List<Internship> aux = new ArrayList<>();

        //for (Internship i : getFirstAndSecondStudentInternships(s)) /// we search in every internship that the student proposed to
        for (Internship i : getAllInternshipsClone())
            if (!i.isAssociated()) { // if it's not associated to someone
                if (s.isInternship())
                    aux.add(i);
                else if (!s.isInternship() && !(i instanceof T1 || i instanceof T3)) // !(if he doesn't have permission to have internship of type T1)
                    aux.add(i);
            }

        return aux; // we send the ones that he has conditions to join

    }

    public boolean closeInternshipState() {

        List<Long> studentsWithCandidacy = new ArrayList<>();

        for (Candidacy i : candidacies)
            studentsWithCandidacy.add(i.getStudentNumber());

        for (Student i : students)
            if (studentsWithCandidacy.contains(i.getNumber()))
                if (i.getInternshipAssociated() == null)
                    return false;

        return true;
    }

    public List<Student> getAllStudentsInternship(int filter) {

        List<Student> aux = new ArrayList<>();

        if (filter == 1)
            for (Student i : getAllStudentsClone()) // first condition (have auto proposal associated)
                //if (i.getInternshipAssociated() instanceof T3) // n precisa de estar associado
                for (Internship j : internships)
                    if (j instanceof T3)
                        if (j.getStudentNumber() == i.getNumber())
                            aux.add(i);

        if (filter == 2)
            for (Student i : getAllStudentsClone())  // second condition (have candidacy registered)
                for (Candidacy c : candidacies)
                    if (c.getStudentNumber() == i.getNumber())
                        aux.add(i);

        /*if (filter == 3)
            for (Student i : getAllStudentsClone())
                if (i.getInternshipAssociated() != null)
                    aux.add(i);*/

        if (filter == 3)
            for (Student i : getAllStudentsClone()) // first condition (have auto proposal associated)
                if (i.getInternshipAssociated() instanceof T3)
                    for (Internship j : internships)
                        if (j.getStudentNumber() == i.getNumber())
                            aux.add(i);

        if (filter == 4)
            for (Student i : getAllStudentsClone())
                if (i.getInternshipAssociated() == null)
                    aux.add(i);

        return aux;
    }

    public List<String> getOrderedCandidacies(Student s) {

        for (Candidacy i : getAllCandidaciesClone())
            if (i.getStudentNumber() == s.getNumber()) {
                return i.getInternshipId();
            }

        return null;
    }

    public List<Internship> getAllInternshipsWithFilters(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {

        List<Internship> aux = new ArrayList<>();

        if (!filter1 && !filter2 && !filter3 && !filter4)
            return new ArrayList<>(getAllInternshipsClone());

        if (filter1) { // first condition (auto proposal from students)

            for (Internship i : getAllInternshipsClone())
                if (i instanceof T3)
                    aux.add(i);

        }

        if (filter2) { // second condition (proposals from professors)

            for (Internship i : getAllInternshipsClone())
                if (i instanceof T2 && !aux.contains(i))
                    aux.add(i);

        }

        if (filter3) { // third condition (proposals not given to students (free to use))

            for (Internship i : getAllInternshipsClone())
                if (!i.isAssociated() && !aux.contains(i))
                    aux.add(i);


        }

        if (filter4) { // fourth condition (proposals already given to a student)

            for (Internship i : getAllInternshipsClone())
                if (i.isAssociated() && !aux.contains(i))
                    aux.add(i);

        }

        return aux;

    }

    public List<Student> getFreeStudents() {

        List<Student> aux = new ArrayList<>();

        for (Student i : getAllStudentsClone())
            if (i.getInternshipAssociated() == null)
                aux.add(i);

        return aux;
    }

    public List<Student> getAssociatedStudents() {

        List<Student> aux = new ArrayList<>();

        for (Student i : getAllStudentsClone())
            if (i.getInternshipAssociated() != null)
                aux.add(i);

        return aux;
    }

    //      InternshipAssignmentState (end)

    //      ProfessorAssignmentState (start)

    public List<Professor> getFreeProfessors() {

        /*List<Professor> aux = new ArrayList<>();

        for (Professor i : getAllProfessorsClone())
            if (!i.isAssociated())
                aux.add(i);

        return aux;*/

        return new ArrayList<>(getAllProfessorsClone()); // every professor is free because he can be associated to more than one project

    }

    public List<Professor> getAssociatedProfessors() {

        List<Professor> aux = new ArrayList<>();

        for (Professor i : getAllProfessorsClone())
            if (i.isAssociated())
                aux.add(i);

        return aux;

    }

    public List<Internship> getInternshipsWithoutProfessor() {

        List<Internship> aux = new ArrayList<>();

        // now we return every internship that is associated to a student but don't have a professor associated
        for (Internship i : getAllInternshipsClone())
            if (i.isAssociated() && i.getAssociatedProfessor() == null)
                aux.add(i);

        return aux;

    }

    public boolean manualProfessorAssociation(Internship it, Professor p) {

        it.setAssociatedProfessor(p);
        //p.setAssociated(true);

        // since P is a clone, we want to search for him in the original array
        for (Professor i : professors)
            if (i.equals(p)) {
                i.setAssociated(true);
                i.addnAssociations();
                return true;
            }

        return false;

    }

    public boolean removeProfessorAssociation(Professor p) {

        boolean last = true;

        for (Internship i : internships)
            if (i.getAssociatedProfessor() != null)
                if (i.getAssociatedProfessor().equals(p)) {
                    i.setAssociatedProfessor(null);
                    //p.setAssociated(false);
                    // since P is a clone, we want to search for him in the original array
                    for (Professor j : professors)
                        if (j.equals(p)) {

                            // we have to see if this was the last internship that the professor was associated to
                            // if it is the last one, then we will change his variable "associated" to false
                            for (Internship i2 : internships)
                                if (i2.getAssociatedProfessor() != null)
                                    if (i2.getAssociatedProfessor().equals(p)) {
                                        last = false;
                                        break;
                                    }

                            if (last) {
                                j.setAssociated(false);
                                j.subnAssociations();
                            }

                            break;
                        }
                    return true;
                }

        return false;

    }

    public boolean autoProfessorAssignment() {

        boolean changes = false;

        for (Internship i : internships)
            if (i instanceof T2 && i.getProfessorResponsible() != null && i.getAssociatedProfessor() == null)
                for (Professor j : professors)
                    if (j.getEmail().equals(i.getProfessorResponsible())) {
                        i.setAssociatedProfessor(j);
                        j.setAssociated(true);
                        j.addnAssociations();
                        changes = true;
                    }

        return changes;

    }

    public List<Student> getStudentsWithInternshipAndProfessor() {

        List<Student> aux = new ArrayList<>();

        for (Student i : getAllStudentsClone())
            if (i.getInternshipAssociated() != null)
                if (i.getInternshipAssociated().getAssociatedProfessor() != null)
                    aux.add(i);

        return aux;

    }

    public List<Student> getStudentsWithInternshipAndWithoutProfessor() {

        List<Student> aux = new ArrayList<>();

        for (Student i : getAllStudentsClone())
            if (i.getInternshipAssociated() != null)
                if (i.getInternshipAssociated().getAssociatedProfessor() == null)
                    aux.add(i);

        return aux;

    }

    public List<String> getStatsPerProfessor() {

        // acabar esta função (max, min, media, etc...)

        return null;

    }

    //      ProfessorAssignmentState (end)

    //      SearchState (start)

    public List<Student> getStudentsWithInternship() {

        List<Student> aux = new ArrayList<>();

        for (Student i : getAllStudentsClone())
            if (i.getInternshipAssociated() != null)
                aux.add(i);

        return aux;

    }

    public List<Student> getStudentsWithoutInternshipAndWithCandidacy() {

        List<Student> aux = new ArrayList<>();

        for (Student i : getAllStudentsClone())
            if (i.getInternshipAssociated() == null)
                for (Candidacy j : candidacies)
                    if (j.getStudentNumber() == i.getNumber()) {
                        aux.add(i);
                        break;
                    }

        return aux;

    }

    public List<Internship> getAvailableInternship() {

        List<Internship> aux = new ArrayList<>();
        boolean foundStudent;

        for (Internship i : getAllInternshipsClone()) {

            foundStudent = false;

            for (Student j : students) {
                if (j.getInternshipAssociated() != null)
                    if (j.getInternshipAssociated().equals(i)) {
                        foundStudent = true;
                        break;
                    }
            }

            if (!foundStudent)
                aux.add(i);

        }

        return aux;

    }

    public List<Internship> getAttributedInternship() {

        List<Internship> aux = new ArrayList<>();

        for (Internship i : getAllInternshipsClone())
            for (Student j : students)
                if (j.getInternshipAssociated() != null)
                    if (j.getInternshipAssociated().equals(i)) {
                        aux.add(i);
                        break;
                    }

        return aux;
    }

    //      SearchState (end)

    //      Importation functions (start)

    public List<String> importStudents(String filename) {

        File file;
        Scanner fileReader = null;
        List<String> returnValue = new ArrayList<>();
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //file = new File("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\students.csv");
            file = new File(filename);
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String dataLine = fileReader.nextLine(); // receiving one line of data from the csv file
                returnValue.add(separateDataStudents(dataLine)); // we want to save one line of data and then separate with the ","
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            returnValue.add("Ficheiro não encontrado");
            return returnValue;
        } finally {
            if (fileReader != null)
                fileReader.close();
        }

        return returnValue;

    }

    public String separateDataStudents(String dataFromFile) {

        if (dataFromFile != null) {

            // variables that will be used to build a new Student
            String auxName = null;
            String auxEmail = null;
            long auxNumber = 0;
            String auxCourse = null;
            String auxBranch = null;
            double auxGrade = 0;
            boolean auxInternship = false;

            String[] dataSplitted = dataFromFile.split(",");

            // since we have one line of data, we want to arrange it into the attributes from Student

            int count = 0;

            for (String i : dataSplitted) {
                switch (count) {
                    case 0 -> {
                        count++;
                        auxNumber = Long.parseLong(i); // converts the string into a long
                    }
                    case 1 -> {
                        count++;
                        auxName = i;
                    }
                    case 2 -> {
                        count++;
                        auxEmail = i;
                    }
                    case 3 -> {
                        count++;
                        auxCourse = i;
                    }
                    case 4 -> {
                        count++;
                        auxBranch = i;
                    }
                    case 5 -> {
                        count++;
                        auxGrade = Double.parseDouble(i); // converts the string into a double
                    }
                    case 6 -> {
                        count++;
                        if (i.equalsIgnoreCase("true") || i.equalsIgnoreCase("false"))
                            auxInternship = Boolean.parseBoolean(i); // converts the string into a boolean
                        else {
                            return "Não se pode importar um aluno cujo atributo 'Possibbilidade de aceder a estágios além de projetos' seja diferente de true ou false!";
                        }

                    }
                }
            }

            Student aux = new Student(auxName, auxEmail, auxNumber, auxCourse, auxBranch, auxGrade, auxInternship, null);

            switch (addStudent(aux)) {
                case -1:
                    return "Este aluno já existe!";
                case -2:
                    return "Impossível importar um aluno cujo ramo não existe!";
                case -3:
                    return "Impossível importar um aluno cujo curso não existe!";
                case -4:
                    return "Impossível importar um aluno cuja classificação não está entre 0 e 1!";
                case -5:
                    return "Impossível importar um aluno cujo número é inválido!";
                case 1:
                    return "Aluno importado com sucesso!";
            }

        }

        return null;

    }

    public List<String> importProfessors(String filename) {

        File file;
        Scanner fileReader = null;
        List<String> returnValue = new ArrayList<>();
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //file = new File("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\professors.csv");
            file = new File(filename);
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String dataLine = fileReader.nextLine(); // receiving one line of data from the csv file
                returnValue.add(separateDataProfessors(dataLine)); // we want to save one line of data and then separate with the ","
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            returnValue.add("Ficheiro não encontrado");
            return returnValue;
        } finally {
            if (fileReader != null)
                fileReader.close();
        }

        return returnValue;

    }

    public String separateDataProfessors(String dataFromFile) {

        if (dataFromFile != null) {

            // variables that will be used to build a new Professor
            String auxName = null;
            String auxEmail = null;

            String[] dataSplitted = dataFromFile.split(",");

            // since we have one line of data, we want to arrange it into the attributes from Student

            int count = 0;

            for (String i : dataSplitted) {
                switch (count) {
                    case 0 -> {
                        count++;
                        auxName = i;
                    }
                    case 1 -> {
                        count++;
                        auxEmail = i;
                    }
                }
            }

            Professor aux = new Professor(auxName, auxEmail, false);

            if (!addProfessor(aux))
                return "Este professor já existe!";
            else
                return "Professor importado com sucesso!";

        }

        return null;

    }

    public List<String> importInternships(String filename) {

        File file;
        Scanner fileReader = null;
        List<String> returnValue = new ArrayList<>();
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //file = new File("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\internships.csv");
            file = new File(filename);
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String dataLine = fileReader.nextLine(); // receiving one line of data from the csv file
                returnValue.add(separateDataInternships(dataLine)); // we want to save one line of data and then separate with the ","
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            returnValue.add("Ficheiro não encontrado");
            return returnValue;
        } finally {
            if (fileReader != null)
                fileReader.close();
        }

        return returnValue;

    }

    public String separateDataInternships(String dataFromFile) {

        if (dataFromFile != null) {

            // variables that will be used to build a new Internship
            String auxID = null;
            String auxTitle = null;
            String auxCompany = null;
            String auxBranchDestiny = null;
            String auxProfessorResponsible = null;
            long auxStudentNumber = 0;

            String auxProfessorEmail; // this variable will be used to search for a Professor

            String[] dataSplitted = dataFromFile.split(",");

            // since we have one line of data, we want to arrange it into the attributes from Internship

            // we have 3 types of internships, and the first attribute that we receive in the .csv file, determines which type we are working with

            int type = 0;

            if (dataSplitted[0].equalsIgnoreCase("T1"))
                type = 1;
            else if (dataSplitted[0].equalsIgnoreCase("T2"))
                type = 2;
            else if (dataSplitted[0].equalsIgnoreCase("T3"))
                type = 3;
            else {
                return "Só é possível importar estágios/projetos do tipo T1, T2 e T3!";
            }

            switch (type) {
                case 1 -> {
                    int count = 0;
                    for (int i = 1; i < dataSplitted.length; i++) { // i starts in one because we don't want the first information to be saved (T1, T2, T3)
                        switch (count) {
                            case 0 -> {
                                count++;
                                auxID = dataSplitted[i];
                            }
                            case 1 -> {
                                count++;
                                auxBranchDestiny = dataSplitted[i];
                            }
                            case 2 -> {
                                count++;
                                auxTitle = dataSplitted[i];
                            }
                            case 3 -> {
                                count++;
                                auxCompany = dataSplitted[i];
                            }
                            case 4 -> {
                                count++;
                                auxStudentNumber = Long.parseLong(dataSplitted[i]);
                            }
                        }
                    }
                }
                case 2 -> {
                    int count2 = 0;
                    for (int i = 1; i < dataSplitted.length; i++) { // i starts in one because we don't want the first information to be saved (T1, T2, T3)
                        switch (count2) {
                            case 0 -> {
                                count2++;
                                auxID = dataSplitted[i];
                            }
                            case 1 -> {
                                count2++;
                                auxBranchDestiny = dataSplitted[i];
                            }
                            case 2 -> {
                                count2++;
                                auxTitle = dataSplitted[i];
                            }
                            case 3 -> {
                                count2++;
                                auxProfessorResponsible = dataSplitted[i];
                            }
                            case 4 -> {
                                count2++;
                                auxStudentNumber = Long.parseLong(dataSplitted[i]);
                            }
                        }
                    }
                }
                case 3 -> {
                    int count3 = 0;
                    for (int i = 1; i < dataSplitted.length; i++) { // i starts in one because we don't want the first information to be saved (T1, T2, T3)
                        switch (count3) {
                            case 0 -> {
                                count3++;
                                auxID = dataSplitted[i];
                            }
                            case 1 -> {
                                count3++;
                                auxTitle = dataSplitted[i];
                            }
                            case 2 -> {
                                count3++;
                                auxStudentNumber = Long.parseLong(dataSplitted[i]);
                            }
                        }
                    }
                }
            }

            if (auxID == null) {
                return "Não se pode importar um estágio/projeto sem um ID!";
            }

            if (auxTitle == null) {
                return "Não se pode importar um estágio/projeto sem um título!";
            }

            switch (type) {
                case 1 -> {

                    if (auxBranchDestiny == null) {
                        return "Não se pode importar um estágio sem um ramo de destino!";
                    }

                    if (auxCompany == null) {
                        return "Não se pode importar um estágio sem uma entidade de acolhimento!";
                    }

                    Internship auxT1 = new T1(auxID, auxBranchDestiny, auxTitle, auxCompany, auxStudentNumber, null, false);

                    switch (addInternship(auxT1)) {
                        case -1:
                            return "Não se pode importar um estágio com um ID/Número de aluno existente noutro estágio/projeto!";
                        case -2:
                            return "Não se pode importar um estágio com um aluno inexistente!";
                        case -4:
                            return "Não se pode importar um estágio com um ramo de destino inexistente!";
                        case -5:
                            return "Não se pode importar um estágio com um número de aluno inválido!";
                        case 1:
                            return "Estágio importado com sucesso!";
                    }

                }
                case 2 -> {

                    if (auxBranchDestiny == null) {
                        return "Não se pode importar um projeto sem um ramo de destino!";
                    }

                    if (auxProfessorResponsible == null) {
                        return "Não se pode importar um projeto sem um email de um professor previamente registado!";
                    }

                    Internship auxT2 = new T2(auxID, auxBranchDestiny, auxTitle, auxProfessorResponsible, auxStudentNumber, null, false);

                    switch (addInternship(auxT2)) {
                        case -1:
                            return "Não se pode importar um projeto com um ID/Número de aluno existente noutro estágio/projeto!";
                        case -2:
                            return "Não se pode importar um projeto com um aluno inexistente!";
                        case -3:
                            return "Não se pode importar um projeto com um professor inexistente!";
                        case -4:
                            return "Não se pode importar um projeto com um ramo de destino inexistente!";
                        case -5:
                            return "Não se pode importar um projeto com um número de aluno inválido!";
                        case 1:
                            return "Projeto importado com sucesso!";
                    }

                }
                case 3 -> {

                    Internship auxT3 = new T3(auxID, auxTitle, auxStudentNumber, null, false);

                    switch (addInternship(auxT3)) {

                        case -1:
                            return "Não se pode importar um estágio/projeto autoproposto com um ID/Número de aluno existente noutro estágio/projeto!";
                        case -2:
                            return "Não se pode importar um estágio/projeto com um aluno inexistente!";
                        case -5:
                            return "Não se pode importar um estágio/projeto autoproposto com um número de aluno inválido!";
                        case 1:
                            return "Estágio/projeto autoproposto importado com sucesso!";

                    }

                }
            }

        }

        return null;

    }

    public List<String> importCandidacies(String filename) {

        File file;
        Scanner fileReader = null;
        List<String> returnValue = new ArrayList<>();
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //file = new File("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\candidacies.csv");
            file = new File(filename);
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String dataLine = fileReader.nextLine(); // receiving one line of data from the csv file
                returnValue.add(separateDataCandidacies(dataLine)); // we want to save one line of data and then separate with the ","
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            returnValue.add("Ficheiro não encontrado");
            return returnValue;
        } finally {
            if (fileReader != null)
                fileReader.close();
        }

        return returnValue;

    }

    public String separateDataCandidacies(String dataFromFile) {

        if (dataFromFile != null) {

            // variables that will be used to build a new Professor
            long auxStudentNumber = 0;
            List<String> auxInternshipId = new ArrayList<>();

            String[] dataSplitted = dataFromFile.split(",");

            // since we have one line of data, we want to arrange it into the attributes from Student

            int count = 0;

            for (String i : dataSplitted) {

                if (count == 0) {
                    auxStudentNumber = Long.parseLong(i);
                    count++;
                } else {
                    auxInternshipId.add(i);
                }

            }

            if (auxStudentNumber == 0)
                return "É impossível importar uma candidatura sem um número de estudante";


            if (auxInternshipId.size() == 0)
                return "É necessário pelo menos um estágio/projeto associado a esta candidatura para poder ser importada!";


            Candidacy aux = new Candidacy(auxStudentNumber, auxInternshipId);

            switch (addCandidacy(aux)) {

                case -1:
                    return "Este aluno já tem uma candidatura!";
                case -2:
                    return "Não é possível importar uma candidatura cujo número de aluno seja inexistente!";
                case -3:
                    return "Não é possível importar uma candidatura cujo número de aluno seja inválido!";
                case -4:
                    return "Não é possível importar uma candidatura, cujo aluno já apresenta um estágio/projeto associado a ele mesmo!";
                case -5:
                    return "Não é possível importar uma candidatura que apresente estágios/projetos com outros alunos associados!";
                case 1:
                    return "Candidatura importada com sucesso!";

            }

        }

        return null;

    }

    public boolean importAll(String filename) {

        ObjectInputStream in;
        StringBuilder fName = new StringBuilder();
        fName.append(filename).append(".dat");

        try {
            //in = new ObjectInputStream(new FileInputStream("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\data.dat"));
            in = new ObjectInputStream(new FileInputStream(fName.toString()));
            students = (List<Student>) in.readObject();
            professors = (List<Professor>) in.readObject();
            internships = (List<Internship>) in.readObject();
            candidacies = (List<Candidacy>) in.readObject();
            in.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException | ClassNotFoundException ex) {
            return false;
        }

    }

    //      Importation functions (end)

    //     General Exportation functions (start)

    public boolean exportState3(String filename) {

        if (students.size() == 0)
            return false;

        PrintWriter pw = null;
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\studentsInfo.csv")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Student i : students)
                for (Candidacy j : candidacies)
                    if (j.getStudentNumber() == i.getNumber()) {

                        pw.print(i.getName() + "," + i.getEmail() + "," + i.getNumber() + "," + i.getCourse() + "," + i.getBranch() + "," + i.getGrade() + "," + i.isInternship() + "," + j.getInternshipId());

                        if (i.getInternshipAssociated() != null)
                            pw.println("," + i.getInternshipAssociated().getID() + "," + (j.getInternshipId().indexOf(i.getInternshipAssociated().getID()) + 1) + "º");

                        pw.println();

                        //pw.println(i.getName() + "," + i.getEmail() + "," + i.getNumber() + "," + i.getCourse() + "," + i.getBranch() + "," + i.getGrade() + "," + i.isInternship() + "," + j.getStudentNumber() + "," + j.getInternshipId() + "," + i.getInternshipAssociated().getID() + "," + i.getInternshipAssociated().getBranchDestiny() + "," + i.getInternshipAssociated().getTitle() + "," + i.getInternshipAssociated().getCompany() + "," + i.getInternshipAssociated().getProfessorResponsible() + "," + i.getInternshipAssociated().getStudentNumber());
                    }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }

        return true;

    }

    public boolean exportState4and5(String filename) {

        if (students.size() == 0)
            return false;

        PrintWriter pw = null;
        //StringBuilder fName = new StringBuilder();
        //fName.append(filename).append(".csv");

        try {
            //pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\studentsInfo.csv")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Student i : students)
                for (Candidacy j : candidacies)
                    if (j.getStudentNumber() == i.getNumber()) {

                        pw.println(i.getName() + "," + i.getEmail() + "," + i.getNumber() + "," + i.getCourse() + "," + i.getBranch() + "," + i.getGrade() + "," + i.isInternship() + "," + j.getInternshipId() + "," + i.getInternshipAssociated().getID() + "," + (j.getInternshipId().indexOf(i.getInternshipAssociated().getID()) + 1) + "º" + "," + i.getInternshipAssociated().getProfessorResponsible());
                        //pw.println(i.getName() + "," + i.getEmail() + "," + i.getNumber() + "," + i.getCourse() + "," + i.getBranch() + "," + i.getGrade() + "," + i.isInternship() + "," + j.getStudentNumber() + "," + j.getInternshipId() + "," + i.getInternshipAssociated().getID() + "," + i.getInternshipAssociated().getBranchDestiny() + "," + i.getInternshipAssociated().getTitle() + "," + i.getInternshipAssociated().getCompany() + "," + i.getInternshipAssociated().getProfessorResponsible() + "," + i.getInternshipAssociated().getStudentNumber());
                    }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null)
                pw.close();
        }

        return true;

    }

    public boolean exportAll(String filename) {

        if (students.size() == 0 || professors.size() == 0 || internships.size() == 0 || candidacies.size() == 0)
            return false;

        ObjectOutputStream out;
        StringBuilder fName = new StringBuilder();
        fName.append(filename).append(".dat");


        try {
            //out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\joaom\\Desktop\\Morais\\Universidade\\2 ano V2\\2 semestre\\PA\\TP\\meta 2\\APOIO_PoE\\data.dat"));
            out = new ObjectOutputStream(new FileOutputStream(fName.toString()));
            out.writeObject(students);
            out.writeObject(professors);
            out.writeObject(internships);
            out.writeObject(candidacies);
            out.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

    }

    //      General Exportation functions (end)

    // 2ª meta GUI

    public List<String> getInfo() {

        List<String> auxList = new ArrayList<>();
        int val1 = 0, val2, val3 = 0;

        for (Student i : students)
            if (i.getInternshipAssociated() != null)
                val1++;

        val2 = internships.size() - val1;

        for (Internship i : internships)
            if (i.getAssociatedProfessor() != null)
                val3++;

        auxList.add("Número de alunos: " + students.size());
        auxList.add("Número de docentes: " + professors.size());
        auxList.add("Número de estágios/projetos: " + internships.size());
        auxList.add("Número de candidaturas: " + candidacies.size());
        auxList.add("Número de propostas atribuídas: " + val1);
        auxList.add("Número de propostas por atribuir: " + val2);
        auxList.add("Número de propostas com docentes associados: " + val3);

        return auxList;

    }

    public void deleteStudent(Student s) {

        // delete a possible autointernship from the student
        internships.removeIf(i -> i.getStudentNumber() == s.getNumber());

        if (s.getInternshipAssociated() != null) { // delete a possible association between student and internship
            for (Internship i : internships)
                if (i.equals(s.getInternshipAssociated()))
                    i.setAssociated(false);
        }

        students.remove(s);

    }

    public void deleteProfessor(Professor p) {

        for (Internship i : internships) // delete a possible association between the professor and an internship
            if (p.equals(i.getAssociatedProfessor()))
                i.setProfessorResponsible(null);

        professors.remove(p);

    }

    public void deleteInternship(Internship it) {

        for (Student i : students) // delete a possible association between student and internship
            if (i.getInternshipAssociated() != null)
                if (i.getInternshipAssociated().equals(it))
                    i.setInternshipAssociated(null);

        for (Professor i : professors)
            if (it.getAssociatedProfessor() != null)
                if (it.getAssociatedProfessor().equals(i))
                    if (i.getnAssociations() == 1) {
                        i.setAssociated(false); // delete a possible association between the professor and an internship (apesar de esta variável já não estar a ser usada depois de se adicionar a possibilidade de um professor estar associado a mais que um projeto)
                        i.subnAssociations();
                    } else
                        i.subnAssociations();

        internships.remove(it);

    }

    public void deleteCandidacy(Candidacy c) {

        candidacies.removeIf(c::equals);

    }

    public Double getDAstats() {

        Double value = 0.0;

        for (Internship i : internships)
            if (ramoDA.contains(i.getBranchDestiny()))
                value++;

        return value;

    }

    public Double getSIstats() {

        Double value = 0.0;

        for (Internship i : internships)
            if (ramoSI.contains(i.getBranchDestiny()))
                value++;

        return value;

    }

    public Double getRASstats() {

        Double value = 0.0;

        for (Internship i : internships)
            if (ramoRAS.contains(i.getBranchDestiny()))
                value++;

        return value;

    }

    public List<Professor> getProfessorsStats() {

        List<Professor> auxList = professors;

        Collections.sort(auxList);

        for (int i = 5; i < auxList.size(); i++)
            auxList.remove(i);

        return auxList.stream().map(Professor::clone).toList(); // send a clone of this array

    }

    // methods from the interface IOriginator

    @Override
    public IMemento save() {
        return null;
    }

    @Override
    public void restore(IMemento memento) {

    }

}
