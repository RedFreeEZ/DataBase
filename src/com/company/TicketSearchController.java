package com.company;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.*;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

import javax.sql.RowSet;

public class TicketSearchController {
    String s;
    String Fr;
    String To;
    ObservableList<String> FRVALUE = FXCollections.observableArrayList();
    ObservableList<String> TOVALUE = FXCollections.observableArrayList();
    ObservableList<String> TimeFRVALUE = FXCollections.observableArrayList();
    ObservableList<String> TimeTOVALUE = FXCollections.observableArrayList();
    ObservableList<String> Price = FXCollections.observableArrayList();
    int counter = 1;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grdP;
    @FXML
    private Label timeFr1;

    @FXML
    private Label timeFr2;

    @FXML
    private Label timeFr3;

    @FXML
    private Label timeFr4;

    @FXML
    private Label timeTo1;

    @FXML
    private Label timeTo2;

    @FXML
    private Label timeTo3;

    @FXML
    private Label timeTo4;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Label from1;

    @FXML
    private Label from2;

    @FXML
    private Label from3;

    @FXML
    private Label from4;
    @FXML
    private Label To1;

    @FXML
    private Label To2;

    @FXML
    private Label To3;

    @FXML
    private Label To4;

    @FXML
    private Label nextBtn;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFind;

    @FXML
    private TextField dataTextField;

    @FXML
    private ComboBox<?> fromComboBox;

    @FXML
    private ComboBox<?> toComboBox;
    @FXML
    private DatePicker datePicker;

