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
import pt.isec.pa.apoio_poe.model.data.Candidacy;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.io.File;
import java.util.Optional;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ManageCandidaciesUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnImport, btnExport, btnRemove, btnShowCandidacies, btnShow1, btnShow2, btnShow3, btnNext, btnNextClose, btnAdd, btnDeleteAll;
    ListView listCandidacies;
    HBox buttonsCandidacy, buttonsShow, buttonsNextHB;
    VBox mainVB;
    Text consultingText;
    CheckMenuItem checkBox1, checkBox2, checkBox3, checkBox4;
    MenuButton btnShow4;
    boolean f1, f2, f3, f4, removing;
    TextField number, ids;
    Dialog<Pair<String, String>> addCandidaciesDialog;
    ButtonType addBT;

    public ManageCandidaciesUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        this.f1 = false;
        this.f2 = false;
        this.f3 = false;
        this.f4 = false;
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


        listCandidacies = new ListView();
        listCandidacies.setEditable(true);
        this.setBottom(listCandidacies);


        btnAdd = new Button("Adicionar candidaturas");
        btnAdd.setMinWidth(BTN_MINWIDTH);
        btnAdd.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnImport = new Button("Importar candidaturas");
        btnImport.setMinWidth(BTN_MINWIDTH);
        btnImport.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnExport = new Button("Exportar candidaturas");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnRemove = new Button("Remover candidaturas");
        btnRemove.setMinWidth(BTN_MINWIDTH);
        btnRemove.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnDeleteAll = new Button("Remover todos as candidaturas");
        btnDeleteAll.setMinWidth(BTN_MINWIDTH);
        btnDeleteAll.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        buttonsCandidacy = new HBox(btnAdd, btnImport, btnExport, btnRemove, btnDeleteAll);
        buttonsCandidacy.setAlignment(Pos.CENTER);
        buttonsCandidacy.setSpacing(10);


        btnShowCandidacies = new Button("Consultar candidaturas");
        btnShowCandidacies.setMinWidth(BTN_MINWIDTH);
        btnShowCandidacies.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnShow1 = new Button("Consultar alunos com autoproposta");
        btnShow1.setMinWidth(BTN_MINWIDTH);
        btnShow1.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnShow2 = new Button("Consultar alunos com candidatura já registada");
        btnShow2.setMinWidth(BTN_MINWIDTH);
        btnShow2.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnShow3 = new Button("Consultar alunos sem candidatura registada");
        btnShow3.setMinWidth(BTN_MINWIDTH);
        btnShow3.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        checkBox1 = new CheckMenuItem("filtrar por: autopropostas de alunos");
        checkBox2 = new CheckMenuItem("filtrar por: propostas de docentes");
        checkBox3 = new CheckMenuItem("filtrar por: propostas com candidaturas");
        checkBox4 = new CheckMenuItem("filtrar por: propostas sem candidaturas");
        btnShow4 = new MenuButton("Consultar estágios/projetos (c/ filtros)");
        btnShow4.setMinWidth(BTN_MINWIDTH);
        btnShow4.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnShow4.getItems().addAll(checkBox1, checkBox2, checkBox3, checkBox4);
        buttonsShow = new HBox(btnShowCandidacies, btnShow1, btnShow2, btnShow3, btnShow4);
        buttonsShow.setAlignment(Pos.CENTER);
        buttonsShow.setSpacing(10);


        btnNext = new Button("Avançar sem fechar fase atual");
        btnNext.setMinWidth(BTN_MINWIDTH);
        btnNext.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnNextClose = new Button("Avançar e fechar fase atual");
        btnNextClose.setMinWidth(BTN_MINWIDTH);
        btnNextClose.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        buttonsNextHB = new HBox(btnNext, btnNextClose);
        buttonsNextHB.setAlignment(Pos.BOTTOM_CENTER);
        buttonsNextHB.setSpacing(50);
        buttonsNextHB.setTranslateY(220);


        consultingText = new Text();
        consultingText.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
        consultingText.setFill(Color.WHITE);
        consultingText.setStroke(Color.BLACK);
        consultingText.setStrokeWidth(1);
        consultingText.setText("Encontra-se a consultar todas as candidaturas existentes");


        mainVB = new VBox(buttonsCandidacy, buttonsShow, buttonsNextHB, consultingText);
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
            alert.setTitle("Fase de configuração de candidaturas (Aviso)");
            alert.setContentText("Deseja remover todos os alunos do sistema?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                while (listCandidacies.getItems().size() > 0) {
                    poeManager.deleteCandidacy((Candidacy) listCandidacies.getItems().get(0));
                }

        });

        btnAdd.setOnAction(event -> {

            addCandidaciesDialog = new Dialog<>();
            addCandidaciesDialog.setTitle("Adicione uma candidatura.");
            addCandidaciesDialog.setHeaderText("Introduza os dados da candidatura");

            addBT = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
            addCandidaciesDialog.getDialogPane().getButtonTypes().addAll(addBT, ButtonType.CANCEL);

            number = new TextField();
            number.setPromptText("Número do estudante");
            ids = new TextField();
            ids.setPromptText("Estágios/Projetos ex:(P027,P007,P010)");

            buttonsCandidacy = new HBox(number, ids);

            addCandidaciesDialog.getDialogPane().setContent(buttonsCandidacy);
            addCandidaciesDialog.showAndWait();

            String auxCandidacy = number.getText() + "," + ids.getText();

            String auxResult = poeManager.separateDataCandidacies(auxCandidacy);

            if (auxResult.equals("Candidatura importada com sucesso!"))
                consultingText.setText("Candidatura importada com sucesso!\nEncontra-se a consultar todas as candidaturas existentes.\nClique duas vezes sobre uma candidatura para a remover.");
            else
                consultingText.setText(auxResult + "\nEncontra-se a consultar todas as candidaturas existentes.\nClique duas vezes sobre uma candidatura para a remover.");

            update();

        });

        btnImport.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importar candidaturas");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.importCandidacies(hFile.getName());

            update();
        });

        btnExport.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar candidaturas");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportCandidacies(hFile.getName());
        });

        btnRemove.setOnAction(event -> {
            if (!poeManager.getAllCandidaciesClone().isEmpty()) {
                consultingText.setText("Clique duas vezes numa candidatura para a eliminar!");
                listCandidacies.getItems().setAll(poeManager.getAllCandidaciesClone());
                removing = true;
            } else
                consultingText.setText("Não existem candidaturas para serem eliminadas!");
        });

        listCandidacies.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && !poeManager.isFlagClosedCandidacyConfiguration() && removing) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fase de configuração de candidaturas (Aviso)");
                alert.setContentText("Deseja remover esta candidatura do sitema?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    poeManager.deleteCandidacy((Candidacy) listCandidacies.getSelectionModel().getSelectedItem());

                removing = false;
                consultingText.setText("Encontra-se a consultar todas as candidaturas existentes");
            }
        });

        btnShowCandidacies.setOnAction(event -> {
            if (poeManager.getAllCandidaciesClone() != null) {
                listCandidacies.getItems().setAll(poeManager.getAllCandidaciesClone());
                consultingText.setText("Encontra-se a consultar todas as candidaturas existentes");
            }
        });

        btnShow1.setOnAction(event -> {
            if (poeManager.getStudentsWithAutoProposedInternship() != null) {
                listCandidacies.getItems().setAll(poeManager.getStudentsWithAutoProposedInternship());
                consultingText.setText("Encontra-se a consultar todos os alunos com autopropostas associadas");
            }
        });

        btnShow2.setOnAction(event -> {
            if (poeManager.getStudentsWithCandidacy() != null) {
                listCandidacies.getItems().setAll(poeManager.getStudentsWithCandidacy());
                consultingText.setText("Encontra-se a consultar todos os alunos com candidaturas registadas");
            }
        });

        btnShow3.setOnAction(event -> {
            if (poeManager.getStudentsWithoutCandidacy() != null) {
                listCandidacies.getItems().setAll(poeManager.getStudentsWithoutCandidacy());
                consultingText.setText("Encontra-se a consultar todos os alunos sem candidaturas registadas");
            }
        });

        btnShow4.setOnShowing(event -> {
            if (poeManager.getInternshipsWithFilter(f1, f2, f3, f4) != null) {
                listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                consultingText.setText("Encontra-se a consultar todos os estágios/projetos de acordo com os seus filtros");
            }
        });

        checkBox1.setOnAction(event -> {
            if (poeManager.getInternshipsWithFilter(f1, f2, f3, f4) != null)
                if ((checkBox1.isSelected())) {
                    f1 = true;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                } else if ((!checkBox1.isSelected())) {
                    f1 = false;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                }

        });

        checkBox2.setOnAction(event -> {
            if (poeManager.getInternshipsWithFilter(f1, f2, f3, f4) != null)
                if ((checkBox2.isSelected())) {
                    f2 = true;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                } else if ((!checkBox2.isSelected())) {
                    f2 = false;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                }
        });

        checkBox3.setOnAction(event -> {
            if (poeManager.getInternshipsWithFilter(f1, f2, f3, f4) != null)
                if ((checkBox3.isSelected())) {
                    f3 = true;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                } else if ((!checkBox3.isSelected())) {
                    f3 = false;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                }
        });

        checkBox4.setOnAction(event -> {
            if (poeManager.getInternshipsWithFilter(f1, f2, f3, f4) != null)
                if ((checkBox4.isSelected())) {
                    f4 = true;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                } else if ((!checkBox4.isSelected())) {
                    f4 = false;
                    listCandidacies.getItems().setAll(poeManager.getInternshipsWithFilter(f1, f2, f3, f4));
                }
        });

        btnNext.setOnAction(event -> {
            poeManager.goFoward();
        });

        btnNextClose.setOnAction(event -> {
            if (!poeManager.isFlagClosedConfiguration()) { // closes state CONFIGURATION

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fase de configuração de candidaturas (Aviso)");
                alert.setContentText("Não é possível fechar este estado. A fase anterior ainda se encontra aberta!");
                alert.showAndWait();

            } else {
                poeManager.setFlagClosedCandidacyConfiguration(true);
                poeManager.goFoward(); // change state to INTERNSHIP_ASSIGNEMENT
            }
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.MANAGE_CANDIDACY) {
            this.setVisible(false);
            return;
        }

        if (poeManager.getAllCandidaciesClone() != null)
            listCandidacies.getItems().setAll(poeManager.getAllCandidaciesClone()); // overkill?


        if (poeManager.isFlagClosedCandidacyConfiguration()) {

            consultingText.setText("A fase de configuração de candidaturas encontra-se fechada, pelo que só pode consultar os seus dados");

            btnNext.setText("Avançar");
            buttonsNextHB = new HBox(btnNext);
            buttonsNextHB.setAlignment(Pos.BOTTOM_CENTER);
            buttonsNextHB.setSpacing(50);
            buttonsNextHB.setTranslateY(250);

            mainVB = new VBox(buttonsShow, consultingText, buttonsNextHB);
            mainVB.setAlignment(Pos.CENTER);
            mainVB.setSpacing(10);
            this.setCenter(mainVB);

        }

        this.setVisible(true);

    }
}
