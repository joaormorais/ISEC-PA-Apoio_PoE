package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.CSSManager;
import pt.isec.pa.apoio_poe.ui.gui.resources.ImageManager;

public class RootPane extends BorderPane {
    PoEManager poeManager;

    public RootPane(PoEManager poeManager) {

        this.poeManager = poeManager;

        createViews();
        registerHandlers();
        update();

    }

    private void createViews() {

        CSSManager.applyCSS(this, "mystyles.css");

        this.setTop(new VBox(
                new ToolBar(poeManager)
        ));

        StackPane stackPane = new StackPane(
                new WaitStartUI(poeManager),
                new ConfigurationUI(poeManager),
                new ManageStudentsUI(poeManager),
                new ManageProfessorsUI(poeManager),
                new ManageInternshipsUI(poeManager),
                new InternshipAssociationUI(poeManager),
                new ManageCandidaciesUI(poeManager),
                new InternshipAssociationUI(poeManager),
                new ManualInternshipAssociationUI(poeManager),
                new ProfessorAssociationUI(poeManager),
                new ManualProfessorAssociationUI(poeManager),
                new SearchUI(poeManager)
        );

        stackPane.setBackground(new Background(new BackgroundImage(
                ImageManager.getImage("backgroundcool.jpeg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false)
        )));

        this.setCenter(stackPane);

    }

    private void registerHandlers() {
    }

    private void update() {
    }

}
