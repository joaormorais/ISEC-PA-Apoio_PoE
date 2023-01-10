package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;

public interface IPoEManagementState {

    // mutual functions

    PoEManagementState getState(); // returns the state of the program
    void goBack(); // function to return to the state before
    void goFoward(); // goes to the next state
    void manualFoward(); // goes to the next manual mode

    // flags management (start)

    boolean isFlag();

    void setFlag(boolean flag);

    boolean isFlagClosedConfiguration();

    void setFlagClosedConfiguration(boolean flagClosedConfiguration);

    boolean isFlagClosedCandidacyConfiguration();

    void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration);

    boolean isFlagClosedInternshipAssignment();

    void setFlagClosedInternshipAssignment(boolean flagClosedInternshipAssignment);

    // flags management (end)

    //void start(); // NEW STATE
    boolean closeConfiguration(); // function to close the state

    void manageStudents(); // NEW STATE
    int addStudent(Student s); // function to add a student to our data
    List<String> importStudents(String filename); // function to import students and returns the error/success
    boolean exportStudents(String filename); // function to export every student to a CSV file
    List<Student> getAllStudents(); // function to help print every student

    void manageProfessors(); // NEW STATE
    boolean addProfessor(Professor p); // function to add a professor to our data
    List<String> importProfessors(String filename); // function to import professors and returns the error/success
    boolean exportProfessors(String filename); // function to export every professor to a CSV file
    List<Professor> getAllProfessors(); // function to help print every student

    void manageInternship(); // NEW STATE
    int addInternship(Internship it); // function to add an internship to our data
    List<String> importInternships(String filename); // function to import internships and returns the error/success
    boolean exportInternships(String filename); // function to export every internship to a CSV file
    List<Internship> getAllInternships(); // function to help print every student

    void manageCandidacy(); // NEW STATE
    List<String> importCandidacies(String filename); // function to import candidacies and returns the error/success
    List<Candidacy> getAllCandidaciesClone(); /// function to help print every candidacy
    int addCandidacy(Candidacy c); // function to add a candidacy to our data
    boolean exportCandidacies(String filename); // function to export every candidacy to a CSV file
    List<Student> getStudentsWithAutoProposedInternship(); // function to receive the students with auto proposed internship, candidacy registered and no candidacy registered
    List<Student> getStudentsWithCandidacy(); // function to receive the students with candidacy registered
    List<Student> getStudentsWithoutCandidacy(); // function to receive the students with no candidacy registered
    List<Internship> getInternshipsWithFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4); // function to receive internships depending on the filters

    //void internshipAssignment(); // NEW STATE
    List<Student> getAllStudentsInternship(int filter); // function to return every student depending on some restrictions applied by INTERNSHIP_ASSIGNMENT state
    List<String> getOrderedCandidacies(Student s); // function to return every candidacy from a student ordered by preference
    List<Internship> getAllInternshipsWithFilters(boolean filter1, boolean filter2, boolean filter3, boolean filter4); // function to return every internship depending on some restrictions applied by INTERNSHIP_ASSIGNMENT state
    boolean autoInternshipAssignment1(); // function to automatically give an internship to a student
    int autoInternshipAssignment2(); // function to automatically give an internship to a student
    List<Student> getStudentsInDrawCondition(); // function to return the 2 students that are in a draw condition while assigning internships
    boolean associateStudentWithInternship(Student s, Internship it); // function to create the association
    boolean removeAssociationStudentInternship(Student s); // function to remove the association
    List<Internship> getFreeInternships(Student s); // function to return every internship that doesn't have a student associated
    boolean closeInternshipState(); // function to see if it's possible to close this state
    //void manualInternshipAssignment(); // NEW STATE
    List<Student> getFreeStudents(); // function to return every student without internships
    List<Student> getAssociatedStudents(); // function to return every student with internships
    boolean removeEveryInternshipAssociation(); // function ro remove every association between student and internship (according to some conditions)

    //void professorAssignment(); // NEW STATE
    boolean removeProfessorAssociation(Professor p); // function to remove the association between a professor and an internship
    boolean autoProfessorAssignment(); // function to automatically give a student to an internship
    //void manualProfessorAssignment(); // NEW STATE
    List<Student> getStudentsWithInternshipAndProfessor(); // function to return every student with internship and with professor associated
    List<Student> getStudentsWithInternshipAndWithoutProfessor(); // function to return every student with internship but without professor associated
    List<String> getStatsPerProfessor(); // function to return the media, max and min per professor
    public List<Professor> getFreeProfessors(); // function to return every professor that isn't associated to an internship
    public List<Professor> getAssociatedProfessors(); // function to return every professor that is associated
    public List<Internship> getInternshipsWithoutProfessor(); // function to return every internship that doesn't have a professor associated
    public boolean manualProfessorAssociation(Internship it, Professor p); // function to associate a professor to an internship

    //void search(); // NEW STATE
    List<Student> getStudentsWithInternship(); // function that returns every student with internships associated
    List<Student> getStudentsWithoutInternshipAndWithCandidacy(); // function that returns every student without internship associated but with a candidacy
    List<Internship> getAvailableInternship(); // function that returns every internship that is not associated to a student
    List<Internship> getAttributedInternship(); // function that returns every internship that already has a student associated

    boolean exportState3(String filename); // function to export data when we are managing internships associations
    boolean exportState4and5(String filename); // function to export data when we are managing professor associations and searching
    boolean exportAll(String filename); // function to export the entire data to a binary file
    boolean importAll(String filename); // function to import the entire data from a binary file

    // GUI
    List<String> getInfo();
    void deleteStudent(Student s);
    void deleteProfessor(Professor p);
    void deleteInternship(Internship it);
    void deleteCandidacy(Candidacy c);
    String separateDataStudents(String dataFromFile);
    String separateDataProfessors(String dataFromFile);
    String separateDataInternships(String dataFromFile);
    String separateDataCandidacies(String dataFromFile);
    public Double getDAstats();
    public Double getSIstats();
    public Double getRASstats();
    public List<Professor> getProfessorsStats();

}
