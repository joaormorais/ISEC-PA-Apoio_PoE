package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;


public class ManageCandidacyState extends PoEManagementStateAdapter {

    // Constructor

    ManageCandidacyState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.MANAGE_CANDIDACY;
    }

    @Override
    public void goBack() {
        changeState(PoEManagementState.CONFIGURATION, data);
    }

    @Override
    public int addCandidacy(Candidacy c) {

        return data.addCandidacy(c);

    }

    @Override
    public boolean exportCandidacies(String filename) {

        return data.exportCandidacies(filename);

    }

    @Override
    public List<Student> getStudentsWithAutoProposedInternship() {

        return data.getStudentsWithAutoProposedInternship();

    }

    @Override
    public List<Student> getStudentsWithCandidacy() {

        return data.getStudentsWithCandidacy();

    }

    @Override
    public List<Student> getStudentsWithoutCandidacy() {

        return data.getStudentsWithoutCandidacy();

    }

    @Override
    public List<Internship> getInternshipsWithFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4) {

        return data.getInternshipsWithFilter(filter1, filter2, filter3, filter4);

    }

    @Override
    public void goFoward() {
        changeState(PoEManagementState.INTERNSHIP_ASSIGNMENT, data);
    }

    @Override
    public List<Candidacy> getAllCandidaciesClone() {
        return data.getAllCandidaciesClone();
    }

    @Override
    public List<String> importCandidacies(String filename) {
        return data.importCandidacies(filename);
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

    @Override
    public boolean isFlagClosedCandidacyConfiguration() {
        return data.isFlagClosedCandidacyConfiguration();
    }

    @Override
    public void setFlagClosedCandidacyConfiguration(boolean flagClosedCandidacyConfiguration) {
        data.setFlagClosedCandidacyConfiguration(flagClosedCandidacyConfiguration);
    }

    // flags management (end)

    @Override
    public List<String> getInfo() {
        return data.getInfo();
    }

    @Override
    public void deleteCandidacy(Candidacy c) {
        data.deleteCandidacy(c);
    }

    @Override
    public String separateDataCandidacies(String dataFromFile) {
        return data.separateDataCandidacies(dataFromFile);
    }
}
