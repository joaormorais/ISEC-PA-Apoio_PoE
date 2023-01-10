package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Student;

import java.util.List;

public class ManageStudentsState extends PoEManagementStateAdapter {

    // Constructor

    ManageStudentsState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.MANAGE_STUDENTS;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.CONFIGURATION, data);
    }

    @Override
    public int addStudent(Student s) {

        return data.addStudent(s);

    }

    @Override
    public boolean exportStudents(String filename) {

        return data.exportStudents(filename);

    }

    @Override
    public List<Student> getAllStudents() {

        return data.getAllStudentsClone();

    }

    @Override
    public List<String> importStudents(String filename) {
        return data.importStudents(filename);
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
    public boolean isFlagClosedConfiguration() {
        return data.isFlagClosedConfiguration();
    }

    @Override
    public void setFlagClosedConfiguration(boolean flagClosedConfiguration) {
        data.setFlagClosedConfiguration(flagClosedConfiguration);
    }

    // flags management (end)

    @Override
    public List<String> getInfo() {
        return data.getInfo();
    }

    @Override
    public void deleteStudent(Student s) {
        data.deleteStudent(s);
    }

    @Override
    public String separateDataStudents(String dataFromFile) {
        return data.separateDataStudents(dataFromFile);
    }
}
