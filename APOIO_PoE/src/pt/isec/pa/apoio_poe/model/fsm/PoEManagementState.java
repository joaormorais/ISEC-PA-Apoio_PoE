package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEManagementData;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementContext;


public enum PoEManagementState {
    WAIT_START, CONFIGURATION, MANAGE_STUDENTS, MANAGE_PROFESSORS, MANAGE_INTERNSHIP, MANAGE_CANDIDACY, INTERNSHIP_ASSIGNMENT, MANUAL_INTERNSHIP_ASSIGNMENT, PROFESSOR_ASSIGNMENT, MANUAL_PROFESSOR_ASSIGNMENT, SEARCH;

    public IPoEManagementState createState(PoEManagementContext context, PoEManagementData data){
        return switch(this){
            case WAIT_START -> new WaitStartState(context,data);
            case CONFIGURATION -> new ConfigurationState(context,data);
            case MANAGE_STUDENTS -> new ManageStudentsState(context,data);
            case MANAGE_PROFESSORS -> new ManageProfessorsState(context,data);
            case MANAGE_INTERNSHIP -> new ManageInternshipState(context,data);
            case MANAGE_CANDIDACY -> new ManageCandidacyState(context,data);
            case INTERNSHIP_ASSIGNMENT -> new InternshipAssignmentState(context,data);
            case MANUAL_INTERNSHIP_ASSIGNMENT -> new ManualInternshipAssignmentState(context,data);
            case PROFESSOR_ASSIGNMENT -> new ProfessorAssignmentState(context,data);
            case MANUAL_PROFESSOR_ASSIGNMENT -> new ManualProfessorAssignmentState(context,data);
            case SEARCH -> new SearchState(context,data);
        };
    }

}
