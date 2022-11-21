package com.company;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.rgb;

public class TicketSelectionController { // String symbol = ((Pane)mouseEvent.getSource()).getId().replace("btn","");


    @FXML
    private AnchorPane achorP;

    @FXML
    private ResourceBundle resources;
    @FXML
    private Label lblPlace;
    @FXML
    private URL location;

    @FXML
    private Pane btnF1;
    @FXML
    private Pane btnA1;

    @FXML
    private Pane btnA2;

    @FXML
    private Pane btnA3;

    @FXML
    private Pane btnA4;

    @FXML
    private Pane btnA5;

    @FXML
    public void onPlaceClicked(javafx.scene.input.MouseEvent mouseEvent) {
        String value = ((Pane)mouseEvent.getSource()).getId().replace("btn","");
        System.out.println("ВЫ выбрали место " + value);
        lblPlace.setText(value);
    }
    @FXML
    void initialize() throws IOException {
        // Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        DBConnect dbConnect = new DBConnect();
        dbConnect.username = "PetrovR";
        dbConnect.password = "PetrovR";
        try {
            dbConnect.ConnectToDB();
            String SQL = "SELECT * FROM public.reservaton \n" +
                    "\tJOIN public.seats ON public.reservaton.id_seats = public.seats.id_seats;";//"Select id_seats FROM public.seats \n" +"WHERE \"Horizontal\"='A'and vertical = 5;

            ResultSet rsF = dbConnect.connection.createStatement().executeQuery(SQL);
            while (rsF.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rsF.getMetaData().getColumnCount(); i++) {
                    row.add(rsF.getString(i));
                }
                System.out.println("Загружены номера мест: " + row.get(5) + " Загружены заняты ли: " + row.get(6));
                if (row.get(2).equals("t")) {
                    System.out.println("ЗАНЯТО");
                    String str = "btn" + row.get(5) + row.get(6);

                    //    Pane pane = (Pane) page.lookup("#" + str);
                //    if (pane != null) {
                  //      pane.setStyle(("-fx-background-color: RED"));
                //    }
                } else {
                    String str = "btn" + row.get(5) + row.get(6);
               //     Pane pane = (Pane) page.lookup("#" + str);
               //     if (pane != null) {
             //           pane.setStyle(("-fx-background-color: White"));
              //      }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }
}
