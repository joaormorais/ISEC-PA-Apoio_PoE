package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.PoEManager;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class MainJFX extends Application {
    PoEManager poeManager;

    @Override
    public void init() throws Exception {
        super.init();
        poeManager = new PoEManager();
    }

    @Override
    public void start(Stage stage) throws Exception {

        configureStage(stage);
        configureListStage(stage.getX() , stage.getY() );

    }

    private void configureListStage(double x, double y) {

        Stage stage = new Stage();
        ListPane root = new ListPane(poeManager);
        Scene scene = new Scene(root, 300, 400);
        stage.setScene(scene);
        stage.setTitle("Apoio ao Projeto ou Estágio: INFO");
        if(x >= 0 && y >= 0){
            stage.setX(x);
            stage.setY(y);
        }
        stage.show();

    }

    public void configureStage(Stage stage){

        RootPane root = new RootPane(poeManager);
        Scene scene = new Scene(root,widthScreen,heightScreen);
        stage.setScene(scene);
        stage.setTitle("Instituto Superior de Engenharia de Coimbra: Apoio ao Projeto ou Estágio");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.show();

    }

}
