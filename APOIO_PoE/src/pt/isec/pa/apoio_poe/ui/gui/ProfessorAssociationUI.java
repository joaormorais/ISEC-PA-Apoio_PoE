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
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.io.File;
import java.util.Optional;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ProfessorAssociationUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnAutoAssociation, btnManual, btnExport, btnNext;
    ListView listInfo;
    HBox buttonsProfessorAssociation, buttonsNextHB;
    VBox mainVB;
    Text consultingText;
    CheckMenuItem checkBox1, checkBox2, checkBox3;
    MenuButton btnShowInfo;

    public ProfessorAssociationUI(PoEManager poeManager) {
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

        listInfo = new ListView();
        listInfo.setEditable(true);
        this.setBottom(listInfo);

        btnAutoAssociation = new Button("Associar docentes automáticamente");
        btnAutoAssociation.setMinWidth(BTN_MINWIDTH);
        btnAutoAssociation.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnManual = new Button("Associar docentes manualmente");
        btnManual.setMinWidth(BTN_MINWIDTH);
        btnManual.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnExport = new Button("Exportar dados");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE, BTN_SIZE);
        buttonsProfessorAssociation = new HBox(btnAutoAssociation, btnManual, btnExport);
        buttonsProfessorAssociation.setAlignment(Pos.CENTER);
        buttonsProfessorAssociation.setSpacing(10);

        checkBox1 = new CheckMenuItem("Consultar alunos com proposta atribuída e com orientador associado");
        checkBox2 = new CheckMenuItem("Consultar alunos com proposta atribuída mas sem orientador associado");
        checkBox3 = new CheckMenuItem("Consultar número de orientações por docente, em média, mínimo, máximo (indisponível)");
        btnShowInfo = new MenuButton("Consultar");
        btnShowInfo.setMinWidth(BTN_MINWIDTH);
        btnShowInfo.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnShowInfo.getItems().addAll(checkBox1, checkBox2, checkBox3);

        btnNext = new Button("Avançar e fechar fase atual");
        btnNext.setMinWidth(BTN_MINWIDTH);
        btnNext.setPrefSize(BTN_SIZE+300, BTN_SIZE);
        buttonsNextHB = new HBox(btnNext);
        buttonsNextHB.setAlignment(Pos.BOTTOM_CENTER);
        buttonsNextHB.setSpacing(50);
        buttonsNextHB.setTranslateY(220);


        consultingText = new Text();
        consultingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 26));
        consultingText.setFill(Color.WHITE);
        consultingText.setStroke(Color.BLACK);
        consultingText.setStrokeWidth(1);


        mainVB = new VBox(buttonsProfessorAssociation, btnShowInfo, consultingText, buttonsNextHB);
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

        btnManual.setOnAction(e -> {
            poeManager.manualFoward();
        });

        btnExport.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar dados da fase 4");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportState4and5(hFile.getName());
        });

        btnNext.setOnAction(event -> {
            if (poeManager.isFlagClosedInternshipAssignment()) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fase de associação de docentes (Aviso)");
                alert.setContentText("Depois de fechar esta fase não pode retornar. Tem a certeza que quer fechar esta fase?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    poeManager.goFoward();

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fase de associação de docentes (Aviso)");
                alert.setContentText("Não é possível fechar esta fase porque a fase anterior está aberta!");
                alert.showAndWait();

            }
        });

        checkBox1.setOnAction(event -> {
            if (poeManager.getStudentsWithInternshipAndProfessor() != null)
                if ((checkBox1.isSelected())) {
                    consultingText.setText("Encontra-se a consultar alunos com proposta atribuída e com orientador associado");
                    listInfo.getItems().setAll(poeManager.getStudentsWithInternshipAndProfessor());
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                }

            if (!checkBox1.isSelected() && !checkBox2.isSelected() && !checkBox3.isSelected())
                consultingText.setText("");
        });

        checkBox2.setOnAction(event -> {
            if (poeManager.getStudentsWithInternshipAndWithoutProfessor() != null)
                if ((checkBox2.isSelected())) {
                    consultingText.setText("Encontra-se a consultar alunos com proposta atribuída mas sem orientador associado");
                    listInfo.getItems().setAll(poeManager.getStudentsWithInternshipAndWithoutProfessor());
                    checkBox1.setSelected(false);
                    checkBox3.setSelected(false);
                }

            if (!checkBox1.isSelected() && !checkBox2.isSelected() && !checkBox3.isSelected())
                consultingText.setText("");
        });

        checkBox3.setOnAction(event -> {
            consultingText.setText("INDISPONÍVEL");
            listInfo.getItems().setAll();
            checkBox1.setSelected(false);
            checkBox2.setSelected(false);

            if (!checkBox1.isSelected() && !checkBox2.isSelected() && !checkBox3.isSelected())
                consultingText.setText("");
        });

        btnAutoAssociation.setOnAction(e -> {

            if (!poeManager.autoProfessorAssignment()) {
                consultingText.setText("Não foram atribuidos professores a estágios/projetos automaticamente!");
            } else
                consultingText.setText("Foram atribuidos professores a estágios/projetos automaticamente!");

        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.PROFESSOR_ASSIGNMENT) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);

    }

}
