package com.openkey.screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class OpenKeyDoorLockScreen extends BaseScreen {
    public static int counter = 0;
   public static int lockOpenSuccessCount = 0;
    public static int lockOpenFailureCount = 0;
    public static int totalNumberOfLockOpeningAttempts=0;

    /**
     * Mobile Elements
     */
    By btnRoomLock = By.xpath("//android.widget.ImageView[@index='0']");
    By allowAccess = By.id("com.android.permissioncontroller:id/permission_allow_button");
    By allowLocation = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    By txtElementOnMyKey = By.xpath("//android.widget.TextView[contains(@text,'This key unlocks your room and common areas')]");
    By doorOpenTxtMessage = By.xpath("//android.widget.TextView[contains(@text,'Your door is now unlocked.')]");
    //By unableToOpenLockPopUp = By.xpath("//android.widget.TextView[contains(@text,'UNABLE TO OPEN LOCK')]");
    //By okBtnOnFailure = By.xpath("//android.widget.TextView[contains(@text,'OKAY')]");

    By unableToOpenLockPopUp = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup");
    By okBtnOnFailure = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");

    String keyMessage;
    String msg;
    Boolean flag;

    public OpenKeyDoorLockScreen(AndroidDriver driver) throws IOException {
        super(driver);
    }

    /**
     * Actions
     */
    public void clickMyKey() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> lockUIElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnRoomLock));
        lockUIElements.get(2).click();
    }

    public void verifyDoorOpen() throws InterruptedException {
        //Thread.sleep(5000);
        try {
            if (driver.findElement(doorOpenTxtMessage).isDisplayed()) {
                msg = wait.until(ExpectedConditions.visibilityOfElementLocated(doorOpenTxtMessage)).getText();
                lockOpenSuccessCount = lockOpenSuccessCount + 1;
                counter = counter + 1;
                if (lockOpenSuccessCount == 1) System.out.println(msg + " for first time");
                else System.out.println(msg + "for : " + lockOpenSuccessCount + " times");

                Assert.assertEquals(msg, "Your door is now unlocked.");
            }

        } catch (Exception e) {
            //e.printStackTrace();
            //if(driver.findElement(okBtnOnFailure).isDisplayed()) {
            if (driver.findElement(okBtnOnFailure).isDisplayed()) {
                lockOpenFailureCount = lockOpenFailureCount + 1;
                if (lockOpenFailureCount == 1)
                    System.out.println("Unable to Open lock for " + lockOpenFailureCount + " time");
                else System.out.println("Unable to Open lock for " + lockOpenFailureCount + " times");
                    counter = counter + 1;
                    wait.until(ExpectedConditions.visibilityOfElementLocated(okBtnOnFailure)).click();
                }
        }
    }

    public void myKeyClickMultipleTimes(int numberOfTimes) throws InterruptedException {
        totalNumberOfLockOpeningAttempts=numberOfTimes;
        System.out.println("Clicking key multiple times");
        while (counter < numberOfTimes) {
            try {
                Thread.sleep(5000);
                clickMyKey();
                verifyDoorOpen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total number of attempts to open lock " + numberOfTimes);
        System.out.println("lock opened successfully " + lockOpenSuccessCount + " times");
        System.out.println("lock opening failed " + lockOpenFailureCount + " times");

    }

    public void accessGrantedCheck() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(allowAccess)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowAccess)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowLocation)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowLocation)).click();
    }


    public String verifyMessage() {
        //System.out.println("Message is :" + driver.findElement(txtElementOnLockOpen).getText());
        return driver.findElement(txtElementOnMyKey).getText();

    }
}
