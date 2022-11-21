package com.company;

import com.company.Utils.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//MainFormController mainFormController = new MainFormController();
        Application.launch(args);
        /*  DBConnect dbConnect = new DBConnect();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¬ведите логин:");
        dbConnect.username = scanner.nextLine();

        System.out.println("¬ведите пароль:");
        dbConnect.password = scanner.nextLine();

        dbConnect.ConnectToDB();
        //dbConnect.dbExecuteQuery("Select * from test");
        dbConnect.dbExecuteQuery("SELECT id_client, \"FIO\", \"Passport\", \"Num_Phone\", \"Login\", \"Password\"\n" +
                "\tFROM public.client;");*/
        //  \t  Ч —имвол табул€ции (в java Ц эквивалент четырех пробелов);
        //  \n Ч —имвол перехода на новую строку;
        /* dbConnect.dbExecuteUpdate("INSERT INTO public.client(\n" +
                "\t \"FIO\", \"Passport\", \"Num_Phone\", \"Login\", \"Password\")\n" +
                "\tVALUES ( 'ѕетров', 'бб', 'Ћќл', 'SS', 'SSfasfafsa');");*/
        //SELECT PERSON_ID, FIRST_NAME, LAST_NAME FROM PERSON ORDER BY LAST_NAME   //Close Connection
        //    public static void dbDisconnect() throws SQLException {
        //        try {
        //            if (connection != null && !connection.isClosed()) {
        //               connection.close();
        //            }
        //        } catch (Exception e){
        //            throw e;
        //        }
        //    }
    }


    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));//MainForm.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Hello Airline");
        stage.setWidth(900);
        stage.setHeight(400);

        stage.show();

        /*
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
                System.out.println("«агружены номера мест: " + row.get(5) + " «агружены зан€ты ли: " + row.get(6));
                if (row.get(2).equals("t")) {
                    System.out.println("«јЌя“ќ");
                    String str = "btn" + row.get(5) + row.get(6);
                    Pane pane = (Pane) root.lookup("#" + str);
                    if (pane != null) {
                        pane.setStyle(("-fx-background-color: RED"));
                    }
                } else {
                    String str = "btn" + row.get(5) + row.get(6);
                    Pane pane = (Pane) root.lookup("#" + str);
                    if (pane != null) {
                        pane.setStyle(("-fx-background-color: White"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }*/
    }
}
