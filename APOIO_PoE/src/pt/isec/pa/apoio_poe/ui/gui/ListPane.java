package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.ListView;
import pt.isec.pa.apoio_poe.model.PoEManager;

public class ListPane extends ListView<String> {

    PoEManager poeManager;

    public ListPane(PoEManager poeManager) {
        this.poeManager = poeManager;

        createView();
        registerHandlers();
        update();
    }

    private void createView() {

    }

    private void registerHandlers() {

        poeManager.addPropertyChangeListener(evt -> {

            try {
                update();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    private void update() {
        this.getItems().clear();
        this.getItems().addAll(poeManager.getInfo());
    }


}
