package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.memento.CareTaker;

import java.io.Serializable;
import java.util.List;

public class PoEManagementContext implements Serializable {
    public static final long serialVersionID = 1L;
    private PoEManagementData data;
    private IPoEManagementState state;
    private CareTaker caretaker;

    // Constructor

    public PoEManagementContext() {
        this.data = new PoEManagementData();
        this.state = new WaitStartState(this, data);
        this.caretaker=new CareTaker(data);
    }

    // Getter's and Setter's

    private PoEManagementData getData() {
        return data;
    }

    private void setData(PoEManagementData data) {
        this.data = data;
    }

    public PoEManagementState getState() {
        return state.getState();
    }

    // Change between states

    public void changeState(PoEManagementState newState, PoEManagementData data) {
        this.state = (PoEManagementStateAdapter) newState.createState(this, data);
    }

    public void goFoward() {
        state.goFoward();
    }

    public void manualFoward() {
        state.manualFoward();
    }

    /*public void undo(){
        caretaker.undo();
        changeState(state.getState(), data);
    }*/

    // Interface functions

    // flags management (start)

    public boolean isFlag() {
        return state.isFlag();
    }

    public void setFlag(boolean flag) {
        state.setFlag(flag);
    }

    public boolean isFlagClosedConfiguration() {
        return state.isFlagClosedConfiguration();
    }

    public void setFlagClosedConfiguration(boolean flagClosedConfiguration) {
        state.setFlagClosedConfiguration(flagClosedConfiguration);
    }

    public boolean isFlagClosedCandidacyConfiguration() {
        return state.isFlagClosedCandidacyConfiguration();
    }

