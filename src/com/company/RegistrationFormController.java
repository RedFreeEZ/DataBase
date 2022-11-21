package com.company;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;


import com.company.Utils.EmailUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class RegistrationFormController {
    private final int min = 0;
    private final int max = 999999;
    private Random randomCode = new Random();
    private String Code;
    String log;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button getCodeButton;

    @FXML
    private Button SigInButton;

    @FXML
    private Button loginSigUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private TextField verification_code_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField check_password_field;

    @FXML
    void initialize() {
        getCodeButton.setOnAction(actionEvent -> {
            getVerificationCode();
        });

        SigInButton.setOnAction(actionEvent -> {
            insertNewUser();
        });

        loginSigUpButton.setOnAction(actionEvent -> {
            System.out.println("Вы нажали на войти");

            loginSigUpButton.getScene().getWindow().hide();
            openNewScene("/com/company/MainForm.fxml");

        });
    }

    private void getVerificationCode() {
        if (!login_field.getText().trim().equals("")) {
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
                    if (row.get(4).equals(login_field.getText().trim())) {
                        // System.out.println("Row [1] added " + row.get(4) + " ");
                        log = row.get(4);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }

            if (!log.equals(login_field.getText().trim())) {
                final String fromEmail = "roman.pyoter@yandex.ru"; //requires valid gmail id
                final String password = "bkdxhhnydkhmbxxs"; // correct password for gmail id
                String toEmail = login_field.getText().trim(); // can be any email id vepiwih484@klblogs.com
                System.out.println("SSLEmail Start");
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.yandex.ru"); //SMTP Host
                props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
                props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
                props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
                props.put("mail.smtp.port", "465"); //SMTP Port

                Authenticator auth = new Authenticator() {
                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                };
                Code = String.valueOf(randomCode.nextInt(max - min) + min);
                Session session = Session.getDefaultInstance(props, auth);
                EmailUtil.sendEmail(session, toEmail, "Вам пришел код ->", Code);
            } else System.out.println("ПОЧТА уже ЗАРЕГЕСТИРОВАНА");
        } else System.out.println("ПОЧТА ПУСТАЯ");
    }

    private void insertNewUser() {
        DBConnect dbConnect = new DBConnect();

        String login = login_field.getText().trim();
        String verCode = verification_code_field.getText().trim();
        String password = password_field.getText().trim();
        String checkPassword = check_password_field.getText().trim();

        System.out.println("login " + login + " verCode " + verCode + " Code " + Code + " password " + password + " checkPassword " + checkPassword);
        if (Objects.equals(Code, verCode) && password.equals(checkPassword)) {
            System.out.println("Код аунтификации правилен и пароли совпадают");
            try {
                dbConnect.username = "PetrovR";
                dbConnect.password = "PetrovR";
                dbConnect.dbExecuteUpdate(
                        "INSERT INTO public.client(\n" +
                                "\t \"FIO\", \"Passport\", \"Num_Phone\", \"Login\", \"Password\")\n" +
                                "\tVALUES " + "( 'Петров', 'бб', 'ЛОл'" + "," + " '" + login + "'" + "," + " '" + password + "'" + ")" + ";");
                System.out.println();
                Code = "";
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (!Objects.equals(Code, verCode) && password.equals(checkPassword)) {
            System.out.println("Неверный код верификации!!!");
        } else
            System.out.println("Пароли не совпадают!!!");
        //System.out.println("Не совпало "+Code +" "+ verCode+" ПАРОЛИ "+password +" "+ checkPassword);
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
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}

