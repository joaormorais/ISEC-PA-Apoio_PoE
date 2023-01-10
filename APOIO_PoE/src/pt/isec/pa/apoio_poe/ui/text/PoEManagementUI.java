package pt.isec.pa.apoio_poe.ui.text;

import java.util.List;

import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementContext;

import java.util.Objects;
import java.util.Scanner;

public class PoEManagementUI {
    private PoEManagementContext fsm;
    /*private boolean flag = false;
    private boolean flagClosedConfiguration = false;
    private boolean flagClosedCandidacyConfiguration = false;
    private boolean flagClosedInternshipAssignment = false;*/

    // Constructor

    public PoEManagementUI(PoEManagementContext fsm) {
        this.fsm = fsm;
    }

    // Functions

    public void run() {

        // in the first run, the state is: WAIT_START

        do {

            switch (fsm.getState()) {

                case WAIT_START -> waitStart();
                case CONFIGURATION -> configuration();
                case MANAGE_STUDENTS -> studentsConfiguration();
                case MANAGE_PROFESSORS -> professorsConfiguration();
                case MANAGE_INTERNSHIP -> internshipConfiguration();
                case MANAGE_CANDIDACY -> candidacyConfiguration();
                case INTERNSHIP_ASSIGNMENT -> internshipAssignmentConfiguration();
                case MANUAL_INTERNSHIP_ASSIGNMENT -> manualInternshipAssignmentConfiguration();
                case PROFESSOR_ASSIGNMENT -> professorAssignmentConfiguration();
                case MANUAL_PROFESSOR_ASSIGNMENT -> manualProfessorAssignmentConfiguration();
                case SEARCH -> search();

            }

            System.out.println("Debug - Current State: " + fsm.getState());

        } while (!fsm.isFlag());

    }

    public void waitStart() {

        int choice;

        System.out.println("""
                 ____   ___     ___      ___ ___   ____  ____    ____   ____    ___  ___ ___    ___  ____   ______\s
                |    \\ /   \\   /  _]    |   |   | /    ||    \\  /    | /    |  /  _]|   |   |  /  _]|    \\ |      |
                |  o  )     | /  [_     | _   _ ||  o  ||  _  ||  o  ||   __| /  [_ | _   _ | /  [_ |  _  ||      |
                |   _/|  O  ||    _]    |  \\_/  ||     ||  |  ||     ||  |  ||    _]|  \\_/  ||    _]|  |  ||_|  |_|
                |  |  |     ||   [_     |   |   ||  _  ||  |  ||  _  ||  |_ ||   [_ |   |   ||   [_ |  |  |  |  | \s
                |  |  |     ||     |    |   |   ||  |  ||  |  ||  |  ||     ||     ||   |   ||     ||  |  |  |  | \s
                |__|   \\___/ |_____|    |___|___||__|__||__|__||__|__||___,_||_____||___|___||_____||__|__|  |__|""");

        System.out.print("\n\n-----------------------\n1 - Iniciar aplicação\n2 - Salvar estado atual\n3 - Carregar dados a partir de um ficheiro\n0 - Sair\n-----------------------\n-> ");

        do {
            choice = readInt();
        } while (choice < 0 || choice > 3);


        switch (choice) {
            case 1 -> //fsm.start(); // change state to CONFIGURATION
                    fsm.goFoward();

            case 2 -> {
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename;
                filename = readString();
                System.out.println();

                if (!fsm.exportAll(filename))
                    System.out.println("Não existem dados para exportar!");
                else
                    System.out.println("Estado atual guardado!");
            }

            case 3 -> {
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename2;
                filename2 = readString();
                System.out.println();

                if (!fsm.importAll(filename2))
                    System.out.println("Não existem dados para importar!");
                else
                    System.out.println("Dados importados!");
            }

            case 0 -> fsm.setFlag(true);

        }

    }

    // configuration UI (start) --------------

    public void configuration() {

        int choice = -1;

        if (!fsm.isFlagClosedConfiguration())  // if the configuration state isn't closed
            System.out.print("-----------------------\n1 - Configurar alunos\n2 - Configurar docentes\n3 - Configurar estágios/projetos\n4 - Avançar sem fechar fase atual\n5 - Fechar fase e avançar\n0 - Voltar\n-----------------------\n-> ");
        else
            System.out.print("-----------------------\n1 - Consultar alunos\n2 - Consultar docentes\n3 - Consultar estágios/projetos\n4 - Retornar à fase de configuração de candidaturas\n0 - Voltar\n-----------------------\n-> ");

        do {
            choice = readInt();
        } while (choice < 0 || choice > 5);

        switch (choice) {
            case 1 -> fsm.manageStudents(); // change state do MANAGE_STUDENTS

            case 2 -> fsm.manageProfessors(); // change state do MANAGE_PROFESSORS

            case 3 -> fsm.manageInternship(); // change state do MANAGE_INTERNSHIPS

            case 4 -> //fsm.manageCandidacy(); // change state to MANAGE_CANDIDACY
                        fsm.goFoward(); // change state to MANAGE_CANDIDACY

            case 5 -> {
                if (!fsm.closeConfiguration()) // closes state CONFIGURATION
                    System.out.println(("Não é possível fechar este estado. O número total de propostas para cada ramo não é superior ou igual ao número de alunos"));
                else {
                    fsm.setFlagClosedConfiguration(true);
                    //fsm.manageCandidacy(); // change state to MANAGE_CANDIDACY
                    fsm.goFoward(); // change state to MANAGE_CANDIDACY
                }

            }

            case 0 -> fsm.goBack(); // change state do WAIT_START

        }

    }