    public void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration) {
        state.setFlagClosedCandidacyConfiguration(flagClosedCandidacyConfiguration);
    }

    public boolean isFlagClosedInternshipAssignment() {
        return state.isFlagClosedInternshipAssignment();
    }

    public void setFlagClosedInternshipAssignment(boolean flagClosedInternshipAssignment) {
        state.setFlagClosedInternshipAssignment(flagClosedInternshipAssignment);
    }

    // flags management (end)

    /*public void start() {
        state.start();
    }*/

    public void goBack() {
        state.goBack();
    }

    public boolean closeConfiguration() {
        return state.closeConfiguration();
    }

    public void manageStudents() {
        state.manageStudents();
    }

    public void manageProfessors() {
        state.manageProfessors();
    }

    public void manageInternship() {
        state.manageInternship();
    }

    public int addStudent(Student s) {
        return state.addStudent(s);
    }

    public List<String> importStudents(String filename) {
        return state.importStudents(filename);
    }

    public boolean exportStudents(String filename) {
        return state.exportStudents(filename);
    }

    public List<Student> getAllStudents() {
        return state.getAllStudents();
    }

    public boolean addProfessor(Professor p) {
        return state.addProfessor(p);
    }

    public List<String> importProfessors(String filename) {
        return state.importProfessors(filename);
    }

    public boolean exportProfessors(String filename) {
        return state.exportProfessors(filename);
    }

    public List<Professor> getAllProfessors() {
        return state.getAllProfessors();
    }

    public int addInternship(Internship it) {
        return state.addInternship(it);
    }

    public List<String> importInternships(String filename) {
        return state.importInternships(filename);
    }

    public boolean exportInternships(String filename) {
        return state.exportInternships(filename);
    }

    public List<Internship> getAllInternships() {
        return state.getAllInternships();
    }

    public void manageCandidacy() {
        state.manageCandidacy();
    }

    public List<Candidacy> getAllCandidaciesClone() {
        return state.getAllCandidaciesClone();
    }

    public int addCandidacy(Candidacy c) {
        return state.addCandidacy(c);
    }

    public List<String> importCandidacies(String filename) {
        return state.importCandidacies(filename);
    }

    public boolean exportCandidacies(String filename) {
        return state.exportCandidacies(filename);
    }

    public List<Student> getStudentsWithAutoProposedInternship() {
        return state.getStudentsWithAutoProposedInternship();
    }

    public List<Student> getStudentsWithCandidacy() {
        return state.getStudentsWithCandidacy();
    }

    public List<Student> getStudentsWithoutCandidacy() {
        return state.getStudentsWithoutCandidacy();
    }

    public List<Internship> getInternshipsWithFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {
        return state.getInternshipsWithFilter(filter1, filter2, filter3, filter4);
    }

    /*public void internshipAssignment() {
        state.internshipAssignment();
    }*/

    public List<Student> getAllStudentsInternship(int filter) {
        return state.getAllStudentsInternship(filter);
    }

    public List<String> getOrderedCandidacies(Student s) {
        return state.getOrderedCandidacies(s);
    }

    public List<Internship> getAllInternshipsWithFilters(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {
        return state.getAllInternshipsWithFilters(filter1, filter2, filter3, filter4);
    }

    public boolean autoInternshipAssignment1() {
        return state.autoInternshipAssignment1();
    }

    public int autoInternshipAssignment2() {
        return state.autoInternshipAssignment2();
    }

    public List<Student> getStudentsInDrawCondition() {
        return state.getStudentsInDrawCondition();
    }

    public List<Internship> getFirstAndSecondStudentInternships(Student s) {
        return data.getFirstAndSecondStudentInternships(s);
    }

    public boolean associateStudentWithInternship(Student s, Internship it) {
        //caretaker.save();
        return state.associateStudentWithInternship(s, it);
    }

    public boolean removeAssociationStudentInternship(Student s) {
        return state.removeAssociationStudentInternship(s);
    }

    public List<Internship> getFreeInternships(Student s) {
        return state.getFreeInternships(s);
    }

    public boolean closeInternshipState() {
        return state.closeInternshipState();
    }

    /*public void manualInternshipAssignment() {
        state.manualInternshipAssignment();
    }*/

    public List<Student> getFreeStudents() {
        return state.getFreeStudents();
    }

    public List<Student> getAssociatedStudents() {
        return state.getAssociatedStudents();
    }

    public boolean removeEveryInternshipAssociation() {
        return state.removeEveryInternshipAssociation();
    }

    /*public void professorAssignment() {
        state.professorAssignment();
    }*/

    public boolean autoProfessorAssignment() {
        return state.autoProfessorAssignment();
    }

    /*public void manualProfessorAssignment() {
        state.manualProfessorAssignment();
    }*/

    public boolean removeProfessorAssociation(Professor p) {
        return state.removeProfessorAssociation(p);
    }

    public List<Student> getStudentsWithInternshipAndProfessor() {
        return state.getStudentsWithInternshipAndProfessor();
    }

    public List<Student> getStudentsWithInternshipAndWithoutProfessor() {
        return state.getStudentsWithInternshipAndWithoutProfessor();
    }

    public List<String> getStatsPerProfessor() {
        return state.getStatsPerProfessor();
    }

    public List<Professor> getFreeProfessors() {
        return state.getFreeProfessors();
    }

    public List<Professor> getAssociatedProfessors() {
        return state.getAssociatedProfessors();
    }

    public List<Internship> getInternshipsWithoutProfessor() {
        return state.getInternshipsWithoutProfessor();
    }

    public boolean manualProfessorAssociation(Internship it, Professor p) {
        //caretaker.save();
        return state.manualProfessorAssociation(it, p);
    }

    /*public void search() {
        state.search();
    }*/

    public List<Student> getStudentsWithInternship() {
        return state.getStudentsWithInternship();
    }

    public List<Student> getStudentsWithoutInternshipAndWithCandidacy() {
        return state.getStudentsWithoutInternshipAndWithCandidacy();
    }

    public List<Internship> getAvailableInternship() {
        return state.getAvailableInternship();
    }

    public List<Internship> getAttributedInternship() {
        return state.getAttributedInternship();
    }

    public boolean exportState3(String filename) {
        return state.exportState3(filename);
    }

    public boolean exportState4and5(String filename) {
        return state.exportState4and5(filename);
    }

    public boolean exportAll(String filename) {
        return state.exportAll(filename);
    }

    public boolean importAll(String filename) {
        return state.importAll(filename);
    }

    // GUI

    public List<String> getInfo() {
        return state.getInfo();
    }

    public void deleteStudent(Student s) {
        state.deleteStudent(s);
    }

    public void deleteProfessor(Professor p) {
        state.deleteProfessor(p);
    }

    public void deleteInternship(Internship it) {
        state.deleteInternship(it);
    }

    public void deleteCandidacy(Candidacy c) {
        state.deleteCandidacy(c);
    }

    public String separateDataStudents(String dataFromFile) {
        return state.separateDataStudents(dataFromFile);
    }

    public String separateDataProfessors(String dataFromFile) {
        return state.separateDataProfessors(dataFromFile);
    }

    public String separateDataInternships(String dataFromFile) {
        return state.separateDataInternships(dataFromFile);
    }

    public String separateDataCandidacies(String dataFromFile) {
        return state.separateDataCandidacies(dataFromFile);
    }

    public Double getDAstats() {
        return state.getDAstats();
    }

    public Double getSIstats() {
        return state.getSIstats();
    }

    public Double getRASstats() {
        return state.getRASstats();
    }

    public List<Professor> getProfessorsStats() {
        return state.getProfessorsStats();
    }

}
