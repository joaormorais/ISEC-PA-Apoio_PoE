package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;

class ManualProfessorAssignmentState extends PoEManagementStateAdapter {

    // Constructor

    public ManualProfessorAssignmentState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    /// Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.MANUAL_PROFESSOR_ASSIGNMENT;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.PROFESSOR_ASSIGNMENT, data);
    }

    @Override
    public List<Professor> getFreeProfessors() {
        return data.getFreeProfessors();
    }

    @Override
    public List<Professor> getAssociatedProfessors() {
        return data.getAssociatedProfessors();
    }

    @Override
    public List<Internship> getInternshipsWithoutProfessor() {
        return data.getInternshipsWithoutProfessor();
    }

    @Override
    public boolean manualProfessorAssociation(Internship it, Professor p) {
        return data.manualProfessorAssociation(it, p);
    }

    @Override
    public boolean removeProfessorAssociation(Professor p) {
        return data.removeProfessorAssociation(p);
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

    // flags management (end)

    @Override
    public List<String> getInfo() {
        return data.getInfo();
    }

}
