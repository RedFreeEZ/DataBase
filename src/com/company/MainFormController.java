package com.company;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainFormController {
        String log;
        String pas;
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button authSigInButton;

        @FXML
        private Button loginSigUpButton;

        @FXML
        private TextField login_field;

        @FXML
        private TextField password_field;

        @FXML
        void initialize() {

                authSigInButton.setOnAction(actionEvent -> {
                        String loginText = login_field.getText().trim();
                        String passwordText = password_field.getText().trim();

                        System.out.println("В логине " + loginText + " В пароле " + passwordText);

                        if (!loginText.equals("") && !passwordText.equals("")) {
                                loginUser();


                        } else System.out.println("Пустой логин или пароль");

                });
                loginSigUpButton.setOnAction(actionEvent -> {
                        System.out.println("Вы нажали на регистрацию");
                        loginSigUpButton.getScene().getWindow().hide();
                        openNewScene("/com/company/Registration.fxml");

                });
        }

        private void loginUser() {
                DBConnect dbConnect = new DBConnect();
                dbConnect.username = "PetrovR";
                dbConnect.password = "PetrovR";
                try {
                        dbConnect.ConnectToDB();
                        String SQL = "SELECT * from public.client";
                        ResultSet rs = dbConnect.connection.createStatement().executeQuery(SQL);
                        while (rs.next()) {
                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                        row.add(rs.getString(i));
                                }
                                if (row.get(4).equals(login_field.getText().trim()) && row.get(5).equals(password_field.getText().trim())) {
                                        log = row.get(4);
                                        pas = row.get(5);
                                            System.out.println("Найден логин: " + log + " Найден пароль: " + pas);
                                        openNewScene("/com/company/TicketSearch.fxml");
                                        authSigInButton.getScene().getWindow().hide();
                                }

                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error on Building Data");
                }
        }
        
        public void openNewScene(String window) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(window));

                try {
                        loader.load();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
        }

}

