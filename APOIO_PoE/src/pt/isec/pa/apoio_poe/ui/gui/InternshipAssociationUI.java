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

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class InternshipAssociationUI extends BorderPane {

    PoEManager poeManager;
    Button btnBack, btnManual, btnExport, btnNext, btnNextClose, btnAutoAssociation;
    ListView listInfo;
    HBox buttonsInternshipAssociation, buttonsShow, buttonsNextHB;
    VBox mainVB;
    Text consultingText;
    CheckMenuItem checkBox1, checkBox2, checkBox3, checkBox4, checkBox1b, checkBox2b, checkBox3b, checkBox4b;
    MenuButton btnShowStudents, btnShowInternships;
    boolean f1, f2, f3, f4;
    int lastFilter;

    public InternshipAssociationUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        consultingText = new Text();
        this.f1 = false;
        this.f2 = false;
        this.f3 = false;
        this.f4 = false;

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


        btnAutoAssociation = new Button("Associar estágios/projetos automáticamente");
        btnAutoAssociation.setMinWidth(BTN_MINWIDTH);
        btnAutoAssociation.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnManual = new Button("Associar estágios/projetos manualmente");
        btnManual.setMinWidth(BTN_MINWIDTH);
        btnManual.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnExport = new Button("Exportar dados");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE, BTN_SIZE);
        buttonsInternshipAssociation = new HBox(btnAutoAssociation, btnManual, btnExport);
        buttonsInternshipAssociation.setAlignment(Pos.CENTER);
        buttonsInternshipAssociation.setSpacing(10);


        checkBox1 = new CheckMenuItem("filtrar por: alunos com autoproposta registada");
        checkBox2 = new CheckMenuItem("filtrar por: alunos com candidatura registada");
        checkBox3 = new CheckMenuItem("filtrar por: alunos com autoproposta atribuída");
        checkBox4 = new CheckMenuItem("filtrar por: alunos sem qualquer proposta atribuída");
        btnShowStudents = new MenuButton("Consultar alunos (c/ filtros, apenas 1)");
        btnShowStudents.setMinWidth(BTN_MINWIDTH);
        btnShowStudents.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnShowStudents.getItems().addAll(checkBox1, checkBox2, checkBox3, checkBox4);
        checkBox1b = new CheckMenuItem("filtrar por: autopropostas de alunos");
        checkBox2b = new CheckMenuItem("filtrar por: propostas de docentes");
        checkBox3b = new CheckMenuItem("filtrar por: propostas disponíveis");
        checkBox4b = new CheckMenuItem("filtrar por: propostas atribuídas");
        btnShowInternships = new MenuButton("Consultar estágios/projetos (c/ filtros)");
        btnShowInternships.setMinWidth(BTN_MINWIDTH);
        btnShowInternships.setPrefSize(BTN_SIZE + 300, BTN_SIZE);
        btnShowInternships.getItems().addAll(checkBox1b, checkBox2b, checkBox3b, checkBox4b);
        buttonsShow = new HBox(btnShowStudents, btnShowInternships);
        buttonsShow.setAlignment(Pos.CENTER);
        buttonsShow.setSpacing(10);


        btnNext = new Button("Avançar sem fechar fase atual");
        btnNext.setMinWidth(BTN_MINWIDTH);
        btnNext.setPrefSize(BTN_SIZE+300, BTN_SIZE);
        btnNextClose = new Button("Avançar e fechar fase atual");
        btnNextClose.setMinWidth(BTN_MINWIDTH);
        btnNextClose.setPrefSize(BTN_SIZE+300, BTN_SIZE);
        buttonsNextHB = new HBox(btnNext, btnNextClose);
        buttonsNextHB.setAlignment(Pos.BOTTOM_CENTER);
        buttonsNextHB.setSpacing(50);
        buttonsNextHB.setTranslateY(220);


        consultingText = new Text();
        consultingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 26));
        consultingText.setFill(Color.WHITE);
        consultingText.setStroke(Color.BLACK);
        consultingText.setStrokeWidth(1);


        mainVB = new VBox(buttonsInternshipAssociation, buttonsShow, consultingText, buttonsNextHB);
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

        btnAutoAssociation.setOnAction(e -> {

            if (!poeManager.isFlagClosedCandidacyConfiguration())
                if (!poeManager.autoInternshipAssignment1())
                    consultingText.setText("Não foram realizadas atribuições automáticas!");
                else
                    consultingText.setText("Foram realizadas atribuições automáticas!");
            else {

                boolean returnFirstAttribution = poeManager.autoInternshipAssignment1();
                int returnSecondAttribution = poeManager.autoInternshipAssignment2();

                if (!returnFirstAttribution && returnSecondAttribution == -1)
                    consultingText.setText("Não foram atribuidas propostas de estágios/projetos a alunos automaticamente!");
                else if (returnSecondAttribution == -2) {
                    consultingText.setText("Surgiu um critério de empate entre dois alunos!");
                    poeManager.manualFoward();
                    return;
                }

                if (returnFirstAttribution || returnSecondAttribution == 1)
                    consultingText.setText("Foram atribuidas propostas de estágios/projetos a alunos automaticamente!");

            }

        });

        btnExport.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar dados da fase 3");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportState3(hFile.getName());
        });

        /*listInfo.setOnMouseClicked(mouseEvent -> {

        });*/

        btnNext.setOnAction(event -> {
            poeManager.goFoward();
        });

        btnNextClose.setOnAction(event -> {
            if (poeManager.closeInternshipState()) {
                if (poeManager.isFlagClosedCandidacyConfiguration()) {
                    poeManager.setFlagClosedInternshipAssignment(true);
                    poeManager.goFoward();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Fase de associação de propostas (Aviso)");
                    alert.setContentText("Não é possível fechar esta fase porque a anterior ainda se encontra aberta!");
                    alert.showAndWait();
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fase de associação de propostas (Aviso)");
                alert.setContentText("Não é possível fechar esta fase porque nem todos os alunos com candidaturas registadas têm propostas atribuídas!");
                alert.showAndWait();

            }
        });

        btnShowInternships.setOnShowing(event -> {
            if (poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4) != null) {
                listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                consultingText.setText("Encontra-se a consultar todos os estágios/projetos de acordo com os seus filtros");
            }
        });

        checkBox1b.setOnAction(event -> {
            if (poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4) != null)
                if ((checkBox1b.isSelected())) {
                    f1 = true;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                } else if ((!checkBox1b.isSelected())) {
                    f1 = false;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                }

        });

        checkBox2b.setOnAction(event -> {
            if (poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4) != null)
                if ((checkBox2b.isSelected())) {
                    f2 = true;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                } else if ((!checkBox2b.isSelected())) {
                    f2 = false;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                }
        });

        checkBox3b.setOnAction(event -> {
            if (poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4) != null)
                if ((checkBox3b.isSelected())) {
                    f3 = true;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                } else if ((!checkBox3b.isSelected())) {
                    f3 = false;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                }
        });

        checkBox4b.setOnAction(event -> {
            if (poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4) != null)
                if ((checkBox4b.isSelected())) {
                    f4 = true;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                } else if ((!checkBox4b.isSelected())) {
                    f4 = false;
                    listInfo.getItems().setAll(poeManager.getAllInternshipsWithFilters(f1, f2, f3, f4));
                }
        });

        btnShowStudents.setOnShowing(event -> {
            consultingText.setText("Encontra-se a consultar todos os alunos de acordo com os seus filtros");
            if (checkBox1.isSelected() || checkBox2.isSelected() || checkBox3.isSelected() || checkBox4.isSelected())
                listInfo.getItems().setAll(poeManager.getAllStudentsInternship(lastFilter));
        });

        checkBox1.setOnAction(event -> {
            if (poeManager.getAllStudentsInternship(1) != null)
                if ((checkBox1.isSelected())) {
                    lastFilter = 1;
                    listInfo.getItems().setAll(poeManager.getAllStudentsInternship(1));
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                    checkBox4.setSelected(false);
                }
        });

        checkBox2.setOnAction(event -> {
            if (poeManager.getAllStudentsInternship(1) != null)
                if ((checkBox2.isSelected())) {
                    lastFilter = 2;
                    listInfo.getItems().setAll(poeManager.getAllStudentsInternship(2));
                    checkBox1.setSelected(false);
                    checkBox3.setSelected(false);
                    checkBox4.setSelected(false);
                }
        });

        checkBox3.setOnAction(event -> {
            if (poeManager.getAllStudentsInternship(1) != null)
                if ((checkBox3.isSelected())) {
                    lastFilter = 3;
                    listInfo.getItems().setAll(poeManager.getAllStudentsInternship(3));
                    checkBox1.setSelected(false);
                    checkBox2.setSelected(false);
                    checkBox4.setSelected(false);
                }
        });

        checkBox4.setOnAction(event -> {
            if (poeManager.getAllStudentsInternship(1) != null)
                if ((checkBox4.isSelected())) {
                    lastFilter = 4;
                    listInfo.getItems().setAll(poeManager.getAllStudentsInternship(4));
                    checkBox1.setSelected(false);
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                }
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.INTERNSHIP_ASSIGNMENT) {
            this.setVisible(false);
            return;
        }

        if (poeManager.isFlagClosedInternshipAssignment()) {

            consultingText.setText("A fase de associação de projetos encontra-se fechada, pelo que só pode consultar os seus dados");

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
