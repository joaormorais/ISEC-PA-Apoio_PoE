package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Student;

import java.util.List;


class ProfessorAssignmentState extends PoEManagementStateAdapter {

    // Constructor

    public ProfessorAssignmentState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.PROFESSOR_ASSIGNMENT;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.INTERNSHIP_ASSIGNMENT, data);
    }

    @Override
    public void manualFoward() {
        changeState(PoEManagementState.MANUAL_PROFESSOR_ASSIGNMENT, data);
    }

    @Override
    public void goFoward() {
        changeState(PoEManagementState.SEARCH, data);
    }

    @Override
    public boolean autoProfessorAssignment() {

        return data.autoProfessorAssignment();

    }

    @Override
    public List<Student> getStudentsWithInternshipAndProfessor() {
        return data.getStudentsWithInternshipAndProfessor();
    }

    @Override
    public List<Student> getStudentsWithInternshipAndWithoutProfessor() {
        return data.getStudentsWithInternshipAndWithoutProfessor();
    }

    @Override
    public List<String> getStatsPerProfessor() {
        return data.getStatsPerProfessor();
    }

    @Override
    public boolean exportState4and5(String filename) {
        return data.exportState4and5(filename);
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
