package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;

import java.util.List;

class WaitStartState extends PoEManagementStateAdapter {

    // Constructor

    WaitStartState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.WAIT_START;
    }

    /*@Override
    public void start() {
        changeState(new ConfigurationState(context, data));
    }*/

    @Override
    public void goFoward() {
        changeState(PoEManagementState.CONFIGURATION, data);
    }

    @Override
    public boolean exportAll(String filename) {
        return data.exportAll(filename);
    }

    @Override
    public boolean importAll(String filename) {
        return data.importAll(filename);
    }

    @Override
    public boolean isFlag() {
        return data.isFlag();
    }

    @Override
    public void setFlag(boolean flag) {
        data.setFlag(flag);
    }

    @Override
    public List<String> getInfo() {
        return data.getInfo();
    }
}
