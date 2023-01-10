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
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.io.File;
import java.util.Optional;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ManageInternshipsUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnImport, btnExport, btnAdd, btnDeleteAll;
    ListView listInternships;
    HBox buttonsInternship;
    VBox mainVB;
    Text t;
    TextField type, id, branch, name, professorResponsible, owner, studentNumber;
    Dialog<Pair<String, String>> addInternshipsDialog;
    ButtonType addBT;

    public ManageInternshipsUI(PoEManager poeManager) {
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

        listInternships = new ListView();
        listInternships.setEditable(true);
        this.setBottom(listInternships);

        t = new Text();
        t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        t.setFill(Color.WHITE);
        t.setStroke(Color.BLACK);
        t.setStrokeWidth(1);
        t.setText("Encontra-se a consultar todos os estágios/projetos existentes.\nClique duas vezes sobre um estágio/projeto para o remover.");


        btnAdd = new Button("Adicionar estágios/projetos");
        btnAdd.setMinWidth(BTN_MINWIDTH);
        btnAdd.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnImport = new Button("Importar estágios/projetos");
        btnImport.setMinWidth(BTN_MINWIDTH);
        btnImport.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnExport = new Button("Exportar estágios/projetos");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnDeleteAll = new Button("Apagar todos os estágios/projetos");
        btnDeleteAll.setMinWidth(BTN_MINWIDTH);
        btnDeleteAll.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        buttonsInternship = new HBox(btnAdd, btnImport, btnExport, btnDeleteAll);
        buttonsInternship.setAlignment(Pos.CENTER);
        buttonsInternship.setSpacing(10);

        mainVB = new VBox(buttonsInternship, t);
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
            alert.setTitle("Fase de configuração de estágios/projetos (Aviso)");
            alert.setContentText("Deseja remover todos os estágios/projetos do sistema?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                while (listInternships.getItems().size() > 0) {
                    poeManager.deleteInternship((Internship) listInternships.getItems().get(0));
                }

        });

        btnAdd.setOnAction(event -> {

            addInternshipsDialog = new Dialog<>();
            addInternshipsDialog.setTitle("Adicione um estudante.");
            addInternshipsDialog.setHeaderText("Introduza os dados do estudante");

            addBT = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
            addInternshipsDialog.getDialogPane().getButtonTypes().addAll(addBT, ButtonType.CANCEL);

            // type, id, branch, name, professorResponsible, owner, studentNumber;


            type = new TextField();
            type.setPromptText("Tipo ex: (T1/T2/T3)");
            id = new TextField();
            id.setPromptText("ID ex: (P007)");
            branch = new TextField();
            branch.setPromptText("Ramos ex: (DA|SI|RAS)");
            name = new TextField();
            name.setPromptText("Nome");
            professorResponsible = new TextField();
            professorResponsible.setPromptText("Professor responsável ex: (ans@isec.pt)");
            owner = new TextField();
            owner.setPromptText("Empresa responsável pelo estágio");
            studentNumber = new TextField();
            studentNumber.setPromptText("Número do aluno");

            buttonsInternship = new HBox(type, id, branch, name, professorResponsible, owner, studentNumber);
            addInternshipsDialog.getDialogPane().setContent(buttonsInternship);
            addInternshipsDialog.showAndWait();

            String auxInternship = null;

            if (type.getText().equalsIgnoreCase("T1")) {
                auxInternship = type.getText() + "," + id.getText() + "," + branch.getText() + "," + name.getText() + "," + owner.getText() + "," + studentNumber.getText();
            } else if (type.getText().equalsIgnoreCase("T2")) {
                auxInternship = type.getText() + "," + id.getText() + "," + branch.getText() + "," + name.getText() + "," + professorResponsible.getText() + "," + studentNumber.getText();
            } else if (type.getText().equalsIgnoreCase("T3")) {
                auxInternship = type.getText() + "," + id.getText() + "," + name.getText() + "," + studentNumber.getText();
            }

            String auxResult = poeManager.separateDataInternships(auxInternship);
            if (auxResult.equals("Estágio importado com sucesso!") || auxResult.equals("Projeto importado com sucesso!") || auxResult.equals("Estágio/projeto autoproposto importado com sucesso!"))
                t.setText("Estágio/projeto importado com sucesso!\nEncontra-se a consultar todos os estágios/projetos existentes.\nClique duas vezes sobre um estágio/projeto  para o remover.");
            else
                t.setText(auxResult + "\nEncontra-se a consultar todos os estágios/projetos  existentes.\nClique duas vezes sobre um estágio/projeto  para o remover.");

            update();

        });

        btnImport.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importar estágios/projetos");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.importInternships(hFile.getName());

            update();
        });

        btnExport.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar estágios/projetos");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportInternships(hFile.getName());
        });

        listInternships.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && !poeManager.isFlagClosedConfiguration()) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fase de configuração de propostas (Aviso)");
                alert.setContentText("Deseja remover esta proposta de possíveis docentes e alunos a que se encontra associado e posteriormente eliminá-la do sitema?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    poeManager.deleteInternship((Internship) listInternships.getSelectionModel().getSelectedItem());

            }
        });


    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.MANAGE_INTERNSHIP) {
            this.setVisible(false);
            return;
        }

        if (poeManager.getAllInternships() != null)
            listInternships.getItems().setAll(poeManager.getAllInternships()); // overkill?

        if (poeManager.isFlagClosedConfiguration()) {

            t.setText("A fase de configuração encontra-se fechada, pelo que só pode consultar os seus dados");

            buttonsInternship = new HBox(t);
            buttonsInternship.setAlignment(Pos.CENTER);
            buttonsInternship.setSpacing(10);

            this.setCenter(buttonsInternship);

        }

        this.setVisible(true);

    }
}
