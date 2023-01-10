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
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.io.File;
import java.util.Optional;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ManageProfessorsUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnImport, btnExport, btnAdd, btnDeleteAll;
    ListView listProfessors;
    HBox buttonsProfessor;
    VBox mainVB;
    Text t;
    TextField name, email;
    Dialog<Pair<String, String>> addProfessorsDialog;
    ButtonType addBT;

    public ManageProfessorsUI(PoEManager poeManager) {
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

        listProfessors = new ListView();
        listProfessors.setEditable(true);
        this.setBottom(listProfessors);

        t = new Text();
        t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        t.setFill(Color.WHITE);
        t.setStroke(Color.BLACK);
        t.setStrokeWidth(1);
        t.setText("Encontra-se a consultar todos os docentes existentes.\nClique duas vezes sobre um docente para o remover.");


        btnAdd = new Button("Adicionar docentes");
        btnAdd.setMinWidth(BTN_MINWIDTH);
        btnAdd.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnImport = new Button("Importar docentes");
        btnImport.setMinWidth(BTN_MINWIDTH);
        btnImport.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnExport = new Button("Exportar docentes");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnDeleteAll = new Button("Apagar todos os docentes");
        btnDeleteAll.setMinWidth(BTN_MINWIDTH);
        btnDeleteAll.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        buttonsProfessor = new HBox(btnAdd, btnImport, btnExport, btnDeleteAll);
        buttonsProfessor.setAlignment(Pos.CENTER);
        buttonsProfessor.setSpacing(10);

        mainVB = new VBox(buttonsProfessor, t);
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
            alert.setTitle("Fase de configuração de docentes (Aviso)");
            alert.setContentText("Deseja remover todos os docentes do sistema?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                while (listProfessors.getItems().size() > 0) {
                    poeManager.deleteProfessor((Professor) listProfessors.getItems().get(0));
                }

        });

        btnAdd.setOnAction(event -> {

            addProfessorsDialog = new Dialog<>();
            addProfessorsDialog.setTitle("Adicione um docente.");
            addProfessorsDialog.setHeaderText("Introduza os dados do docente");

            addBT = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
            addProfessorsDialog.getDialogPane().getButtonTypes().addAll(addBT, ButtonType.CANCEL);

            name = new TextField();
            name.setPromptText("Nome");
            email = new TextField();
            email.setPromptText("Email");

            buttonsProfessor = new HBox(name, email);

            addProfessorsDialog.getDialogPane().setContent(buttonsProfessor);
            addProfessorsDialog.showAndWait();

            if (!name.getText().isEmpty() && !email.getText().isEmpty()) {
                String auxProfessor = name.getText() + "," + email.getText();

                String auxResult = poeManager.separateDataProfessors(auxProfessor);
                if (auxResult.equals("Docente importado com sucesso!"))
                    t.setText("Docente importado com sucesso!\nEncontra-se a consultar todos os docentes existentes.\nClique duas vezes sobre um docente para o remover.");
                else
                    t.setText(auxResult + "\nEncontra-se a consultar todos os docentes existentes.\nClique duas vezes sobre um docente para o remover.");

            }

            update();

        });

        btnImport.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importar docentes");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.importProfessors(hFile.getName());

            update();
        });

        btnExport.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar docentes");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportProfessors(hFile.getName());
        });

        listProfessors.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && !poeManager.isFlagClosedConfiguration()) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fase de configuração de docentes (Aviso)");
                alert.setContentText("Deseja remover este docente de possíveis estágios em que se encontra associado e posteriormente eliminá-lo do sitema?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    poeManager.deleteProfessor((Professor) listProfessors.getSelectionModel().getSelectedItem());

            }
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.MANAGE_PROFESSORS) {
            this.setVisible(false);
            return;
        }

        if (poeManager.getAllProfessors() != null)
            listProfessors.getItems().setAll(poeManager.getAllProfessors()); // overkill?

        if (poeManager.isFlagClosedConfiguration()) {

            t.setText("A fase de configuração encontra-se fechada, pelo que só pode consultar os seus dados");

            buttonsProfessor = new HBox(t);
            buttonsProfessor.setAlignment(Pos.CENTER);
            buttonsProfessor.setSpacing(10);

            this.setCenter(buttonsProfessor);

        }

        this.setVisible(true);

    }
}
