package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;

import java.util.List;

class SearchState extends PoEManagementStateAdapter {

    // Constructor

    public SearchState(PoEManagementContext context, PoEManagementData data) {
        super(context, data);
    }

    // Methods

    @Override
    public PoEManagementState getState() {
        return PoEManagementState.SEARCH;
    }

    @Override
    public List<Student> getStudentsWithInternship() {

        return data.getStudentsWithInternship();

    }

    @Override
    public List<Student> getStudentsWithoutInternshipAndWithCandidacy() {

        return data.getStudentsWithoutInternshipAndWithCandidacy();

    }

    @Override
    public List<Internship> getAvailableInternship() {

        return data.getAvailableInternship();

    }

    @Override
    public List<Internship> getAttributedInternship() {

        return data.getAttributedInternship();

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

    // flags management (end)

    @Override
    public List<String> getInfo() {
        return data.getInfo();
    }

    @Override
    public Double getDAstats() {
        return data.getDAstats();
    }

    @Override
    public Double getSIstats() {
        return data.getSIstats();
    }

    @Override
    public Double getRASstats() {
        return data.getRASstats();
    }

    @Override
    public List<Professor> getProfessorsStats() {
        return data.getProfessorsStats();
    }

    @Override
    public List<Student> getAllStudents() {
        return data.getAllStudentsClone();
    }

    @Override
    public List<Internship> getAllInternships() {
        return data.getAllInternshipsClone();
    }
}
