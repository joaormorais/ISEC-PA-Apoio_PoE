package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ManualProfessorAssociationUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnRemove;
    ListView listFreeProfessorsInternships;
    VBox mainVB;
    Text consultingText;
    Professor professor;
    Internship internship;
    Boolean removing;

    public ManualProfessorAssociationUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        this.removing = false;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnBack = new Button("Retroceder");
        btnBack.setMinWidth(BTN_MINWIDTH);
        btnBack.setPrefSize(BTN_SIZE, BTN_SIZE);
        this.setLeft(btnBack);

        listFreeProfessorsInternships = new ListView();
        listFreeProfessorsInternships.setEditable(true);
        this.setBottom(listFreeProfessorsInternships);


        btnRemove = new Button("Remover atribuições");
        btnRemove.setMinWidth(BTN_MINWIDTH);
        btnRemove.setPrefSize(BTN_SIZE, BTN_SIZE);
        consultingText = new Text();
        consultingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 26));
        consultingText.setFill(Color.WHITE);
        consultingText.setStroke(Color.BLACK);
        consultingText.setStrokeWidth(1);
        consultingText.setText("Cique duas vezes num docente para associá-lo a um estágio/projeto disponível");
        mainVB = new VBox(btnRemove, consultingText);
        mainVB.setAlignment(Pos.CENTER);
        mainVB.setSpacing(10);
        this.setCenter(mainVB);

    }

    private void registerHandlers() {

        poeManager.addPropertyChangeListener(evt -> {
            update();
        });

        btnBack.setOnAction(event -> {
            poeManager.goBack();
        });

        btnRemove.setOnAction(event -> {
            if (!poeManager.getAssociatedProfessors().isEmpty()) {
                consultingText.setText("Clique duas vezes num docente para o desassociar!");
                listFreeProfessorsInternships.getItems().setAll(poeManager.getAssociatedProfessors());
                removing = true;
            } else
                consultingText.setText("Não existem docentes para serem desassociados!");
        });

        listFreeProfessorsInternships.setOnMouseClicked(mouseEvent -> {

            if (removing) {
                if (mouseEvent.getClickCount() == 2 && listFreeProfessorsInternships != null) {
                    poeManager.removeProfessorAssociation((Professor) listFreeProfessorsInternships.getSelectionModel().getSelectedItem());
                    removing = false;
                    consultingText.setText("Professor(a) " + professor.getName() + " desassociado(a)" + "\nCique duas vezes num docente para associá-lo a um estágio/projeto disponível");
                }
            } else {
                if (mouseEvent.getClickCount() == 2 && listFreeProfessorsInternships != null) {

                    if (listFreeProfessorsInternships.getSelectionModel().getSelectedItem() instanceof Professor) {

                        professor = (Professor) listFreeProfessorsInternships.getSelectionModel().getSelectedItem();

                        if (!poeManager.getInternshipsWithoutProfessor().isEmpty()) {
                            consultingText.setText("Docente selecionado: " + professor.getName() + "\nCique duas vezes num estágio/projeto para associá-lo ao docente.");
                            listFreeProfessorsInternships.getItems().setAll(poeManager.getInternshipsWithoutProfessor());
                        } else {

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Fase de associação manual de docentes (Aviso)");
                            alert.setContentText("Não existem propostas disponíveis para este docente");
                            alert.showAndWait();
                            update();

                        }

                    } else if (listFreeProfessorsInternships.getSelectionModel().getSelectedItem() instanceof Internship) {

                        internship = (Internship) listFreeProfessorsInternships.getSelectionModel().getSelectedItem();

                        if (poeManager.manualProfessorAssociation(internship, professor)) {
                            consultingText.setText("Cique duas vezes num docente para associá-lo a um estágio/projeto disponível");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Fase de associação de propostas");
                            alert.setContentText("Associou o docente " + professor.getName() + " ao estágio/projeto " + internship.getID());
                            alert.showAndWait();
                            update();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Fase de associação manual de docentes (Erro)");
                            alert.setContentText("Não possível fazer essa atribuição");
                            alert.showAndWait();
                            update();
                        }


                    }


                }
            }

        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.MANUAL_PROFESSOR_ASSIGNMENT) {
            this.setVisible(false);
            return;
        }

        if (!poeManager.getFreeProfessors().isEmpty())
            listFreeProfessorsInternships.getItems().setAll(poeManager.getFreeProfessors());

        this.setVisible(true);

    }

}