    @FXML
    void initialize() {

        DBConnect dbConnect = new DBConnect();
        dbConnect.username = "PetrovR";
        dbConnect.password = "PetrovR";
        try {
            dbConnect.ConnectToDB();
            String SQL = "SELECT * FROM public.tickets \n" +
                    "\tJOIN public.\"transportation period\" ON public.tickets.id_ticket = public.\"transportation period\".id_transportatio_period;\n";
            ResultSet rsF = dbConnect.connection.createStatement().executeQuery(SQL);
            while (rsF.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rsF.getMetaData().getColumnCount(); i++) {
                    row.add(rsF.getString(i));
                }
                System.out.println("Загружены откуда: " + row.get(3) + " Загружены куда: " + row.get(4));
                Fr = row.get(3);
                To = row.get(4);
                List listFr = new ArrayList<>(Collections.singleton(Fr));
                List listTo = new ArrayList<>(Collections.singleton(To));
                fromComboBox.getItems().addAll(listFr);
                toComboBox.getItems().addAll(listTo);
                fromComboBox.getSelectionModel().select(0);
                toComboBox.getSelectionModel().select(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        btnFind.setOnAction(actionEvent -> {
            FRVALUE.clear();
            TOVALUE.clear();
            Price.clear();
            System.out.println("ВЫ НАЖАЛИ НА ВОЙТИ");
            String getFR = (String) fromComboBox.getValue();
            String getTo = (String) toComboBox.getValue();
            String Date = String.valueOf(datePicker.getValue());
            if (!getFR.equals("")  && !getTo.equals("") && Date != null) {
                try {
                    dbConnect.ConnectToDB();
                    String SQL = " SELECT * FROM public.tickets\n" +
                            "\tJOIN public.\"transportation period\" ON  public.tickets.\"Transportatio_period\" =public.\"transportation period\".id_transportatio_period \n" +
                            "\tWhere \"From\"=" + "'" + getFR + "'" + "\n" +
                            "\tand \"To\"=" + "'" + getTo + "'" + "\n" +
                            "\tand \"Departure_Date\"=" + "'" + Date + "'" + ";";
                    ResultSet rsF = dbConnect.connection.createStatement().executeQuery(SQL);
                    System.out.println(rsF);
                    while (rsF.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rsF.getMetaData().getColumnCount(); i++) {
                            row.add(rsF.getString(i));
                        }
                        if (Fr.equals(getFR) && To.equals(getTo)) {
                            FRVALUE.add(row.get(3));
                            TOVALUE.add(row.get(4));
                            TimeFRVALUE.add(row.get(10));
                            TimeTOVALUE.add(row.get(12));
                            Price.add(row.get(7));
                            Fr = row.get(3);
                            To = row.get(4);
                        } else {
                            System.out.println("НЕТ РЕЙСОВ");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error on Building Data");
                }
                System.out.println("FRVALUE "+FRVALUE.size()+" TOVALUE "+TOVALUE.size());
                if (FRVALUE.size()!=0&&TOVALUE.size()!=0&&TimeFRVALUE.size()!= 0&&TimeTOVALUE.size()!= 0){
                from1.setText(FRVALUE.get(0));
                To1.setText(TOVALUE.get(0));
                from2.setText(FRVALUE.get(1));
                To2.setText(TOVALUE.get(1));
                from3.setText(FRVALUE.get(2));
                To3.setText(TOVALUE.get(2));
                from4.setText(FRVALUE.get(3));
                To4.setText(TOVALUE.get(3));

                timeTo1.setText(TimeFRVALUE.get(0));
                timeFr1.setText(TimeTOVALUE.get(0));
                timeTo2.setText(TimeFRVALUE.get(1));
                timeFr2.setText(TimeTOVALUE.get(1));
                timeTo3.setText(TimeFRVALUE.get(2));
                timeFr3.setText(TimeTOVALUE.get(2));
                timeTo4.setText(TimeFRVALUE.get(3));
                timeFr4.setText(TimeTOVALUE.get(3));

                btn1.setText(Price.get(0));
                btn2.setText(Price.get(1));
                btn3.setText(Price.get(2));
                btn4.setText(Price.get(3));

                    grdP.addRow(FRVALUE.size());
                    }
                int s = TOVALUE.size();
                if (s != 0) {
                    for (int i = 0; i < 3; i++) {
                        TimeFRVALUE.add("Не найдено");
                        TimeTOVALUE.add("Не найдено");
                        Price.add("Не найдено");
                    }
                }
            } else {
                from1.setText("не найдено");
                To1.setText("не найдено");
                from2.setText("не найдено");
                To2.setText("не найдено");
                from3.setText("не найдено");
                To3.setText("не найдено");
                from4.setText("не найдено");
                To4.setText("не найдено");

                timeTo1.setText("не найдено");
                timeFr1.setText("не найдено");
                timeTo2.setText("не найдено");
                timeFr2.setText("не найдено");
                timeTo3.setText("не найдено");
                timeFr3.setText("не найдено");
                timeTo4.setText("не найдено");
                timeFr4.setText("не найдено");

                btn1.setText("не найдено");
                btn2.setText("не найдено");
                btn3.setText("не найдено");
                btn4.setText("не найдено");
            }

        });

        btn1.setOnAction(actionEvent -> {
            s = btn1.getId().replace("btn", "");
            openNewScene("/com/company/TicketSelection.fxml");
        });
        btn2.setOnAction(actionEvent -> {
            s = btn2.getId().replace("btn", "");
            openNewScene("/com/company/TicketSelection.fxml");
        });
        btn3.setOnAction(actionEvent -> {
            s = btn3.getId().replace("btn", "");
            openNewScene("/com/company/TicketSelection.fxml");
        });
        btn4.setOnAction(actionEvent -> {
            s = btn4.getId().replace("btn", "");
            openNewScene("/com/company/TicketSelection.fxml");
        });

    }

    @FXML
    public void onNextClicked(javafx.scene.input.MouseEvent mouseEvent) {
        String value = ((Label) mouseEvent.getSource()).getId();
        System.out.println("ВЫ выбрали место " + value + TOVALUE.size());
        //if (4>3)
        int s = TOVALUE.size();
        //1 + 1 =2

        int elem = counter * 4;

        System.out.println(counter);
        //if 4> 1*4
        if (s > elem) {
            timeTo1.setText(TimeFRVALUE.get(elem));
            timeFr1.setText(TimeTOVALUE.get(elem));

            timeTo2.setText(TimeFRVALUE.get(elem + 1));
            timeFr2.setText(TimeTOVALUE.get(elem + 1));

            timeTo3.setText(TimeFRVALUE.get(elem + 2));
            timeFr3.setText(TimeTOVALUE.get(elem + 2));

            timeTo4.setText(TimeFRVALUE.get(elem + 3));
            timeFr4.setText(TimeTOVALUE.get(elem + 3));
            btn1.setText(Price.get(elem));
            btn2.setText(Price.get(elem + 1));
            btn3.setText(Price.get(elem + 2));
            btn4.setText(Price.get(elem + 3));
            counter = counter + 1;
        }

    }

    @FXML
    public void onBackClicked(javafx.scene.input.MouseEvent mouseEvent) {
        String value = ((Label) mouseEvent.getSource()).getId();
        System.out.println("ВЫ выбрали место " + value);
        int s = TOVALUE.size();
        //1 + 1 =2

        int elem = counter * 4;

        System.out.println(counter);
        //if 4> 1*4
        if (s < elem) {// 8 - 8/2 = 2 8-3 = 7
            timeTo1.setText(TimeFRVALUE.get(elem-elem/2));
            timeFr1.setText(TimeTOVALUE.get(elem-elem/2));

            timeTo2.setText(TimeFRVALUE.get(elem - 3));
            timeFr2.setText(TimeTOVALUE.get(elem - 3));

            timeTo3.setText(TimeFRVALUE.get(elem - 2));
            timeFr3.setText(TimeTOVALUE.get(elem - 2));

            timeTo4.setText(TimeFRVALUE.get(elem - 1));
            timeFr4.setText(TimeTOVALUE.get(elem - 1));
            btn1.setText(Price.get(elem - elem/2));
            btn2.setText(Price.get(elem - 1));
            btn3.setText(Price.get(elem - 2));
            btn4.setText(Price.get(elem - 2));
            counter = counter + 1;
        } else {
        }


    }

    public void openNewScene(String window) {
        String getFR = (String) fromComboBox.getValue();
        String getTO = (String) toComboBox.getValue();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        DBConnect dbConnect = new DBConnect();
        dbConnect.username = "PetrovR";
        dbConnect.password = "PetrovR";
        try {
            String itog = String.valueOf(Integer.parseInt(s) * counter);
            dbConnect.ConnectToDB();
            String SQL = "SELECT * FROM public.reservaton JOIN public.seats ON public.reservaton.id_seats = public.seats.id_seats \n" +
                    "Where \"id_transportatio_period \" =" + "'" + itog + "';";
            System.out.println(SQL);
            ResultSet rsF = dbConnect.connection.createStatement().executeQuery(SQL);
            while (rsF.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rsF.getMetaData().getColumnCount(); i++) {
                    row.add(rsF.getString(i));

                }
                System.out.println("Загружены номера мест: " + row.get(5) + " Загружены заняты ли: " + row.get(6));
                if (row.get(2).equals("t") && getFR.equals(row.get(4)) && getTO.equals(row.get(5))) {
                    //   System.out.println("ЗАНЯТО");
                    String str = "btn" + row.get(7) + row.get(8);
                    Pane pane = (Pane) root.lookup("#" + str);
                    if (pane != null) {
                        pane.setStyle(("-fx-background-color: RED"));
                    }
                } else {
                    String str = "btn" + row.get(7) + row.get(8);
                    Pane pane = (Pane) root.lookup("#" + str);
                    if (pane != null) {
                        pane.setStyle(("-fx-background-color: White"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        stage.showAndWait();
    }

}