    public void studentsConfiguration() {

        if (fsm.isFlagClosedConfiguration()) { // if the configuration state is closed, the user can only see the data
            if (!showStudents())
                System.out.println("Não existem estudantes no sistema!");
            fsm.goBack(); // change state do CONFIGURATION
            return;
        }

        int choice = -1;

        while (choice != 0) {

            System.out.print("-----------------------\n1 - Consultar alunos\n2 - Importar alunos através de um ficheiro CSV\n3 - Exportar alunos para um ficheiro CSV\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 3);

            switch (choice) {

                //case 1 -> addStudentsUI();

                //case 2 -> deleteStudentsUI();

                //case 3 -> editStudentsUI();

                case 1:
                    if (!showStudents())
                        System.out.println("Não existem estudantes no sistema!");
                    break;

                case 2:
                    System.out.print("Intoduza o nome do ficheiro: ");
                    String filename;
                    filename = readString();
                    System.out.println();

                    for (String i : fsm.importStudents(filename))
                        System.out.println(Objects.requireNonNullElse(i, "Erro a ler o ficheiro .csv"));

                    break;

                case 3:
                    System.out.print("Intoduza o nome do ficheiro: ");
                    String filename2;
                    filename2 = readString();
                    System.out.println();

                    if (!fsm.exportStudents(filename2))
                        System.out.println("Não existem dados para exportar!");
                    else
                        System.out.println("Alunos exportados com sucesso!");

                    break;

                case 0:
                    fsm.goBack(); // change state do CONFIGURATION
                    break;

            }
        }

    }

    public void professorsConfiguration() {

        if (fsm.isFlagClosedConfiguration()) { // if the configuration state is closed, the user can only see the data
            if (!showProfessors())
                System.out.println("Não existem professores no sistema!");
            fsm.goBack(); // change state do CONFIGURATION
            return;
        }

        int choice = -1;

        while (choice != 0) {

            System.out.print("-----------------------\n1 - Consultar docentes\n2 - Importar professores através de um ficheiro CSV\n3 - Exportar professores para um ficheiro CSV\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 3);

            switch (choice) {

                //case 1 -> addProfessorsUI();

                //case 2 -> deleteProfessorsUI();

                //case 3 -> editProfessorsUI();

                case 1:
                    if (!showProfessors())
                        System.out.println("Não existem professores no sistema!");
                    break;

                case 2:
                    System.out.print("Intoduza o nome do ficheiro: ");
                    String filename;
                    filename = readString();
                    System.out.println();

                    for (String i : fsm.importProfessors(filename))
                        System.out.println(Objects.requireNonNullElse(i, "Erro a ler o ficheiro .csv"));

                    break;

                case 3:
                    System.out.print("Intoduza o nome do ficheiro: ");
                    String filename2;
                    filename2 = readString();
                    System.out.println();

                    if (!fsm.exportProfessors(filename2))
                        System.out.println("Não existem dados para exportar!");
                    else
                        System.out.println("Professores exportados com sucesso!");

                    break;

                case 0:
                    fsm.goBack(); // change state do CONFIGURATION
                    break;

            }

        }

    }

    public void internshipConfiguration() {

        if (fsm.isFlagClosedConfiguration()) { // if the configuration state is closed, the user can only see the data
            if (!showInternships())
                System.out.println("Não existem estágios/projetos no sistema!");
            fsm.goBack(); // change state do CONFIGURATION
            return;
        }

        int choice = -1;

        while (choice != 0) {

            System.out.print("-----------------------\n1 - Consultar estágios/projetos\n2 - Importar estágios/projetos através de um ficheiro CSV\n3 - Exportar estágios/projetos para um ficheiro CSV\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 3);

            switch (choice) {

                //case 1 -> addInternshipsUI();

                //case 2 -> deleteInternshipsUI();

                //case 3 -> editInternshipsUI();

                case 1:
                    if (!showInternships())
                        System.out.println("Não existem estágios/projetos no sistema!");
                    break;

                case 2:
                    System.out.print("Intoduza o nome do ficheiro: ");
                    String filename;
                    filename = readString();
                    System.out.println();

                    for (String i : fsm.importInternships(filename))
                        System.out.println(Objects.requireNonNullElse(i, "Erro a ler o ficheiro .csv"));
                    break;

                case 3:
                    System.out.print("Intoduza o nome do ficheiro: ");
                    String filename2;
                    filename2 = readString();
                    System.out.println();

                    if (!fsm.exportInternships(filename2))
                        System.out.println("Não existem dados para exportar!");
                    else
                        System.out.println("Estágios/projetos exportados com sucesso!");

                    break;

                case 0:
                    fsm.goBack(); // change state do CONFIGURATION
                    break;

            }

        }

    }

    public void candidacyConfiguration() {

        int choice;

        if (!fsm.isFlagClosedCandidacyConfiguration()) {

            System.out.print("-----------------------\n1 - Consultar candidaturas\n2 - Consultar alunos com autoproposta\n3 - Consultar alunos com candidatura já registada\n4 - Consultar alunos sem candidatura registada\n5 - Consultar listas de estágios/projetos\n6 - Avançar sem fechar fase atual\n7 - Fechar fase e avançar\n8 - Importar candidaturas através de um ficheiro CSV\n9 - Exportar candidaturas para um ficheiro CSV\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 9);

        } else {

            System.out.print("-----------------------\n1 - Consultar candidaturas\n2 - Consultar alunos com autoproposta\n3 - Consultar alunos com candidatura já registada\n4 - Consultar alunos sem candidatura registada\n5 - Consultar listas de estágios/projetos\n6 - Retornar à fase de atribuição de propostas\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 6);

        }


        switch (choice) {

            case 1:
                if (!showCandidacies())
                    System.out.println("Não existem candidaturas no sistema!");
                break;

            case 8:
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename;
                filename = readString();
                System.out.println();

                for (String i : fsm.importCandidacies(filename))
                    System.out.println(Objects.requireNonNullElse(i, "Erro a ler o ficheiro .csv"));

                break;

            case 9:
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename2;
                filename2 = readString();
                System.out.println();

                if (!fsm.exportCandidacies(filename2))
                    System.out.println("Não existem dados para exportar!");
                else
                    System.out.println("Candidaturas exportadas com sucesso!");

                break;

            case 2:
                List<Student> auxPrintStudents = fsm.getStudentsWithAutoProposedInternship();
                if (!auxPrintStudents.isEmpty()) {
                    System.out.println("Lista de alunos com autopropostas associadas: ");
                    for (Student i : auxPrintStudents)
                        System.out.println(i);
                } else
                    System.out.println("Não existem alunos com autopropostas associadas!");

                break;

            case 3:
                List<Student> auxPrintStudents2 = fsm.getStudentsWithCandidacy();
                if (!auxPrintStudents2.isEmpty()) {
                    System.out.println("Lista de alunos com candidaturas registadas: ");
                    for (Student i : auxPrintStudents2)
                        System.out.println(i);
                } else
                    System.out.println("Não existem alunos com candidaturas registadas!");

                break;

            case 4:

                List<Student> auxPrintStudents3 = fsm.getStudentsWithoutCandidacy();
                if (!auxPrintStudents3.isEmpty()) {
                    System.out.println("Lista de alunos sem candidaturas registadas: ");
                    for (Student i : auxPrintStudents3)
                        System.out.println(i);
                } else
                    System.out.println("Não existem alunos sem candidaturas registadas!");

                break;

            case 5:

                int allowFilters;
                System.out.println("-----------------------\nDeseja usar filtos?\n1 - Sim\n2 - Não");
                do {
                    allowFilters = readInt();
                } while (allowFilters > 2 || allowFilters < 1);

                if (allowFilters == 1) {

                    int filterAutoProposedProject, filterProfessorsProposal, filterWithCandidacies, filterWithoutCandidacies;
                    boolean filter1 = false, filter2 = false, filter3 = false, filter4 = false;

                    System.out.println("Deseja filtrar por autopropostas de alunos?\n1 - Sim\n2 - Não");
                    do {
                        filterAutoProposedProject = readInt();
                    } while (filterAutoProposedProject > 2 || filterAutoProposedProject < 1);
                    if (filterAutoProposedProject == 1)
                        filter1 = true;

                    System.out.println("Deseja filtrar por propostas de docentes?\n1 - Sim\n2 - Não");
                    do {
                        filterProfessorsProposal = readInt();
                    } while (filterProfessorsProposal > 2 || filterProfessorsProposal < 1);
                    if (filterProfessorsProposal == 1)
                        filter2 = true;

                    System.out.println("Deseja filtrar por propostas com candidaturas?\n1 - Sim\n2 - Não");
                    do {
                        filterWithCandidacies = readInt();
                    } while (filterWithCandidacies > 2 || filterWithCandidacies < 1);
                    if (filterWithCandidacies == 1)
                        filter3 = true;

                    System.out.println("Deseja filtrar por propostas sem candidaturas?\n1 - Sim\n2 - Não");
                    do {
                        filterWithoutCandidacies = readInt();
                    } while (filterWithoutCandidacies > 2 || filterWithoutCandidacies < 1);
                    if (filterWithoutCandidacies == 1)
                        filter4 = true;

                    List<Internship> filteredInternships = fsm.getInternshipsWithFilter(filter1, filter2, filter3, filter4);

                    if (!filteredInternships.isEmpty()) {
                        System.out.println("-----------------------\nLista de estágios/projetos com os filtros aplicados:");
                        for (Internship i : filteredInternships)
                            System.out.println(i);
                    } else
                        System.out.println("Não existem estágios/projetos com estes filtros aplicados!");

                } else {

                    // List<Internship> notFilteredInternships = fsm.getAllInternships(); não posso chamar uma função de um estado diferente
                    List<Internship> notFilteredInternships = fsm.getInternshipsWithFilter(false, false, false, false);

                    if (!notFilteredInternships.isEmpty()) {
                        System.out.println("-----------------------\nLista de estágios/projetos sem filtros aplicados:");
                        for (Internship i : notFilteredInternships)
                            System.out.println(i);
                    } else
                        System.out.println("Não existem estágios/projetos!");

                }

                break;

            case 6:
                //fsm.internshipAssignment(); // change state to INTERNSHIP_ASSIGNMENT
                fsm.goFoward();
                break;

            case 7:
                if (fsm.isFlagClosedConfiguration()) { // closes state MANAGE_CANDIDACY
                    fsm.setFlagClosedCandidacyConfiguration(true);
                    //fsm.internshipAssignment(); // change state to INTERNSHIP_ASSIGNMENT
                    fsm.goFoward();
                } else
                    System.out.println("Não é possível fechar esta fase porque a anterior ainda se encontra aberta!");

                break;

            case 0:
                if (fsm.isFlagClosedConfiguration())
                    System.out.println("Esta fase encontra-se fechada pelo que só poderá consultar os seus dados e não efetuar alterações!");

                fsm.goBack(); // change state do CONFIGURATION

                break;

        }
    }

    public void internshipAssignmentConfiguration() {

        int answer, choice;

        if (!fsm.isFlagClosedInternshipAssignment()) {

            System.out.println("Deseja que o programa faça atribuições automáticas entre Aluno e Estágio/Proposta?\n1 - Sim\n2 - Não");

            do {
                answer = readInt();
            } while (answer < 1 || answer > 2);

            if (answer == 1)
                if (!fsm.isFlagClosedCandidacyConfiguration()) { // this is the only automatic attribution that happens when the previous state is open

                    if (!fsm.autoInternshipAssignment1())
                        System.out.println("Não foram atribuidas propostas de estágios/projetos a alunos automaticamente!");
                    else
                        System.out.println("Foram atribuidas propostas de estágios/projetos a alunos automaticamente!");

                } else {

                    boolean returnFirstAttribution = fsm.autoInternshipAssignment1();
                    int returnSecondAttribution = fsm.autoInternshipAssignment2();

                    if (!returnFirstAttribution && returnSecondAttribution == -1)
                        System.out.println("Não foram atribuidas propostas de estágios/projetos a alunos automaticamente!");
                    else if (returnSecondAttribution == -2) {
                        System.out.println("Surgiu um critério de empate entre dois alunos!");
                        //fsm.manualInternshipAssignment();
                        fsm.manualFoward();
                        return;
                    }

                    if (returnFirstAttribution || returnSecondAttribution == 1)
                        System.out.println("Foram atribuidas propostas de estágios/projetos a alunos automaticamente!");

                }

            System.out.print("-----------------------\n1 - Atribuição manual de propostas de estágio/projeto\n2 - Consultar alunos\n3 - Consultar estágios/pojetos\n4 - Avançar sem fechar fase atual\n5 - Fechar fase e avançar\n6 - Exportar dados de alunos para ficheiro CSV\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 6);

        } else {

            System.out.print("-----------------------\n1 - Consultar alunos\n2 - Consultar estágios/pojetos\n3 - Retornar à fase de atribuição de docentes\n0 - Voltar\n-----------------------\n-> ");

            do {
                choice = readInt();
            } while (choice < 0 || choice > 3);

            if (choice != 0)
                choice++;

        }

        switch (choice) {

            case 1:
                //fsm.manualInternshipAssignment();
                fsm.manualFoward();
                break;

            case 2:
                int filter;

                System.out.println("Que filtro deseja aplicar?\n1 - Alunos com autoproposta associada\n2 - Alunos com candidatura já registada\n3 - Alunos com proposta atribuída\n4 - Alunos sem qualquer proposta atribuída");

                do {
                    filter = readInt();
                } while (filter < 1 || filter > 4);

                List<Student> studentsWithFilters = fsm.getAllStudentsInternship(filter);

                if (studentsWithFilters.isEmpty())
                    System.out.println("Não existem alunos segundo os filtros! (ter autoproposta registada / ter candidatura registada / ter autoproposta atribuída / não ter qualquer proposta atribuída)");
                else {
                    if (filter == 3) {
                        int n = 1;
                        for (Student i : studentsWithFilters) {
                            System.out.print(n++ + " - ");
                            System.out.println(i);
                            List<String> orderedCandidaciesByPreference = fsm.getOrderedCandidacies(i);
                            System.out.println("Lista de candidaturas do estudante " + i.getName() + ":" + orderedCandidaciesByPreference);
                        }
                    } else {
                        int n = 1;
                        for (Student i : studentsWithFilters) {
                            System.out.print(n++ + " - ");
                            System.out.println(i);
                        }
                    }
                }

                break;

            case 3:

                int allowFilters;
                System.out.println("-----------------------\nDeseja usar filtos?\n1 - Sim\n2 - Não");
                do {
                    allowFilters = readInt();
                } while (allowFilters > 2 || allowFilters < 1);

                if (allowFilters == 1) {

                    int filterAutoProposedProject, filterProfessorsProposal, filterFreeProposals, filterAssociatedProposals;
                    boolean filter1 = false, filter2 = false, filter3 = false, filter4 = false;

                    System.out.println("Deseja filtrar por autopropostas de alunos?\n1 - Sim\n2 - Não");
                    do {
                        filterAutoProposedProject = readInt();
                    } while (filterAutoProposedProject > 2 || filterAutoProposedProject < 1);
                    if (filterAutoProposedProject == 1)
                        filter1 = true;

                    System.out.println("Deseja filtrar por propostas de docentes?\n1 - Sim\n2 - Não");
                    do {
                        filterProfessorsProposal = readInt();
                    } while (filterProfessorsProposal > 2 || filterProfessorsProposal < 1);
                    if (filterProfessorsProposal == 1)
                        filter2 = true;

                    System.out.println("Deseja filtrar por propostas disponíveis?\n1 - Sim\n2 - Não");
                    do {
                        filterFreeProposals = readInt();
                    } while (filterFreeProposals > 2 || filterFreeProposals < 1);
                    if (filterFreeProposals == 1)
                        filter3 = true;

                    System.out.println("Deseja filtrar por propostas atribuídas?\n1 - Sim\n2 - Não");
                    do {
                        filterAssociatedProposals = readInt();
                    } while (filterAssociatedProposals > 2 || filterAssociatedProposals < 1);
                    if (filterAssociatedProposals == 1)
                        filter4 = true;

                    List<Internship> filteredInternships = fsm.getAllInternshipsWithFilters(filter1, filter2, filter3, filter4);

                    if (!filteredInternships.isEmpty()) {
                        System.out.println("-----------------------\nLista de estágios/projetos com os filtros aplicados:");
                        for (Internship i : filteredInternships)
                            System.out.println(i);
                    } else
                        System.out.println("Não existem estágios/projetos com estes filtros aplicados!");

                } else {

                    List<Internship> notFilteredInternships = fsm.getAllInternshipsWithFilters(false, false, false, false);

                    if (!notFilteredInternships.isEmpty()) {
                        System.out.println("-----------------------\nLista de estágios/projetos sem filtros aplicados:");
                        for (Internship i : notFilteredInternships)
                            System.out.println(i);
                    } else
                        System.out.println("Não existem estágios/projetos!");

                }

                break;

            case 4:
                //fsm.professorAssignment(); // change state to PROFESSOR_ASSIGNMENT
                fsm.goFoward();
                break;

            case 5:
                if (fsm.closeInternshipState()) {
                    if (fsm.isFlagClosedCandidacyConfiguration()) { // closes state MANAGE_CANDIDACY
                        fsm.setFlagClosedInternshipAssignment(true);
                        //fsm.professorAssignment(); // change state to PROFESSOR_ASSIGNMENT
                        fsm.goFoward();
                    } else
                        System.out.println("Não é possível fechar esta fase porque a anterior ainda se encontra aberta!");
                } else {
                    System.out.println("Não é possível fechar esta fase porque nem todos os alunos com candidaturas registadas têm propostas atribuídas!");
                }

                break;

            case 6:
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename;
                filename = readString();
                System.out.println();

                if (!fsm.exportState3(filename))
                    System.out.println("Não existem dados para exportar!");
                else
                    System.out.println("Dados sobre alunos exportados com sucesso!");

                break;

            case 0:
                if (fsm.isFlagClosedCandidacyConfiguration())
                    System.out.println("Esta fase encontra-se fechada pelo que só poderá consultar os seus dados e não efetuar alterações!");

                fsm.goBack(); // change state do CONFIGURATION

                break;

        }

    }

    public void manualInternshipAssignmentConfiguration() {

        // verifying if we are coming to this function because we have a draw condition

        List<Student> drawStudents = fsm.getStudentsInDrawCondition();

        if (drawStudents != null) { // we have a draw condition if != null

            int n = 1, nI1 = 1, nI2 = 1, n2 = 1, student, internship;

            System.out.println("Alunos que se encontram em fator de empate:");

            for (Student i : drawStudents) {
                System.out.print(n++ + " - ");
                System.out.println(i);
            }

            List<Internship> firstStudentInternships = fsm.getFirstAndSecondStudentInternships(drawStudents.get(0));
            System.out.println("\nEstágios/projetos a que o primeiro aluno de candidatou:");
            for (Internship i : firstStudentInternships) {
                System.out.print(nI1++ + " - ");
                System.out.println(i);
            }

            List<Internship> secondStudentInternships = fsm.getFirstAndSecondStudentInternships(drawStudents.get(1));
            System.out.println("\nEstágios/projetos a que o segundo aluno de candidatou:");
            for (Internship i : secondStudentInternships) {
                System.out.print(nI2++ + " - ");
                System.out.println(i);
            }

            System.out.println("\nA qual aluno deseja atribuir um estágio?");

            do {
                student = readInt();
            } while (student < 1 || student > 2);

            System.out.println("\nQue estágio deseja atribuir a esse aluno?\n(encontram-se apresentados apenas as propostas em que o aluno tem condições de entrar)");

            List<Internship> freeInternships = fsm.getFreeInternships(drawStudents.get(student - 1)); // we send the student so we receive every internship available FOR HIM

            if (!freeInternships.isEmpty()) {
                for (Internship i : freeInternships) {
                    System.out.print(n2++ + " - ");
                    System.out.println(i);
                }
            } else {
                System.out.println("\nNão existem estágios/projetos disponíveis para este aluno!");
                //fsm.internshipAssignment();
                fsm.goFoward();
                return;
            }

            System.out.print("\nEscolha o estágio a atribuir ao aluno:\n0 - Voltar\n--> ");

            do {
                internship = readInt();
            } while (internship < 0 || internship > freeInternships.size());

            if (internship == 0) {
                //fsm.internshipAssignment();
                fsm.goFoward();
                return;
            }

            if (fsm.associateStudentWithInternship(drawStudents.get(student - 1), freeInternships.get(internship - 1)))
                System.out.println("\nFase de desempate concluída!");
            else
                System.out.println("\nNão foi possível fazer essa atribbuição! [ERRO]");

            fsm.goBack();
            return;

        }

        // normal function of this function

        System.out.print("-----------------------\n1 - Atribuir estágio/projeto a aluno\n2 - Remover estágio/projeto a aluno\n3 - Remover todas as associações exceto as já previamente definidas\n0 - Voltar\n-----------------------\n-> ");

        int choice;

        do {
            choice = readInt();
        } while (choice < 0 || choice > 3);

        switch (choice) {

            case 1:

                int nFree = 1, n = 1, student, internship;

                List<Student> getFreeStudents = fsm.getFreeStudents();
                System.out.println("\nLista de alunos sem estágios/projetos associados:");
                for (Student i : getFreeStudents) {
                    System.out.print(nFree++ + " - ");
                    System.out.println(i);
                }

                System.out.println("\nA qual aluno deseja atribuir um estágio/projeto?");

                do {
                    student = readInt();
                } while (student < 1 || student > getFreeStudents.size());

                System.out.println("\nQue estágio deseja atribuir a esse aluno?\n(encontram-se apresentados apenas as propostas em que o aluno tem condições de entrar)");

                List<Internship> freeInternships = fsm.getFreeInternships(getFreeStudents.get(student - 1)); // we send the student so we receive every internship available FOR HIM

                if (!freeInternships.isEmpty()) {
                    for (Internship i : freeInternships) {
                        System.out.print(n++ + " - ");
                        System.out.println(i);
                    }
                } else {
                    System.out.println("\nNão existem estágios/projetos disponíveis para este aluno!");
                    break;
                }

                System.out.print("\nEscolha o estágio a atribuir ao aluno:\n0 - Voltar\n--> ");

                do {
                    internship = readInt();
                } while (internship < 0 || internship > freeInternships.size());

                if (internship == 0)
                    break;

                if (fsm.associateStudentWithInternship(getFreeStudents.get(student - 1), freeInternships.get(internship - 1)))
                    System.out.println("\nAtribuição concluída!");
                else
                    System.out.println("\nNão foi possível fazer essa atribuição! [ERRO]");

                break;

            case 2:

                int nAssociated = 1, student2;

                List<Student> getAssociatedStudents = fsm.getAssociatedStudents();
                System.out.println("\nLista de alunos com estágios/projetos associados:");
                for (Student i : getAssociatedStudents) {
                    System.out.print(nAssociated++ + " - ");
                    System.out.println(i);
                }

                System.out.println("\nA qual aluno deseja remover um estágio/projeto?");

                do {
                    student2 = readInt();
                } while (student2 < 1 || student2 > getAssociatedStudents.size());

                if (fsm.removeAssociationStudentInternship(getAssociatedStudents.get(student2 - 1)))
                    System.out.println("\nRemoção de associação concluída!");
                else
                    System.out.println("\nNão foi possível fazer essa remoção! [ERRO]");

                break;

            case 3:
                if (fsm.removeEveryInternshipAssociation())
                    System.out.println("\nForam removidas associações!");
                else
                    System.out.println("\nNão existiam associações com a possibilidade de serem removidas!");
                break;

            case 0:
                fsm.goBack();
                break;

        }

    }

    public void professorAssignmentConfiguration() {

        System.out.println("Deseja que o programa faça atribuições automáticas entre Professor e Estágio/Proposta?\n1 - Sim\n2 - Não");

        int answer;

        do {
            answer = readInt();
        } while (answer < 1 || answer > 2);

        if (answer == 1)
            if (!fsm.autoProfessorAssignment())
                System.out.println("Não foram atribuidos professores a estágios/projetos automaticamente!");
            else
                System.out.println("Foram atribuidos professores a estágios/projetos automaticamente!");

        int choice;

        System.out.print("-----------------------\n1 - Atribuição manual de docentes\n2 - Consultar dados diversos sobre atribuição de orientadores\n3 - Avançar (fecho automático da fase atual)\n4 - Exportar dados de alunos para ficheiro CSV\n0 - Voltar\n-----------------------\n-> ");

        do {
            choice = readInt();
        } while (choice < 0 || choice > 4);

        switch (choice) {

            case 1:
                //fsm.manualProfessorAssignment(); // changes state to MANUAL_PROFESSOR_ASSIGNMENT
                fsm.manualFoward();
                break;

            case 2:
                System.out.println("Deseja consultar:\n1 - Lista de estudantes com proposta atribuída e com orientador associado\n2 - lista de estudantes com proposta atribuída mas sem orientador associado\n3 - número de orientações por docente, em média, mínimo, máximo\n0 - Nenhum");
                int answer2;

                do {
                    answer2 = readInt();
                } while (answer2 < 0 || answer2 > 3);

                switch (answer2) {
                    case 1:
                        int n = 1;
                        List<Student> auxPrintStudent = fsm.getStudentsWithInternshipAndProfessor();

                        if (!auxPrintStudent.isEmpty()) {
                            for (Student i : auxPrintStudent) {
                                System.out.print(n++ + " - ");
                                System.out.println(i);
                            }
                        } else {
                            System.out.println("Não existem estudantes com estes requisittos!");
                        }

                        break;
                    case 2:
                        int n2 = 1;
                        List<Student> auxPrintStudent2 = fsm.getStudentsWithInternshipAndWithoutProfessor();

                        if (!auxPrintStudent2.isEmpty()) {
                            for (Student i : auxPrintStudent2) {
                                System.out.print(n2++ + " - ");
                                System.out.println(i);
                            }
                        } else {
                            System.out.println("Não existem estudantes com estes requisittos!");
                        }

                        break;
                    case 3:
                        System.out.println("----------------------- incompleto ----------------------");
                        break;

                    case 0:
                        break;
                }

                break;

            case 3:
                if (fsm.isFlagClosedInternshipAssignment()) {
                    System.out.println("Depois de avançar, não pode regressar a qualquer fase! Tem a certeza que quer continuar?\n1 - Sim\n2 - Não");
                    int sure;
                    do {
                        sure = readInt();
                    } while (sure > 2 || sure < 1);
                    if (sure == 1)
                        //fsm.search(); // change state to SEARCH
                        fsm.goFoward();
                } else
                    System.out.println("Não é possível avançar quando as fases anteriores não se encontram fechadas!");

                break;

            case 4:
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename;
                filename = readString();
                System.out.println();

                if (!fsm.exportState4and5(filename))
                    System.out.println("Não existem dados para exportar!");
                else
                    System.out.println("Dados sobre alunos exportados com sucesso!");

                break;

            case 0:
                if (fsm.isFlagClosedInternshipAssignment())
                    System.out.println("Esta fase encontra-se fechada pelo que só poderá consultar os seus dados e não efetuar alterações!");

                fsm.goBack(); // change state do CONFIGURATION

                break;

        }


    }

    public void manualProfessorAssignmentConfiguration() {

        System.out.print("-----------------------\n1 - Associar docente\n2 - Remover docente associado\n3 - Editar docente associado\n0 - Voltar\n-----------------------\n-> ");

        int choice;

        do {
            choice = readInt();
        } while (choice < 0 || choice > 3);

        switch (choice) {
            case 1 -> {
                int n = 1, professor;
                List<Professor> auxPrintProfessor = fsm.getFreeProfessors();
                System.out.println("Lista de professores que podem ser associados a um estágio/projeto:"); // afterall, every professor can be associated
                if (!auxPrintProfessor.isEmpty()) {
                    for (Professor i : auxPrintProfessor) {
                        System.out.print(n++ + " - ");
                        System.out.println(i);
                    }
                } else {
                    System.out.println("Não existem professores livres!");
                    break;
                }
                System.out.println("\nA qual professor deseja atribuir um estágio/projeto?");
                do {
                    professor = readInt();
                } while (professor < 1 || professor > auxPrintProfessor.size());
                int n2 = 1, internship;
                List<Internship> auxPrintInternship = fsm.getInternshipsWithoutProfessor();
                System.out.println("Lista de estágios/projetos sem professores associados:");
                if (!auxPrintInternship.isEmpty()) {
                    for (Internship i : auxPrintInternship) {
                        System.out.print(n2++ + " - ");
                        System.out.println(i);
                    }
                } else {
                    System.out.println("Não existem estágios/projetos sem professor associado!");
                    break;
                }
                System.out.println("\nQue estágio/projeto deseja atribuir?");
                do {
                    internship = readInt();
                } while (internship < 1 || internship > auxPrintInternship.size());
                if (fsm.manualProfessorAssociation(auxPrintInternship.get(internship - 1), auxPrintProfessor.get(professor - 1)))
                    System.out.println("Associação criada Professor - Estágio/Projeto");
                else
                    System.out.println("Erro a criar associação");
            }
            case 2 -> {
                int n3 = 1, professor2;
                List<Professor> auxPrintProfessor2 = fsm.getAssociatedProfessors();
                System.out.println("Lista de professores associados a um estágio/projeto:");
                if (!auxPrintProfessor2.isEmpty()) {
                    for (Professor i : auxPrintProfessor2) {
                        System.out.print(n3++ + " - ");
                        System.out.println(i);
                    }
                } else {
                    System.out.println("Não existem professores associados a estágios/projetos!");
                    break;
                }
                System.out.println("\nA que professor deseja remover a associação?");
                do {
                    professor2 = readInt();
                } while (professor2 < 1 || professor2 > auxPrintProfessor2.size());
                if (fsm.removeProfessorAssociation(auxPrintProfessor2.get(professor2 - 1)))
                    System.out.println("Associação removida Professor - Estágio/Projeto");
                else
                    System.out.println("Erro a remover associação");
            }
            case 3 -> System.out.println("NÃO DISPONÍVEL NA META 1!");
            case 0 -> //fsm.professorAssignment();
                    fsm.goFoward();
        }

    }

    public void search() {

        int choice;

        System.out.print("-----------------------\n1 - Consultar estudantes com propostas atribuídas\n2 - Consultar estudantes sem propostas atribuídas e com opções de candidatura\n3 - Consultar propostas disponíveis\n4 - Consultar propostas atribuídas\n5 - Consultar dados estatisticos sobre docentes\n6 - Exportar dados de alunos para ficheiro CSV\n7 - Salvar estado atual\n0 - Sair\n-----------------------\n-> ");

        do {
            choice = readInt();
        } while (choice < 0 || choice > 7);

        switch (choice) {
            case 1 -> {
                List<Student> auxPrintStudents = fsm.getStudentsWithInternship();
                int n = 1;

                if (auxPrintStudents.isEmpty()) {
                    System.out.println("Não existem estudantes com propostas atribuidas!");
                } else {
                    System.out.println("-----------------------\nLista de estudantes com propostas atribuidas: ");
                    for (Student i : auxPrintStudents) {
                        System.out.print(n++ + " - ");
                        System.out.println(i);
                    }
                }
            }
            case 2 -> {
                List<Student> auxPrintStudents2 = fsm.getStudentsWithoutInternshipAndWithCandidacy();
                int n2 = 1;

                if (auxPrintStudents2.isEmpty()) {
                    System.out.println("Não existem estudantes sem propostas atribuidas e com opções de candidatura!");
                } else {
                    System.out.println("-----------------------\nLista de estudantes sem propostas atribuidas e com opções de candidatura: ");
                    for (Student i : auxPrintStudents2) {
                        System.out.print(n2++ + " - ");
                        System.out.println(i);
                    }
                }
            }
            case 3 -> {
                List<Internship> auxPrintInternship = fsm.getAvailableInternship();
                int n3 = 1;

                if (auxPrintInternship.isEmpty()) {
                    System.out.println("Não existem propostas disponíveis!");
                } else {
                    System.out.println("-----------------------\nLista de propostas disponíveis: ");
                    for (Internship i : auxPrintInternship) {
                        System.out.print(n3++ + " - ");
                        System.out.println(i);
                    }
                }
            }
            case 4 -> {
                List<Internship> auxPrintInternship2 = fsm.getAttributedInternship();
                int n4 = 1;

                if (auxPrintInternship2.isEmpty()) {
                    System.out.println("Não existem propostas atribuídas!");
                } else {

                    for (Internship i : auxPrintInternship2) {
                        System.out.print(n4++ + " - ");
                        System.out.println(i);
                    }
                }
            }
            case 5 -> System.out.println("----------------------- incompleto ----------------------");

            case 6 -> {
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename;
                filename = readString();
                System.out.println();

                if (!fsm.exportState4and5(filename))
                    System.out.println("Não existem dados para exportar!");
                else
                    System.out.println("Dados sobre alunos exportados com sucesso!");
            }

            case 7 -> {
                System.out.print("Intoduza o nome do ficheiro: ");
                String filename2;
                filename2 = readString();
                System.out.println();

                if (!fsm.exportAll(filename2))
                    System.out.println("Não existem dados para exportar!");
                else
                    System.out.println("Estado atual guardado!");
            }

            case 0 -> fsm.setFlag(true);
        }

    }

    // configuration UI (end) --------------

    // show students + professors + internships UI (start) --------------

    public boolean showStudents() {

        List<Student> auxPrintStudents = fsm.getAllStudents();

        if (auxPrintStudents.isEmpty())
            return false;


        int n = 1;
        System.out.println("-----------------------\nLista de alunos: ");

        for (Student i : auxPrintStudents) {
            System.out.print(n++ + " - ");
            System.out.println(i);
        }

        return true;

    }

    public boolean showProfessors() {

        List<Professor> auxPrintProfessors = fsm.getAllProfessors();

        if (auxPrintProfessors.isEmpty())
            return false;


        int n = 1;
        System.out.println("-----------------------\nLista de professores: ");

        for (Professor i : auxPrintProfessors) {
            System.out.print(n++ + " - ");
            System.out.println(i);
        }

        return true;

    }

    public boolean showInternships() {

        List<Internship> auxPrintInternships = fsm.getAllInternships();

        if (auxPrintInternships == null)
            return false;


        int n = 1;
        System.out.println("-----------------------\nLista de estágios/projetos: ");

        for (Internship i : auxPrintInternships) {
            System.out.print(n++ + " - ");
            System.out.println(i);
        }

        return true;

    }

    public boolean showCandidacies() {

        List<Candidacy> auxCandidacies = fsm.getAllCandidaciesClone();

        if (auxCandidacies.isEmpty())
            return false;

        int n = 1;
        System.out.println("-----------------------\nLista de candidaturas: ");

        for (Candidacy i : auxCandidacies) {
            System.out.print(n++ + " - ");
            System.out.println(i);
        }

        return true;

    }

    // show students + professors + internships UI (end) --------------

    // import students + professors + internships UI (start) --------------


    // import students + professors + internships UI (end) --------------

    // read inputs (start) --------------

    public int readInt() {

        Scanner scan = new Scanner(System.in);

        while (!scan.hasNextInt())
            scan.next();

        return scan.nextInt();

    }

    public String readString() {

        Scanner scan = new Scanner(System.in);

        while (!scan.hasNextLine())
            scan.next();

        return scan.nextLine();

    }

    /*public long readLong() {

        Scanner scan = new Scanner(System.in);

        while (!scan.hasNextLong())
            scan.next();

        return scan.nextLong();

    }

    public double readDouble() {

        Scanner scan = new Scanner(System.in);

        while (!scan.hasNextDouble())
            scan.next();

        return scan.nextDouble();

    }

    public Professor readProfessor() {

        System.out.println("Que professor deseja atribuir? (nº 1, nº 2, nº 3....)");

        showProfessors();

        int answer;

        do {
            answer = readInt();
        } while (answer < 1 || answer > fsm.getData().getProfessors().size());

        return fsm.getData().getProfessors().get(answer - 1);

    }*/

    // read inputs (end) --------------

}
