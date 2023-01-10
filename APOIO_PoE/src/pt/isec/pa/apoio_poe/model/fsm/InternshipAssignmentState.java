package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;

class InternshipAssignmentState extends PoEManagementStateAdapter {

    // Constructor

    public InternshipAssignmentState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.INTERNSHIP_ASSIGNMENT;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.MANAGE_CANDIDACY, data);
    }

    @Override
    public void manualFoward() {
        changeState(PoEManagementState.MANUAL_INTERNSHIP_ASSIGNMENT, data);
    }

    @Override
    public void goFoward() {
        changeState(PoEManagementState.PROFESSOR_ASSIGNMENT, data);
    }

    @Override
    public boolean autoInternshipAssignment1() {

        return data.autoInternshipAssignment1();

    }

    @Override
    public int autoInternshipAssignment2() {

        return data.autoInternshipAssignment2();

    }

    @Override
    public List<Student> getAllStudentsInternship(int filter) {

        return data.getAllStudentsInternship(filter);
    }

    @Override
    public List<String> getOrderedCandidacies(Student s) {
        return data.getOrderedCandidacies(s);
    }

    @Override
    public List<Internship> getAllInternshipsWithFilters(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {

        return data.getAllInternshipsWithFilters(filter1, filter2, filter3, filter4);

    }

    @Override
    public boolean closeInternshipState() {
        return data.closeInternshipState();
    }

    @Override
    public boolean exportState3(String filename) {
        return data.exportState3(filename);
    }

    // flags management (start)

    @Override
    public boolean isFlag() {
        return data.isFlag();
    }

    @Override
    public void setFlag(boolean flag) {
        data.setFlag(flag);
    }

    @Override
    public boolean isFlagClosedCandidacyConfiguration() {
        return data.isFlagClosedCandidacyConfiguration();
    }

    @Override
    public void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration) {
        data.setFlagClosedCandidacyConfiguration(flagClosedCandidacyConfiguration);
    }

    @Override
    public boolean isFlagClosedInternshipAssignment() {
        return data.isFlagClosedInternshipAssignment();
    }

    @Override
    public void setFlagClosedInternshipAssignment(boolean flagClosedInternshipAssignment) {
        data.setFlagClosedInternshipAssignment(flagClosedInternshipAssignment);
    }

// flags management (end)

    @Override
    public List<String> getInfo() {
        return data.getInfo();
    }

}
