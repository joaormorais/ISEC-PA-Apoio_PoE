package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.PoEManager;
import pt.isec.pa.apoio_poe.model.data.Professor;
import pt.isec.pa.apoio_poe.model.data.Student;
import pt.isec.pa.apoio_poe.model.data.internship.Internship;
import pt.isec.pa.apoio_poe.model.fsm.PoEManagementState;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static pt.isec.pa.apoio_poe.ui.gui.IVariablesUI.*;

public class SearchUI extends BorderPane {

    PoEManager poeManager;
    Button btnExport;
    ListView listInfo;
    HBox buttonsSearch;
    VBox mainVB, sideVB;
    Text consultingText, statsText;
    CheckMenuItem checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    MenuButton btnShowInfo;
    Double d1, d2, d3;
    ObservableList<PieChart.Data> pieChartData;
    BarChart<String, Number> barChart;
    List<Internship> auxInternships;
    List<Student> auxStudents;
    int nAssociated, nNotAssociated;

    public SearchUI(PoEManager poeManager) {
        this.poeManager = poeManager;

        this.d1 = 0.0;
        this.d2 = 0.0;
        this.d3 = 0.0;
        this.pieChartData = null;
        this.nNotAssociated = 0;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        listInfo = new ListView();
        listInfo.setEditable(true);
        this.setBottom(listInfo);


        btnExport = new Button("Exportar dados");
        btnExport.setMinWidth(BTN_MINWIDTH);
        btnExport.setPrefSize(BTN_SIZE, BTN_SIZE);
        buttonsSearch = new HBox(btnExport);
        buttonsSearch.setAlignment(Pos.CENTER);
        buttonsSearch.setSpacing(10);


        checkBox1 = new CheckMenuItem("Consultar estudantes com propostas atribuídas");
        checkBox2 = new CheckMenuItem("Consultar estudantes sem propostas atribuídas e com opções de candidatura");
        checkBox3 = new CheckMenuItem("Consultar propostas disponíveis");
        checkBox4 = new CheckMenuItem("Consultar propostas atribuídas");
        checkBox5 = new CheckMenuItem("Consultar dados estatisticos sobre docentes (indisponível)");
        btnShowInfo = new MenuButton("Consultar");
        btnShowInfo.setMinWidth(BTN_MINWIDTH);
        btnShowInfo.setPrefSize(BTN_SIZE, BTN_SIZE);
        btnShowInfo.getItems().addAll(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5);


        consultingText = new Text();
        consultingText.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
        consultingText.setFill(Color.WHITE);
        consultingText.setStroke(Color.BLACK);
        consultingText.setStrokeWidth(1);
        statsText = new Text();
        statsText.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
        statsText.setFill(Color.WHITE);
        statsText.setStroke(Color.BLACK);
        statsText.setStrokeWidth(1);


        mainVB = new VBox(buttonsSearch, btnShowInfo, statsText, consultingText);
        mainVB.setAlignment(Pos.CENTER);
        mainVB.setSpacing(10);
        this.setCenter(mainVB);

    }

