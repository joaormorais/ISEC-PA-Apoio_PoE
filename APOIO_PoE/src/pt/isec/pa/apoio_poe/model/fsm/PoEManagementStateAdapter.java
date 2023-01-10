package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.io.Serializable;
import java.util.List;

abstract class PoEManagementStateAdapter implements IPoEManagementState, Serializable {
    protected PoEManagementContext context; // general context of the Finite-State Machine
    protected PoEManagementData data;

    // Constructor

    public PoEManagementStateAdapter(PoEManagementContext context, PoEManagementData data) {
        this.context = context;
        this.data = data;
    }

    // Change between states

    final protected void changeState(PoEManagementState newState,PoEManagementData data) {
        context.changeState(newState,data);
    }

    @Override
    public void goFoward() {

    }

    @Override
    public void manualFoward() {

    }

    // Interface functions

    // flags management (start)

    @Override
    public boolean isFlag() {
        return false;
    }

    @Override
    public void setFlag(boolean flag) {

    }

    @Override
    public boolean isFlagClosedConfiguration() {
        return false;
    }

    @Override
    public void setFlagClosedConfiguration(boolean flagClosedConfiguration) {

    }

    @Override
    public boolean isFlagClosedCandidacyConfiguration() {
        return false;
    }

    @Override
    public void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration) {

    }

    @Override
    public boolean isFlagClosedInternshipAssignment() {
        return false;
    }

    @Override
    public void setFlagClosedInternshipAssignment(boolean flagClosedInternshipAssignment) {

    }

    // flags management (end)

    /*@Override
    public void start() {
    }*/

    @Override
    public void goBack() {
    }

    @Override
    public PoEManagementState getState() {
        return null;
    }

    @Override
    public boolean closeConfiguration() {
        return false;
    }

    @Override
    public void manageStudents() {

    }

    @Override
    public int addStudent(Student s) {
        return 0;
    }

    @Override
    public List<String> importStudents(String filename) {
        return null;
    }

    @Override
    public boolean exportStudents(String filename) {
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public void manageProfessors() {

    }

    @Override
    public boolean addProfessor(Professor p) {
        return false;
    }

    @Override
    public List<String> importProfessors(String filename) {
        return null;
    }

    @Override
    public boolean exportProfessors(String filename) {
        return false;
    }

    @Override
    public List<Professor> getAllProfessors() {
        return null;
    }

    @Override
    public void manageInternship() {

    }

    @Override
    public int addInternship(Internship it) {
        return 0;
    }

    @Override
    public List<String> importInternships(String filename) {
        return null;
    }

    @Override
    public boolean exportInternships(String filename) {
        return false;
    }

    @Override
    public List<Internship> getAllInternships() {
        return null;
    }

    @Override
    public void manageCandidacy() {

    }

    @Override
    public List<Candidacy> getAllCandidaciesClone() {
        return null;
    }

    @Override
    public int addCandidacy(Candidacy c) {
        return 0;
    }

    @Override
    public List<String> importCandidacies(String filename) {
        return null;
    }

    @Override
    public boolean exportCandidacies(String filename) {
        return false;
    }

    @Override
    public List<Student> getStudentsWithAutoProposedInternship() {
        return null;
    }

    @Override
    public List<Student> getStudentsWithCandidacy() {
        return null;
    }

    @Override
    public List<Student> getStudentsWithoutCandidacy() {
        return null;
    }

    @Override
    public List<Internship> getInternshipsWithFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {
        return null;
    }

    /*@Override
    public void internshipAssignment() {

    }*/

    @Override
    public List<Student> getAllStudentsInternship(int filter) {
        return null;
    }

    @Override
    public List<String> getOrderedCandidacies(Student s) {
        return null;
    }

    @Override
    public List<Internship> getAllInternshipsWithFilters(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {
        return null;
    }

    @Override
    public boolean autoInternshipAssignment1() {
        return false;
    }

    @Override
    public int autoInternshipAssignment2() {
        return 0;
    }

    @Override
    public List<Student> getStudentsInDrawCondition() {
        return null;
    }

    @Override
    public boolean associateStudentWithInternship(Student s, Internship it) {
        return false;
    }

    @Override
    public boolean removeAssociationStudentInternship(Student s) {
        return false;
    }

    @Override
    public List<Internship> getFreeInternships(Student s) {
        return null;
    }

    @Override
    public boolean closeInternshipState() {
        return false;
    }

    /*@Override
    public void manualInternshipAssignment() {

    }*/

    @Override
    public List<Student> getFreeStudents() {
        return null;
    }

    @Override
    public List<Student> getAssociatedStudents() {
        return null;
    }

    @Override
    public boolean removeEveryInternshipAssociation() {
        return false;
    }

    /*@Override
    public void professorAssignment() {

    }*/

    @Override
    public boolean autoProfessorAssignment() {
        return false;
    }

    /*@Override
    public void manualProfessorAssignment() {

    }*/

    @Override
    public boolean removeProfessorAssociation(Professor p) {
        return false;
    }

    @Override
    public List<Student> getStudentsWithInternshipAndProfessor() {
        return null;
    }

    @Override
    public List<Student> getStudentsWithInternshipAndWithoutProfessor() {
        return null;
    }

    @Override
    public List<String> getStatsPerProfessor() {
        return null;
    }

    @Override
    public List<Professor> getFreeProfessors() {
        return null;
    }

    @Override
    public List<Professor> getAssociatedProfessors() {
        return null;
    }

    @Override
    public List<Internship> getInternshipsWithoutProfessor() {
        return null;
    }

    @Override
    public boolean manualProfessorAssociation(Internship it, Professor p) {
        return false;
    }

    /*@Override
    public void search() {

    }*/

    @Override
    public List<Student> getStudentsWithInternship() {
        return null;
    }

    @Override
    public List<Student> getStudentsWithoutInternshipAndWithCandidacy() {
        return null;
    }

    @Override
    public List<Internship> getAvailableInternship() {
        return null;
    }

    @Override
    public List<Internship> getAttributedInternship() {
        return null;
    }

    @Override
    public boolean exportState3(String filename) {
        return false;
    }

    @Override
    public boolean exportState4and5(String filename) {
        return false;
    }

    @Override
    public boolean exportAll(String filename) {
        return false;
    }

    @Override
    public boolean importAll(String filename) {
        return false;
    }

    // GUI

    @Override
    public List<String> getInfo() {
        return null;
    }

    @Override
    public void deleteStudent(Student s) {

    }

    @Override
    public void deleteProfessor(Professor p) {

    }

    @Override
    public void deleteInternship(Internship it) {

    }

    @Override
    public void deleteCandidacy(Candidacy c) {

    }

    @Override
    public String separateDataStudents(String dataFromFile) {
        return null;
    }

    @Override
    public String separateDataProfessors(String dataFromFile) {
        return null;
    }

    @Override
    public String separateDataInternships(String dataFromFile) {
        return null;
    }

    @Override
    public String separateDataCandidacies(String dataFromFile) {
        return null;
    }

    @Override
    public Double getDAstats() {
        return null;
    }

    @Override
    public Double getSIstats() {
        return null;
    }

    @Override
    public Double getRASstats() {
        return null;
    }

    @Override
    public List<Professor> getProfessorsStats() {
        return null;
    }
}
