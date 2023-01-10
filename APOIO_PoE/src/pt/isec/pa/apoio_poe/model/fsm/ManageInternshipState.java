package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;

public class ManageInternshipState extends PoEManagementStateAdapter {

    // Constructor

    ManageInternshipState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.MANAGE_INTERNSHIP;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.CONFIGURATION, data);
    }

    @Override
    public int addInternship(Internship it) {

        return data.addInternship(it);

    }

    @Override
    public boolean exportInternships(String filename) {

        return data.exportInternships(filename);

    }

    @Override
    public List<Internship> getAllInternships() {

        return data.getAllInternshipsClone();

    }

    @Override
    public List<String> importInternships(String filename) {
        return data.importInternships(filename);
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
    public void deleteInternship(Internship it) {
        data.deleteInternship(it);
    }

    @Override
    public String separateDataInternships(String dataFromFile) {
        return data.separateDataInternships(dataFromFile);
    }
}
