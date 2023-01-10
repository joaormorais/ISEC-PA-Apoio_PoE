package pt.isec.pa.apoio_poe.model;

import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementContext;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;
import pt.isec.pa.apoio_poe.model.memento.IOriginator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;

public class PoEManager {
    PoEManagementContext fsm;
    PropertyChangeSupport pcs;

    public PoEManager() {
        this.fsm = new PoEManagementContext();
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * updates the program
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) { // ver melhor isto
        pcs.addPropertyChangeListener(listener);
    }

    /**
     *
     * @return actual state
     */
    public PoEManagementState getState() { // returns the actual state
        return fsm.getState();
    }

    // Undo and Redo (start) --------------------------------------

    /*@Override
    public IMemento save() {
        return null;
    }

    @Override
    public void restore(IMemento memento) {

    }*/

    // Undo and Redo (end) --------------------------------------

    // FLAGS to manage the program (start) --------------------------------------

    /**
     *
     * @return true/false
     */
    public boolean isFlagClosedConfiguration() { // flag to understand if the configuration state is closed or not
        return fsm.isFlagClosedConfiguration();
    }

    /**
     * Changes the flag that indicates if the state CONFIGURATION is closed
     * @param flagClosedConfiguration
     */
    public void setFlagClosedConfiguration(boolean flagClosedConfiguration) {
        //save();
        fsm.setFlagClosedConfiguration(flagClosedConfiguration);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     *
     * @return true/false
     */
    public boolean isFlagClosedCandidacyConfiguration() {
        return fsm.isFlagClosedCandidacyConfiguration();
    }

    /**
     * Changes the flag that indicates if the state MANAGE_CANDIDACY is closed
     * @param flagClosedCandidacyConfiguration
     */
    public void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration) {
        //save();
        fsm.setFlagClosedCandidacyConfiguration(flagClosedCandidacyConfiguration);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     *
     * @return tru/false
     */
    public boolean isFlagClosedInternshipAssignment() {
        return fsm.isFlagClosedInternshipAssignment();
    }

    /**
     * Changes the flag that indicates if the state INTERNSHIP_ASSIGNMENT is closed
     * @param flagClosedInternshipAssignment
     */
    public void setFlagClosedInternshipAssignment(boolean flagClosedInternshipAssignment) {
        //save();
        fsm.setFlagClosedInternshipAssignment(flagClosedInternshipAssignment);
        pcs.firePropertyChange(null, null, null);
    }

    // FLAGS to manage the program (end) --------------------------------------

    /**
     *
     * @return various information about the data
     */
    public List<String> getInfo() {
        return fsm.getInfo();
    }

    /**
     * Starts the "data"
     */
    public void init() {
        this.fsm = new PoEManagementContext();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Goes to the previous state
     */
    public void goBack() { // goes to the previous state
        //save();
        fsm.goBack();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Goes to the next state
     */
    public void goFoward() { // goes to the next state
        //save();
        fsm.goFoward();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Goes either to the state MANUAL_INTERNSHIP_ASSIGNMENT or MANUAL_PROFESSOR_ASSIGNMENT
     */
    public void manualFoward() {
        //save();
        fsm.manualFoward();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Load data from a DAT file
     * @param file
     * @return
     */
    public boolean loadData(File file) { // load a .dat file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            fsm = (PoEManagementContext) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //save();
        pcs.firePropertyChange(null, null, null);
        return true;
    }

    /**
     * Saves the actual state of the program in a DAT file
     * @param file
     * @return true/false
     */
    public boolean saveData(File file) { // saves the actual state of the program in a .dat file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(fsm);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Goes to the state MANAGESTUDENTS
     */
    public void manageStudents() { // goes to the state MANAGE_STUDENTS (in the state: CONFIGURATION, we can go the 4 different states)
        //save();
        fsm.manageStudents();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Goes to the state MANAGEPROFESSORS
     */
    public void manageProfessors() { // goes to the state MANAGE_PROFESSORS (in the state: CONFIGURATION, we can go the 4 different states)
        //save();
        fsm.manageProfessors();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Goes to the state MANAGEINTERNSHIP
     */
    public void manageInternship() { // goes to the state MANAGE_INTERNSHIPS (in the state: CONFIGURATION, we can go the 4 different states)
        //save();
        fsm.manageInternship();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Closes the state of configuration
     * @return true/false
     */
    public boolean closeConfiguration() {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.closeConfiguration();
    }

    /**
     *
     * @return every student as a clone
     */
    public List<Student> getAllStudents() {
        return fsm.getAllStudents();
    }

    /**
     * Imports data from a CSV to the professor data
     * @param filename
     * @return error/result
     */
    public List<String> importStudents(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.importStudents(filename);
    }

    /**
     * Exports to a CSV file the data from every student
     * @param filename
     * @return true/false
     */
    public boolean exportStudents(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.exportStudents(filename);
    }

    /**
     * Imports data from a CSV to the professor data
     * @param filename
     * @return error/result
     */
    public List<String> importProfessors(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.importProfessors(filename);
    }

    /**
     * Exports to a CSV file the data from every professor
     * @param filename
     * @return true/false
     */
    public boolean exportProfessors(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.exportProfessors(filename);
    }

    /**
     *
     * @return every professor as a clone
     */
    public List<Professor> getAllProfessors() {
        return fsm.getAllProfessors();
    }

    /**
     * Imports data from a CSV to the internship data
     * @param filename
     * @return
     */
    public List<String> importInternships(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.importInternships(filename);
    }

    /**
     * Exports to a CSV file the data from every internship
     * @param filename
     * @return true/false
     */
    public boolean exportInternships(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.exportInternships(filename);
    }

    /**
     *
     * @return every internship
     */
    public List<Internship> getAllInternships() {
        return fsm.getAllInternships();
    }

    /**
     * Imports data from a CSV to the candidacy data
     * @param filename
     * @return error/result
     */
    public List<String> importCandidacies(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.importCandidacies(filename);
    }

    /**
     * Exports to a CSV file the data from every candidacy
     * @param filename
     * @return
     */
    public boolean exportCandidacies(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.exportCandidacies(filename);
    }

    /**
     *
     * @return every candidacies as a clone
     */
    public List<Candidacy> getAllCandidaciesClone() {
        return fsm.getAllCandidaciesClone();
    }

    /**
     *
     * @return every student with an auto proposed internship (T3)
     */
    public List<Student> getStudentsWithAutoProposedInternship() {
        return fsm.getStudentsWithAutoProposedInternship();
    }

    /**
     *
     * @return every student with candidacy
     */
    public List<Student> getStudentsWithCandidacy() {
        return fsm.getStudentsWithCandidacy();
    }

    /**
     *
     * @return every stuudent without candidacy
     */
    public List<Student> getStudentsWithoutCandidacy() {
        return fsm.getStudentsWithoutCandidacy();
    }

    /**
     *
     * @param filter1
     * @param filter2
     * @param filter3
     * @param filter4
     * @return every internship depending on filters
     */
    public List<Internship> getInternshipsWithFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {
        return fsm.getInternshipsWithFilter(filter1, filter2, filter3, filter4);
    }

    /**
     * Closes the Internship association state
     * @return true/false
     */
    public boolean closeInternshipState() {
        return fsm.closeInternshipState();
    }

    /**
     *
     * @param filter1
     * @param filter2
     * @param filter3
     * @param filter4
     * @return every internship depending on filters
     */
    public List<Internship> getAllInternshipsWithFilters(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {
        return fsm.getAllInternshipsWithFilters(filter1, filter2, filter3, filter4);
    }

    /**
     *
     * @param filter
     * @return every student depending on a filter
     */
    public List<Student> getAllStudentsInternship(int filter) {
        return fsm.getAllStudentsInternship(filter);
    }

    /**
     * Exports to a CSV file the data presented in state 3 (internship association)
     * @param filename
     * @return true/false
     */
    public boolean exportState3(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.exportState3(filename);
    }

    /**
     * Does an association between a student and an internship (automatically)
     * @return error/result
     */
    public boolean autoInternshipAssignment1() {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.autoInternshipAssignment1();
    }

    /**
     * Does an association between a student and an internship (automatically)
     * @return error/result
     */
    public int autoInternshipAssignment2() {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.autoInternshipAssignment2();
    }

    /**
     *
     * @return every student not associated to an internship
     */
    public List<Student> getFreeStudents() {
        return fsm.getFreeStudents();
    }

    /**
     *
     * @param s
     * @return every internship not associated to a student
     */
    public List<Internship> getFreeInternships(Student s) {
        return fsm.getFreeInternships(s);
    }

    /**
     * Does the association between a student and an internship
     * @param s
     * @param it
     * @return true/false
     */
    public boolean associateStudentWithInternship(Student s, Internship it) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.associateStudentWithInternship(s, it);
    }

    /**
     *
     * @return students that are in a draw condition (same attributes)
     */
    public List<Student> getStudentsInDrawCondition() {
        return fsm.getStudentsInDrawCondition();
    }

    /**
     *
     * @param s
     * @return every internship that the student has proposed to
     */
    public List<Internship> getFirstAndSecondStudentInternships(Student s) {
        return fsm.getFirstAndSecondStudentInternships(s);
    }

    /**
     * Exports to a CSV file the data presented in both state 4(professor association) and 5(data search)
     * @param filename
     * @return true/false
     */
    public boolean exportState4and5(String filename) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.exportState4and5(filename);
    }

    /**
     *
     * @return every student with an internship and professor associated
     */
    public List<Student> getStudentsWithInternshipAndProfessor() {
        return fsm.getStudentsWithInternshipAndProfessor();
    }

    /**
     *
     * @return every student associated to an internship but without a professor associated to that internship
     */
    public List<Student> getStudentsWithInternshipAndWithoutProfessor() {
        return fsm.getStudentsWithInternshipAndWithoutProfessor();
    }

    /**
     * Makes associations between professors and internships that have a responsible professor
     * @return true/false
     */
    public boolean autoProfessorAssignment() {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.autoProfessorAssignment();
    }

    /**
     *
     * @return every professor that hasn't an internship associated to him
     */
    public List<Professor> getFreeProfessors() {
        return fsm.getFreeProfessors();
    }

    /**
     *
     * @return every internship without a professor associated to it
     */
    public List<Internship> getInternshipsWithoutProfessor() {
        return fsm.getInternshipsWithoutProfessor();
    }

    /**
     * Associates a professor to an internship
     * @param it
     * @param p
     * @return true/false
     */
    public boolean manualProfessorAssociation(Internship it, Professor p) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.manualProfessorAssociation(it, p);
    }

    /**
     *
     * @return every student that has an internship associated to him
     */
    public List<Student> getStudentsWithInternship() {
        return fsm.getStudentsWithInternship();
    }

    /**
     *
     * @return every student with candidacy but without an internship associated
     */
    public List<Student> getStudentsWithoutInternshipAndWithCandidacy() {
        return fsm.getStudentsWithoutInternshipAndWithCandidacy();
    }

    /**
     *
     * @return every internship available to be associated to someone
     */
    public List<Internship> getAvailableInternship() {
        return fsm.getAvailableInternship();
    }

    /**
     *
     * @return every internship already associated to a student
     */
    public List<Internship> getAttributedInternship() {
        return fsm.getAttributedInternship();
    }

    /**
     *
     * @return every students associated to an internship
     */
    public List<Student> getAssociatedStudents() {
        return fsm.getAssociatedStudents();
    }

    /**
     *
     * @return every professor that is associated to at least one internship
     */
    public List<Professor> getAssociatedProfessors() {
        return fsm.getAssociatedProfessors();
    }

    /**
     * Removes the association between a student and an internship
     * @param s
     * @return true/false
     */
    public boolean removeAssociationStudentInternship(Student s) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.removeAssociationStudentInternship(s);
    }

    /**
     * Removes the association between a professor and an internship
     * @param p
     * @return true/false
     */
    public boolean removeProfessorAssociation(Professor p) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.removeProfessorAssociation(p);
    }

    /**
     * Deletes the student sent by argument (if it exists in the data)
     * @param s
     */
    public void deleteStudent(Student s) {
        //save();
        fsm.deleteStudent(s);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Deletes the professor sent by argument (if it exists in the data)
     * @param p
     */
    public void deleteProfessor(Professor p) {
        //save();
        fsm.deleteProfessor(p);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Deletes the internship sent by argument (if it exists in the data)
     * @param it
     */
    public void deleteInternship(Internship it) {
        //save();
        fsm.deleteInternship(it);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Deletes the candidacy sent by argument (if it exists in the data)
     * @param c
     */
    public void deleteCandidacy(Candidacy c) {
        //save();
        fsm.deleteCandidacy(c);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * Converts a String into a student and add it to the list of students
     * @param dataFromFile
     * @return String with a error/result
     */
    public String separateDataStudents(String dataFromFile) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.separateDataStudents(dataFromFile);
    }

    /**
     * Converts a String into a professor and add it to the list of professors
     * @param dataFromFile
     * @return String with a error/result
     */
    public String separateDataProfessors(String dataFromFile) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.separateDataProfessors(dataFromFile);
    }

    /**
     * Converts a String into an internship and add it to the list of internships
     * @param dataFromFile
     * @return String with a error/result
     */
    public String separateDataInternships(String dataFromFile) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.separateDataInternships(dataFromFile);
    }

    /**
     * Converts a String into a candidacy and add it to the list of candidacies
     * @param dataFromFile
     * @return String with a error/result
     */
    public String separateDataCandidacies(String dataFromFile) {
        //save();
        pcs.firePropertyChange(null, null, null);
        return fsm.separateDataCandidacies(dataFromFile);
    }

    /**
     *
     * @return amount of internships with "DA" as a branch
     */
    public Double getDAstats() {
        return fsm.getDAstats();
    }

    /**
     *
     * @return amount of internships with "SI" as a branch
     */
    public Double getSIstats() {
        return fsm.getSIstats();
    }

    /**
     *
     * @return amount of internships with "RAS" as a branch
     */
    public Double getRASstats() {
        return fsm.getRASstats();
    }

    /**
     * Sorts every professor by the amount of time he is associated to an internship (top5)
     * @return top5 professors with the most associations
     */
    public List<Professor> getProfessorsStats() {
        return fsm.getProfessorsStats();
    }

}
