package com.company;

import com.company.Utils.Map;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        DBConnect dbConnect = new DBConnect();
        Scanner scanner = new Scanner(System.in);

        System.out.println("������� �����:");
        dbConnect.username = scanner.nextLine();

        System.out.println("������� ������:");
        dbConnect.password = scanner.nextLine();

        dbConnect.ConnectToDB();

    }
}
