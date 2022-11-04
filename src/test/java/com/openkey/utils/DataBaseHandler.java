package com.openkey.utils;

import com.openkey.screens.OpenKeyDoorLockScreen;
import com.openkey.setups.CapabilitiesManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;

import java.sql.*;
import java.util.Calendar;


public class DataBaseHandler extends CapabilitiesManager {

  static Connection con;

    private static PreparedStatement pstmt;

    public static String DB_URL="jdbc:mysql://localhost/openkeytestrun";
    public static String DB_USER="root";

    public static String DB_PASSWORD="@This4now";
    String dbClass = "com.mysql.cj.jdbc.Driver";

    public void setupConnection() throws ClassNotFoundException, SQLException {

    Class.forName(dbClass);
    con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    String query = "insert into `locksdata` (TotalRun,TotalPass,TotalFailed,timestamp) values (?,?,?,?)";

    pstmt= con.prepareStatement(query);

    pstmt.setInt(1,OpenKeyDoorLockScreen.totalNumberOfLockOpeningAttempts);

    pstmt.setInt(2,OpenKeyDoorLockScreen.lockOpenSuccessCount);

    pstmt.setInt(3,OpenKeyDoorLockScreen.lockOpenFailureCount);

    pstmt.setTimestamp(4,new Timestamp(Calendar.getInstance().getTime().getTime()));

    pstmt.executeUpdate();





}



}
