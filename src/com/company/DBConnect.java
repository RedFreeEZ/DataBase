package com.company;

import com.company.Utils.Map;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {




    public String jbdcURL = "jdbc:postgresql://46.229.214.241:5432/postgres";
    public String username;
    public String password;
    public void ConnectToDB() throws IOException {
        try {
            Connection connection = DriverManager.getConnection(jbdcURL,username,password);

            System.out.println("Connect");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error to connect");
        }
    }

}
