package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;

import java.util.List;

class ConfigurationState extends PoEManagementStateAdapter {

    // Constructor

    ConfigurationState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.CONFIGURATION;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.WAIT_START, data);
    }

    @Override
    public boolean closeConfiguration() {

        return data.closeConfiguration();

    }

    @Override
    public void manageStudents() {
        changeState(PoEManagementState.MANAGE_STUDENTS, data);
    }

    @Override
    public void manageProfessors() {
        changeState(PoEManagementState.MANAGE_PROFESSORS, data);
    }

    @Override
    public void manageInternship() {
        changeState(PoEManagementState.MANAGE_INTERNSHIP, data);
    }

    /*@Override
    public void manageCandidacy() {
        changeState(PoEManagementState.MANAGE_CANDIDACY, data);
    }*/

    @Override
    public void goFoward() {
        changeState(PoEManagementState.MANAGE_CANDIDACY, data);
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

}
