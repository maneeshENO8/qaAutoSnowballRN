package com.openkey.utils;

import com.google.gson.JsonObject;
import com.openkey.screens.OpenKeyDoorLockScreen;
import com.openkey.setups.CapabilitiesManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;
import java.util.Properties;

public class AllureReporting extends CapabilitiesManager {

    String path;
    Properties pr;

    File screenshotFile;

    /*Checking if allure-results is not empty and clean it*/

    public void cleanUpAllureDirectory() throws IOException {

        if (new File("allure-results").list().length>0) {

            FileUtils.cleanDirectory(new File("allure-results"));
        }
    }

    /* Writing Data into the Environment variable */
    public void envFileWriter() throws IOException {

        path = "allure-results\\environment.properties";

        FileOutputStream outputStream = new FileOutputStream(path, true);

        pr = new Properties();

        pr.put("Total number of times the test executed", String.valueOf(OpenKeyDoorLockScreen.totalNumberOfLockOpeningAttempts));
        pr.put("Number of times lock opens successfully : ", String.valueOf(OpenKeyDoorLockScreen.lockOpenSuccessCount));
        pr.put("Number of times lock opening failed  : ",  String.valueOf(OpenKeyDoorLockScreen.lockOpenFailureCount));
        pr.put("Device Name :", deviceName);
        pr.put("deviceType : ", platformName);
        pr.put("platformVersion : ", platformVersion);
        pr.store(outputStream,"Allure Report Results");

    }

    public void executorFileWriter() throws IOException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name",System.getenv("USERNAME"));
        jsonObject.put("buildName",appPackage);
        jsonObject.put("type","LocalRun");

        FileWriter fr = new FileWriter("allure-results\\executor.json");
        fr.write(jsonObject.toString());
        fr.flush();

    }

    public void methodtakescreenShot() throws IOException {

        screenshotFile  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

      File targetFile=new File("allure-results\\"+System.currentTimeMillis()+".png");

      FileUtils.copyFile(screenshotFile,targetFile);

    }

public void addScreenshot(String name) throws FileNotFoundException {

        Allure.addAttachment(name, new FileInputStream(screenshotFile));

    }




}
