package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

import java.io.File;

public class ToolBar extends MenuBar {

    PoEManager poeManager;

    Menu mnFile;
    MenuItem mnOpen, mnSave, mnExit, mnNew;

    Menu mnEdit;
    MenuItem mnUndo, mnRedo;

    public ToolBar(PoEManager poeManager) {
        this.poeManager = poeManager;

        createViews();
        registerHandlers();
        update();

    }

    private void createViews() {

        mnFile = new Menu("Ficheiro");
        mnNew = new MenuItem("Novo");
        mnNew.setGraphic(ImageManager.getImageView("Clear-icon.png", 20));
        mnOpen = new MenuItem("_Abrir");
        mnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        mnSave = new MenuItem("_Guardar");
        mnSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        mnExit = new MenuItem("_Sair");
        mnFile.getItems().addAll(mnNew, mnOpen, mnSave, new SeparatorMenuItem(), mnExit);

        mnEdit = new Menu("Editar");
        mnUndo = new MenuItem("_Desfazer");
        mnRedo = new MenuItem("_Refazer");
        mnEdit.getItems().addAll(mnUndo, mnRedo);

        this.getMenus().addAll(mnFile, mnEdit);

    }

    private void registerHandlers() {

        poeManager.addPropertyChangeListener(evt -> {
            update();
        });

        mnOpen.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Carregar dados");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("data (*.dat)", "*.dat"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.loadData(hFile);
        });

        mnSave.setOnAction(e -> { // rever isto
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar estado atual");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("data (*.dat)", "*.dat"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.saveData(hFile);
        });

        mnExit.setOnAction(e -> {
            Platform.exit();
        });

        mnNew.setOnAction(e -> {
            poeManager.init();
        });

        mnUndo.setOnAction(e -> {

        });

        mnRedo.setOnAction(e -> {

        });

    }

    private void update() {
    }

}
