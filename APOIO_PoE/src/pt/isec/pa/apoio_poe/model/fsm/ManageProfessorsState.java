package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ManageProfessorsState extends PoEManagementStateAdapter {

    // Constructor

    ManageProfessorsState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.MANAGE_PROFESSORS;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.CONFIGURATION, data);
    }

    @Override
    public boolean addProfessor(Professor p) {

        return data.addProfessor(p);

    }

    @Override
    public boolean exportProfessors(String filename) {

        return data.exportProfessors(filename);

    }

    @Override
    public List<Professor> getAllProfessors() {

        return data.getAllProfessorsClone();

    }

    @Override
    public List<String> importProfessors(String filename) {
        return data.importProfessors(filename);
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
    public void deleteProfessor(Professor p) {
        data.deleteProfessor(p);
    }

    @Override
    public String separateDataProfessors(String dataFromFile) {
        return data.separateDataProfessors(dataFromFile);
    }
}