    private void registerHandlers() {

        poeManager.addPropertyChangeListener(evt -> {
            update();
        });

        btnExport.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exportar dados da fase 5");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("csv (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("Qualquer tipo", "*.*")
            );

            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (hFile != null)
                poeManager.exportState4and5(hFile.getName());
        });

        checkBox1.setOnAction(event -> {
            if (poeManager.getStudentsWithInternship() != null)
                if ((checkBox1.isSelected())) {
                    consultingText.setText("Encontra-se a consultar alunos com propostas atribuídas");
                    listInfo.getItems().setAll(poeManager.getStudentsWithInternship());
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                    checkBox4.setSelected(false);
                    checkBox5.setSelected(false);
                }
        });

        checkBox2.setOnAction(event -> {
            if (poeManager.getStudentsWithoutInternshipAndWithCandidacy() != null)
                if ((checkBox2.isSelected())) {
                    consultingText.setText("Encontra-se a consultar alunos sem propostas atribuídas e com opções de candidatura");
                    listInfo.getItems().setAll(poeManager.getStudentsWithoutInternshipAndWithCandidacy());
                    checkBox1.setSelected(false);
                    checkBox3.setSelected(false);
                    checkBox4.setSelected(false);
                    checkBox5.setSelected(false);
                }
        });

        checkBox3.setOnAction(event -> {
            if (poeManager.getAvailableInternship() != null)
                if ((checkBox3.isSelected())) {
                    consultingText.setText("Encontra-se a consultar propostas disponíveis");
                    listInfo.getItems().setAll(poeManager.getAvailableInternship());
                    checkBox1.setSelected(false);
                    checkBox2.setSelected(false);
                    checkBox4.setSelected(false);
                    checkBox5.setSelected(false);
                }
        });

        checkBox4.setOnAction(event -> {
            if (poeManager.getAttributedInternship() != null)
                if ((checkBox4.isSelected())) {
                    consultingText.setText("Encontra-se a consultar propostas atribuídas");
                    listInfo.getItems().setAll(poeManager.getAttributedInternship());
                    checkBox1.setSelected(false);
                    checkBox2.setSelected(false);
                    checkBox3.setSelected(false);
                    checkBox5.setSelected(false);
                }
        });

        checkBox5.setOnAction(event -> {
            if ((checkBox5.isSelected())) {
                consultingText.setText("INDISPONÍVEL");
                listInfo.getItems().setAll();
                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
                checkBox3.setSelected(false);
                checkBox4.setSelected(false);
            }
        });

    }

    private void update() {

        if (poeManager.getState() != PoEManagementState.SEARCH) {
            this.setVisible(false);
            return;
        }

        /**
         * Initialization of the text that will show stats about the number of internships associated to a student and not associated
         */

        auxInternships = poeManager.getAllInternships();
        auxStudents = poeManager.getAllStudents();

        for (Student i : auxStudents)
            if (i.getInternshipAssociated() != null)
                nAssociated++;

        nNotAssociated = auxInternships.size() - nAssociated;

        statsText.setText("Número de propostas atribuídas: " + nAssociated + "(" + (nAssociated / auxInternships.size()) * 100 + "%)" + "\nNúmero de propostas não atribbuídas: " + nNotAssociated + "(" + (nNotAssociated / auxInternships.size()) * 100 + "%)");

        /**
         * Creation of a pie graphic with the distribution of every branch per internships
         */

        if (poeManager.getDAstats() != null)
            d1 = poeManager.getDAstats();

        if (poeManager.getSIstats() != null)
            d2 = poeManager.getSIstats();

        if (poeManager.getRASstats() != null)
            d3 = poeManager.getRASstats();

        pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("DA", d1),
                        new PieChart.Data("SI", d2),
                        new PieChart.Data("RAS", d3));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Distribuição de estágios/projetos por ramos");

        /**
         * Creattion of a bars graphgic with the top5 professors with the most associations
         */

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("Número de associações")));
        xAxis.setLabel("Docentes");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Número absoluto");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparação entre docentes");

        List<Professor> professorsStats = poeManager.getProfessorsStats();

        XYChart.Series<String, Number> series1 = new XYChart.Series<>(), series2 = new XYChart.Series<>(), series3 = new XYChart.Series<>(), series4 = new XYChart.Series<>(), series5 = new XYChart.Series<>();

        if (professorsStats.size() > 0) {
            series1.setName(professorsStats.get(0).getName());
            series1.getData().add(new XYChart.Data<>("Número de associações", professorsStats.get(0).getnAssociations()));
        }

        if (professorsStats.size() > 1) {
            series2.setName(professorsStats.get(1).getName());
            series2.getData().add(new XYChart.Data<>("Número de associações", professorsStats.get(1).getnAssociations()));
        }

        if (professorsStats.size() > 2) {
            series3.setName(professorsStats.get(2).getName());
            series3.getData().add(new XYChart.Data<>("Número de associações", professorsStats.get(2).getnAssociations()));
        }

        if (professorsStats.size() > 3) {
            series4.setName(professorsStats.get(3).getName());
            series4.getData().add(new XYChart.Data<>("Número de associações", professorsStats.get(3).getnAssociations()));
        }

        if (professorsStats.size() > 4) {
            series5.setName(professorsStats.get(4).getName());
            series5.getData().add(new XYChart.Data<>("Número de associações", professorsStats.get(4).getnAssociations()));
        }

        barChart.getData().addAll(series1, series2, series3, series4, series5);

        // putting every graphic in the right border

        sideVB = new VBox(chart, barChart);
        sideVB.setAlignment(Pos.CENTER);
        sideVB.setSpacing(10);
        this.setRight(sideVB);

        this.setVisible(true);

    }

}
