package com.company;

import com.company.Utils.Map;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.rowset.CachedRowSetImpl;

public class DBConnect {
   public String str;

    //Connection
    public static Connection connection = null;

    public String jbdcURL = "jdbc:postgresql://46.229.214.241:5432/Airline";
    public String username;
    public String password;

    public Connection ConnectToDB() throws IOException, SQLException {
        try {
            Connection connection = DriverManager.getConnection(jbdcURL,username,password);
            this.connection = connection;
            System.out.println("Connect");
      //      connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error to connect");
        }
        finally {
      //      connection.close();
        }
        return connection;

    }


    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            ConnectToDB();
            System.out.println("Select statement: " + queryStmt + "\n");
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException | IOException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            connection.close();
        }
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            ConnectToDB();
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
            System.out.println("Запрос отправлен: \n" + sqlStmt + " \n ");
        } catch (SQLException | IOException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);

        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }

            //Close connection
            connection.close();
        }
    }

    public void signUpUser(User user) {
     /*    String insert = "INSERT INTO "+Const.UserTable+"("+
                 Const.UsersLogin+","+Const.UsersPass+")"+
                 "VALUES(?,?)";
         PreparedStatement prSt = ConnectToDB().prepareStatement(insert)
        prSt.setString(1,);
        prSt.setString(2,);
        try {
           prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prSt.setString(1,);*/
    }
}
