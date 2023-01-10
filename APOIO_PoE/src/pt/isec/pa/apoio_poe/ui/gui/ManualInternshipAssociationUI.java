package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.util.List;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ManualInternshipAssociationUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnRemove;
    ListView listFreeStudentsInternships, listDrawStudentsInternships, listCandidacies1, listCandidacies2;
    HBox listDraw;
    VBox mainVB,bottomVB;
    Text consultingText, bottomText;
    Student student;
    Internship internship;
    Boolean removing;
    List<Student> drawStudents;

    public ManualInternshipAssociationUI(PoEManager poeManager) {
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

        listDrawStudentsInternships = new ListView(); // this list will only be used if we have a draw condition
        listDrawStudentsInternships.setEditable(true);
        listFreeStudentsInternships = new ListView();
        listFreeStudentsInternships.setEditable(true);
        this.setBottom(listFreeStudentsInternships);

        btnRemove = new Button("Remover atribuições");
        btnRemove.setMinWidth(BTN_MINWIDTH);
        btnRemove.setPrefSize(BTN_SIZE, BTN_SIZE);
        consultingText = new Text();
        consultingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 26));
        consultingText.setFill(Color.WHITE);
        consultingText.setStroke(Color.BLACK);
        consultingText.setStrokeWidth(1);
        consultingText.setText("Cique duas vezes num aluno para associá-lo a um estágio/projeto disponível");
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
            if (!poeManager.getAssociatedStudents().isEmpty()) {
                consultingText.setText("Clique duas vezes num estudante para o desassociar!");
                listFreeStudentsInternships.getItems().setAll(poeManager.getAssociatedStudents());
                removing = true;
            } else
                consultingText.setText("Não existem propostas para serem desassociadas!");
        });

        listFreeStudentsInternships.setOnMouseClicked(mouseEvent -> {
            if (removing) { // if we are removing an association
                if (mouseEvent.getClickCount() == 2 && listFreeStudentsInternships != null) {
                    student = (Student) listFreeStudentsInternships.getSelectionModel().getSelectedItem();
                    poeManager.removeAssociationStudentInternship((Student) listFreeStudentsInternships.getSelectionModel().getSelectedItem());
                    removing = false;
                    consultingText.setText("Aluno(a) " + student.getName() + " desassociado(a)" + "\nCique duas vezes num aluno para associá-lo a um estágio/projeto disponível");
                    listFreeStudentsInternships.getItems().setAll(poeManager.getFreeStudents());
                    update();
                }
            } else { // if we are doing an association
                if (mouseEvent.getClickCount() == 2 && listFreeStudentsInternships != null) {

                    if (listFreeStudentsInternships.getSelectionModel().getSelectedItem() instanceof Student) {

                        student = (Student) listFreeStudentsInternships.getSelectionModel().getSelectedItem();

                        if (!poeManager.getFreeInternships(student).isEmpty()) {
                            consultingText.setText("Aluno(a) selecionado: " + student.getName() + "\nCique duas vezes num estágio/projeto para associá-lo ao estudante.");
                            listFreeStudentsInternships.getItems().setAll(poeManager.getFreeInternships(student));
                            this.setBottom(listFreeStudentsInternships);
                        } else {

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Fase de associação manual de propostas (Aviso)");
                            alert.setContentText("Não existem propostas disponíveis para este aluno");
                            alert.showAndWait();
                            update();

                        }

                    } else if (listFreeStudentsInternships.getSelectionModel().getSelectedItem() instanceof Internship) {

                        internship = (Internship) listFreeStudentsInternships.getSelectionModel().getSelectedItem();

                        if (poeManager.associateStudentWithInternship(student, internship)) {
                            consultingText.setText("Cique duas vezes num aluno para associá-lo a um estágio disponível");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Fase de associação manual de propostas");
                            alert.setContentText("Associou o aluno " + student.getName() + " ao estágio/projeto " + internship.getID());
                            alert.showAndWait();
                            listFreeStudentsInternships.getItems().setAll(poeManager.getFreeStudents());
                            this.setBottom(listFreeStudentsInternships);
                            //this.setBottom(listFreeStudentsInternships);
                            //update();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Fase de associação manual de propostas (Erro)");
                            alert.setContentText("Não possível fazer essa atribuição");
                            alert.showAndWait();
                            update();
                        }


                    }

                    update();

                }
            }
        });

        listDrawStudentsInternships.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && listDrawStudentsInternships != null) {

                student = (Student) listDrawStudentsInternships.getSelectionModel().getSelectedItem();
                consultingText.setText("Aluno selecionado: " + student);

                if (!poeManager.getFreeInternships(student).isEmpty()) {
                    consultingText.setText("Aluno selecionado: " + student.getName() + "\nCique duas vezes num estágio/projeto para associá-lo ao estudante.");
                    listFreeStudentsInternships.getItems().setAll(poeManager.getFreeInternships(student));
                    this.setBottom(listFreeStudentsInternships);
                } else {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Fase de associação manual de propostas (Aviso)");
                    alert.setContentText("Não existem propostas disponíveis para este aluno");
                    alert.showAndWait();
                    update();

                }

            }

        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.MANUAL_INTERNSHIP_ASSIGNMENT) {
            this.setVisible(false);
            return;
        }

        // verify if we have a draw condition (start) -------------

        drawStudents = poeManager.getStudentsInDrawCondition();

        if (drawStudents != null) {

            consultingText.setText("Estes alunos encontram-se em fator de empate!\nClique duas vezes no aluno que deseja associar a um estágio/projeto");
            listDrawStudentsInternships.setPrefSize(580, 300);
            listDrawStudentsInternships.setEditable(true);
            listDrawStudentsInternships.getItems().setAll(drawStudents);

            listCandidacies1 = new ListView();
            listCandidacies1.setPrefSize(580, 300);
            listCandidacies1.setEditable(true);
            listCandidacies1.getItems().setAll(poeManager.getFirstAndSecondStudentInternships(drawStudents.get(0)));

            listCandidacies2 = new ListView();
            listCandidacies2.setPrefSize(580, 300);
            listCandidacies2.setEditable(true);
            listCandidacies2.getItems().setAll(poeManager.getFirstAndSecondStudentInternships(drawStudents.get(1)));


            bottomText = new Text();
            bottomText.setFill(Color.WHITE);
            bottomText.setTextAlignment(TextAlignment.CENTER);
            bottomText.setFont(Font.font ("Verdana", FontWeight.BOLD, 8));
            bottomText.setText("\t\t\t\t\t\t\t\t\t\t\t\tAlunos em estado de empate\t\t\t\t\t\t\t\t\t\t\t\tEstágios/projetos a que o primeiro aluno de candidatou\t\t\t\t\t\t\t\t\t\t\t\tEstágios/projetos a que o segundo aluno de candidatou");

            listDraw = new HBox(listDrawStudentsInternships, listCandidacies1, listCandidacies2);
            listDraw.setAlignment(Pos.CENTER);
            listDraw.setSpacing(10);
            bottomVB = new VBox(bottomText, listDraw);
            bottomVB.setAlignment(Pos.CENTER);
            bottomVB.setSpacing(10);

            this.setBottom(bottomVB);

            // verify if we have a draw condition (end) -------------
        } else {
            this.setBottom(listFreeStudentsInternships);

            /*if (poeManager.getFreeStudents() != null)
                listFreeStudentsInternships.getItems().setAll(poeManager.getFreeStudents()); // overkill?*/
        }

        this.setVisible(true);

    }

}
