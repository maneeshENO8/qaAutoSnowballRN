package com.openkey.screens;

import com.openkey.utils.AllureReporting;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class RN_OpenKeyMobileAndroidAppLoginScreen extends BaseScreen {
    By pushNotificationHeader = By.xpath("//android.widget.TextView[@text='PUSH NOTIFICATIONS']");
    By btnNext = By.xpath("//android.widget.TextView[@text='NEXT']");
    By titleFindReservation = By.xpath("//android.widget.TextView[@text='FIND MY RESERVATION']");
    By txtFieldMobileNumber = By.xpath("//android.widget.EditText");
    By btnSubmit = By.xpath("//android.widget.TextView[@text='SUBMIT']");
    By otpNotification = By.xpath("//android.widget.TextView[contains(@text,'Verification Code for Project Snowball.')]");
    By titleVerificationScreen = By.xpath("//android.widget.TextView[@text='VERIFICATION']");
    By editBoxOtpInput = By.xpath("//android.widget.EditText");

    By btnRoomLock = By.xpath("//android.widget.ImageView[@index='0']");
    By allowLocation = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");

    String otpValue;

    public RN_OpenKeyMobileAndroidAppLoginScreen(AndroidDriver driver) throws IOException {
        super(driver);
    }



    public void verifyPushNotificationScreen() {

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(pushNotificationHeader)).isDisplayed());
        System.out.println("PUSH NOTIFICATION screen is displayed successfully");

    }

    public void verifyNextBtnAction() {

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(btnNext)).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnNext)).click();
        System.out.println("NEXT button is clicked successfully");
    }

    public void verifyFindMyReservationScreen() {

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(titleFindReservation)).isDisplayed());
        System.out.println("FIND MY RESERVATION screen is displayed successfully");
    }

    public void verifyRegistrdMobileNumEnter() throws InterruptedException, IOException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(txtFieldMobileNumber)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtFieldMobileNumber)).sendKeys("+91-9557221143");

    }

    public void verifyRegistrdMobileNumSubmit() throws InterruptedException, IOException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(btnSubmit)).click();
        System.out.println("SUBMIT button is clicked successfully");
        Thread.sleep(5000);

    }



    public void verifyVerificationScreen() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(titleVerificationScreen)).isDisplayed();
        System.out.println("VERIFICATION screen is displayed successfully");

    }

    public void readVerificationCode() {

        driver.openNotifications();

        Boolean otpMsgBody = wait.until(ExpectedConditions.visibilityOfElementLocated(otpNotification)).getText().contains("Project Snowball");

        Assert.assertTrue(otpMsgBody);

        System.out.println("Received text message for Verification code :"+" "+wait.until(ExpectedConditions.visibilityOfElementLocated(otpNotification)).getText());

        otpValue = wait.until(ExpectedConditions.visibilityOfElementLocated(otpNotification)).getText().split(" is")[0];

        System.out.println("Verification Code for app login is :"+" "+otpValue);
        driver.navigate().back();
    }

    public void verifyEnterVerificationCodeAction() throws InterruptedException, IOException {

        List<WebElement> otpEditTextArray = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editBoxOtpInput));

        for (int i = 0; i < otpValue.length(); i++) {
            otpEditTextArray.get(i).sendKeys(String.valueOf(otpValue.charAt(i)));
        }


        //Thread.sleep(2000);


    }


}
