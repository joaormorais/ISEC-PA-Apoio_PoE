package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class WaitStartUI extends BorderPane {
    PoEManager poeManager;
    Button btnStart, btnExit;

    public WaitStartUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnStart = new Button("Iniciar");
        btnStart.setMinWidth(BTN_MINWIDTH);
        btnStart.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnExit = new Button("Sair");
        btnExit.setMinWidth(BTN_MINWIDTH);
        btnExit.setPrefSize(BTN_SIZE, BTN_SIZE);

        /*Text t = new Text();
        t.setText("estado atual (debug): " + poeManager.getState());
        t.setFill(Color.WHITE);
        t.setX(0);
        t.setY(0);*/

        //VBox vBox = new VBox(btnStart, btnExit, t);
        VBox vBox = new VBox(btnStart, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);


    }

    private void registerHandlers() {

        poeManager.addPropertyChangeListener(evt -> {
            update();
        });

        btnStart.setOnAction(event -> {
            poeManager.goFoward();
        });

        btnExit.setOnAction(event -> {
            Platform.exit();
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.WAIT_START) {
            this.setVisible(false);
            return;
        }

        this.setVisible(true);

    }
}
