package com.openkey.steps;

import com.openkey.utils.HooksManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import java.io.IOException;
import java.lang.annotation.Annotation;

//import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class RN_OpenKeyAppLaunchSteps extends BaseSteps {


    Annotation anno = RN_OpenKeyAppLaunchSteps.class.getAnnotation(Given.class);




    @Before

    public void setups() throws IOException {

        setupScreens(driver);

    }



    @Given("^RN Snowball App is installed in the device$")

    public void RN_snowball_app_is_installed_in_the_device() {


        openKeyMobileAndroidAppLaunchScreen.verifyIfAppInstalled();


    }


    @When("^App is launched$")

    public void app_is_launched() {

        System.out.println("App is launched Successfully");

    }

    @Then("^Splash screen should appear$")

    public void splash_screen_should_appear() throws IOException {


        openKeyMobileAndroidAppLaunchScreen.verifySplashScreen();
        hooksManager.stepsScreenshots();

    }




}
