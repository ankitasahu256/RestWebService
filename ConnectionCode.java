/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myorg.jdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author krawler
 */
public class ConnectionCode {

    Connection conn = null;
    Scanner sc = new Scanner(System.in);
    char ch;

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "root", "");
        return conn;
    }

    public void insert() throws SQLException, ClassNotFoundException {
        do {
            EmployeePojo e=input();
            PreparedStatement pstmt = connect().prepareStatement("insert into emptable values(?,?)");
            pstmt.setInt(1, e.getEmpId());
            pstmt.setString(2, e.getEmpName());
            pstmt.executeUpdate();
            System.out.println("Data Added successfully");
            System.out.println("Do you want to continue");
            ch = sc.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }

    public EmployeePojo input() {
        System.out.println("Enter Employee id");
        int empId = sc.nextInt();
        System.out.println("Enter Employee Name");
        String empName = sc.next();

        EmployeePojo e = new EmployeePojo();
        e.setEmpId(empId);
        e.setEmpName(empName);
        return e;
    }
}
