package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.io.File;
import java.util.Optional;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ManageStudentsUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnImport, btnExport, btnAdd, btnDeleteAll;
    ListView listStudents;
    HBox buttonsStudent;
    VBox mainVB;
    Text t;
    TextField name, email, number, course, branch, grade, internship;
    Dialog<Pair<String, String>> addStudentsDialog;
    ButtonType addBT;

    public ManageStudentsUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnBack = new Button("Retroceder");
        btnBack.setMinWidth(BTN_MINWIDTH);
        btnBack.setPrefSize(BTN_SIZE, BTN_SIZE);
        this.setLeft(btnBack);

        listStudents = new ListView();
        listStudents.setEditable(true);
        this.setBottom(listStudents);

        t = new Text();
        t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        t.setFill(Color.WHITE);
        t.setStroke(Color.BLACK);
        t.setStrokeWidth(1);
        t.setText("Encontra-se a consultar todos os estudantes existentes.\nClique duas vezes sobre um estudante para o remover.");


        btnAdd = new Button("Adicionar estudantes");
        btnAdd.setMinWidth(BTN_MINWIDTH);
        btnAdd.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnImport = new Button("Importar estudantes");
        btnImport.setMinWidth(BTN_MINWIDTH);
        btnImport.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnExport = new Button("Exportar estudantes");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnDeleteAll = new Button("Apagar todos os estudantes");
        btnDeleteAll.setMinWidth(BTN_MINWIDTH);
        btnDeleteAll.setPrefSize(BTN_SIZE, BTN_SIZE);
        buttonsStudent = new HBox(btnAdd, btnImport, btnExport, btnDeleteAll);
        buttonsStudent.setAlignment(Pos.CENTER);
        buttonsStudent.setSpacing(10);

        mainVB = new VBox(buttonsStudent, t);
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

        btnDeleteAll.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fase de configuração de alunos (Aviso)");
            alert.setContentText("Deseja remover todos os alunos do sistema?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                while (listStudents.getItems().size() > 0) {
                    poeManager.deleteStudent((Student) listStudents.getItems().get(0));
                }

        });

        btnAdd.setOnAction(event -> {

            addStudentsDialog = new Dialog<>();
            addStudentsDialog.setTitle("Adicione um estudante.");
            addStudentsDialog.setHeaderText("Introduza os dados do estudante");

            addBT = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
            addStudentsDialog.getDialogPane().getButtonTypes().addAll(addBT, ButtonType.CANCEL);

            name = new TextField();
            name.setPromptText("Nome");
            email = new TextField();
            email.setPromptText("Email");
            number = new TextField();
            number.setPromptText("Número de aluno");
            course = new TextField();
            course.setPromptText("Curso ex: (LEI)");
            branch = new TextField();
            branch.setPromptText("Ramos ex: (DA|SI|RAS)");
            grade = new TextField();
            grade.setPromptText("Nota de curso");
            internship = new TextField();
            internship.setPromptText("Tem possibilidade de aceder a estágios além de projetos? (sim/não4)");

            buttonsStudent = new HBox(name, email, number, course, branch, grade, internship);

            addStudentsDialog.getDialogPane().setContent(buttonsStudent);
            addStudentsDialog.showAndWait();

            if (!name.getText().isEmpty() && !email.getText().isEmpty() && !number.getText().isEmpty() && !course.getText().isEmpty() && !branch.getText().isEmpty() && !grade.getText().isEmpty() && !internship.getText().isEmpty()) {

                String auxInternshipValue = "";

                if (internship.getText().equalsIgnoreCase("sim"))
                    auxInternshipValue = "true";
                else if (internship.getText().equalsIgnoreCase("não"))
                    auxInternshipValue = "false";
                else if (internship.getText().equalsIgnoreCase("nao"))
                    auxInternshipValue = "false";

                String auxStudent = number.getText() + "," +
                        name.getText() + "," + email.getText() + "," + course.getText() + "," +
                        branch.getText() + "," + grade.getText() + "," + auxInternshipValue + ",null";

                String auxResult = poeManager.separateDataStudents(auxStudent);
                if (auxResult.equals("Aluno importado com sucesso!"))
                    t.setText("Aluno importado com sucesso!\nEncontra-se a consultar todos os estudantes existentes.\nClique duas vezes sobre um estudante para o remover.");
                else
                    t.setText(auxResult + "\nEncontra-se a consultar todos os estudantes existentes.\nClique duas vezes sobre um estudante para o remover.");

            }

            update();

        });


        btnImport.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importar estudantes");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.importStudents(hFile.getName());

            update();
        });

        btnExport.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar estudantes");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportStudents(hFile.getName());
        });

        listStudents.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && !poeManager.isFlagClosedConfiguration()) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fase de configuração de alunos (Aviso)");
                alert.setContentText("Deseja remover este aluno de possíveis estágios em que se encontra associado e posteriormente eliminá-lo do sitema?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    poeManager.deleteStudent((Student) listStudents.getSelectionModel().getSelectedItem());

            }
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.MANAGE_STUDENTS) {
            this.setVisible(false);
            return;
        }

        if (poeManager.getAllStudents() != null)
            listStudents.getItems().setAll(poeManager.getAllStudents()); // overkill?

        if (poeManager.isFlagClosedConfiguration()) {

            t.setText("A fase de configuração encontra-se fechada, pelo que só pode consultar os seus dados");

            buttonsStudent = new HBox(t);
            buttonsStudent.setAlignment(Pos.CENTER);
            buttonsStudent.setSpacing(10);

            this.setCenter(buttonsStudent);

        }

        this.setVisible(true);

    }
}
