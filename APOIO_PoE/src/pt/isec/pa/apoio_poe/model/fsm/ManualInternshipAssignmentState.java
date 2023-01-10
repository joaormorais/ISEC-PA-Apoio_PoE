package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;

class ManualInternshipAssignmentState extends PoEManagementStateAdapter {

    // Constructor

    public ManualInternshipAssignmentState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.MANUAL_INTERNSHIP_ASSIGNMENT;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.INTERNSHIP_ASSIGNMENT, data);
    }

    @Override
    public List<Student> getStudentsInDrawCondition() {

        return data.getStudentsInDrawCondition();

    }

    @Override
    public List<Internship> getFreeInternships(Student s) {

        return data.getFreeInternships(s);

    }

    @Override
    public boolean associateStudentWithInternship(Student s, Internship it) {

        return data.associateStudentWithInternship(s, it);

    }

    @Override
    public boolean removeAssociationStudentInternship(Student s) {

        return data.removeAssociationStudentInternship(s);
    }

    @Override
    public List<Student> getFreeStudents() {
        return data.getFreeStudents();
    }

    @Override
    public List<Student> getAssociatedStudents() {
        return data.getAssociatedStudents();
    }

    @Override
    public boolean removeEveryInternshipAssociation() {
        return data.removeEveryInternshipAssociation();
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
