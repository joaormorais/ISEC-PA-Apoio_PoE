package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class ConfigurationUI extends BorderPane {

    PoEManager poeManager;
    Button btnManageStudents, btnManageProfessors, btnManageInternships, btnNext, btnNextClose, btnBack;
    HBox buttonsTop, buttonsHB, buttonsNextHB;
    VBox mainVB;
    Text t;

    public ConfigurationUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnBack = new Button("Retroceder");
        btnBack.setMinWidth(BTN_MINWIDTH);
        btnBack.setPrefSize(BTN_SIZE, BTN_SIZE);

        buttonsTop = new HBox(btnBack);
        buttonsTop.setAlignment(Pos.TOP_LEFT);

        this.setLeft(buttonsTop); // isto faz com que o resto do painel não fique centrado porque não existe um lado direito definido (corrigir)


        t = new Text();
        t.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
        t.setFill(Color.WHITE);
        t.setStroke(Color.BLACK);
        t.setStrokeWidth(1);
        t.setText("O que deseja configurar?");


        btnManageStudents = new Button("Alunos");
        btnManageStudents.setMinWidth(BTN_MINWIDTH);
        btnManageStudents.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnManageProfessors = new Button("Docentes");
        btnManageProfessors.setMinWidth(BTN_MINWIDTH);
        btnManageProfessors.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnManageInternships = new Button("Estágios/Projetos");
        btnManageInternships.setMinWidth(BTN_MINWIDTH);
        btnManageInternships.setPrefSize(BTN_SIZE, BTN_SIZE);
        buttonsHB = new HBox(btnManageStudents, btnManageProfessors, btnManageInternships);
        buttonsHB.setAlignment(Pos.CENTER);
        buttonsHB.setSpacing(10);



        btnNext = new Button("Avançar sem fechar fase atual");
        btnNext.setMinWidth(BTN_MINWIDTH);
        btnNext.setPrefSize(BTN_SIZE+300, BTN_SIZE);
        btnNextClose = new Button("Avançar e fechar fase atual");
        btnNextClose.setMinWidth(BTN_MINWIDTH);
        btnNextClose.setPrefSize(BTN_SIZE+300, BTN_SIZE);
        buttonsNextHB = new HBox(btnNext, btnNextClose);
        buttonsNextHB.setAlignment(Pos.BOTTOM_CENTER);
        buttonsNextHB.setSpacing(50);
        buttonsNextHB.setTranslateY(250);


        mainVB = new VBox(t, buttonsHB, buttonsNextHB);
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

        btnManageStudents.setOnAction(event -> {
            poeManager.manageStudents();
        });

        btnManageProfessors.setOnAction(event -> {
            poeManager.manageProfessors();
        });

        btnManageInternships.setOnAction(event -> {
            poeManager.manageInternship();
        });

        btnNext.setOnAction(event -> {
            poeManager.goFoward();
        });

        btnNextClose.setOnAction(event -> {
            if (!poeManager.closeConfiguration()) { // closes state CONFIGURATION

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fase de configuração (Aviso)");
                alert.setContentText("Não é possível fechar este estado. O número total de propostas para cada ramo não é superior ou igual ao número de alunos");
                alert.showAndWait();

            } else {
                poeManager.setFlagClosedConfiguration(true);
                poeManager.goFoward(); // change state to MANAGE_CANDIDACY
            }
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.CONFIGURATION) {
            this.setVisible(false);
            return;
        }

        if (poeManager.isFlagClosedConfiguration()) {

            t.setText("O que deseja consultar?");

            btnNext.setText("Avançar");
            buttonsNextHB = new HBox(btnNext);
            buttonsNextHB.setAlignment(Pos.BOTTOM_CENTER);
            buttonsNextHB.setSpacing(50);
            buttonsNextHB.setTranslateY(250);

            mainVB = new VBox(t, buttonsHB, buttonsNextHB);
            mainVB.setAlignment(Pos.CENTER);
            mainVB.setSpacing(10);
            this.setCenter(mainVB);

        }

        this.setVisible(true);

    }
}
