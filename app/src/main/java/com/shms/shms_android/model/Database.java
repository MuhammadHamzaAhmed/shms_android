package com.shms.shms_android.model;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.widget.Toast;


import com.shms.shms_android.view.ForgotPassword;

import java.sql.*;

public class Database {

    private final static String SERVER = "mysql-78213-0.cloudclusters.net";
    private final static int PORT = 19493;
    private final static String DBNAME = "shms";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "BAuupcgf";
    private static String URL;

    private Connection con;
    private static Database db;

    @SuppressLint("DefaultLocale")
    private Database() throws Exception{
            URL = String.format("jdbc:mysql://%s:%d?user=%s&password=%s&characterEncoding=latin1&useConfigs=maxPerformance", SERVER, PORT, USERNAME, PASSWORD);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL);

            Statement stmt = con.createStatement();
            String sqlusedb = "use " + DBNAME;
            stmt.executeUpdate(sqlusedb);
    }

    public static Database getDatabase() throws Exception{
        if(db == null){
            db = new Database();
        }
        return db;
    }

    public Connection getCon() {
        return con;
    }
}
