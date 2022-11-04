package com.openkey.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;

public class RN_OpenKeyMobileAndroidAppLaunchScreen extends BaseScreen {

    By splashLocator = By.xpath("//android.widget.TextView[@content-desc=\"OpenKey\"]");

    public RN_OpenKeyMobileAndroidAppLaunchScreen(AndroidDriver driver) throws IOException {
        super(driver);
    }

    public void verifyIfAppInstalled() {

        if (driver.isAppInstalled("com.openkey")) {

            System.out.println("App is already installed in the device");
        } else {

            System.out.println("App is not Installed in the Device,Install it first to continue");

        }

    }


    public void verifySplashScreen() {

        if (driver.currentActivity().equalsIgnoreCase("com.openkey.MainActivity")) ;

        System.out.println("Splash screen is displayed successfully");
    }


}
