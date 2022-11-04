package com.openkey.steps;

import com.openkey.utils.AllureReporting;
import com.openkey.utils.HooksManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;




public class RN_OpenKeyAppLoginSteps extends BaseSteps {



    @Before

    public void setups() throws IOException {

        setupScreens(driver);



    }



    @Given("^User is at Push Notifications Screen$")

    public void user_is_at_push_notifications_screen() throws IOException {

        openKeyMobileAndroidAppLoginScreen.verifyPushNotificationScreen();

       hooksManager.stepsScreenshots();
    }





    @When("^User clicks 'NEXT' over Push Notifications Screen$")

    public void user_clicks_next_over_push_notifications_screen() throws IOException {

        openKeyMobileAndroidAppLoginScreen.verifyNextBtnAction();

        hooksManager.stepsScreenshots();


    }

    @Then("^User should be presented with Find My Reservation Screen$")

    public void user_should_be_presented_with_find_my_reservation_screen() throws IOException {

        openKeyMobileAndroidAppLoginScreen.verifyFindMyReservationScreen();
        hooksManager.stepsScreenshots();

    }

    @When("^User enters registered mobile number and clicks 'SUBMIT'$")

    public void user_enters_registered_mobile_number_and_clicks_submit() throws InterruptedException, IOException {

        openKeyMobileAndroidAppLoginScreen.verifyRegistrdMobileNumEnter();
        hooksManager.stepsScreenshots();

        openKeyMobileAndroidAppLoginScreen.verifyRegistrdMobileNumSubmit();
        hooksManager.stepsScreenshots();

    }

    @Then("^VERIFICATION Screen should be displayed$")

    public void verification_screen_should_be_displayed() throws IOException {

        openKeyMobileAndroidAppLoginScreen.verifyVerificationScreen();

        hooksManager.stepsScreenshots();
    }

    @When("^User receives the verification code$")

    public void user_receives_the_verification_code() throws IOException {

        openKeyMobileAndroidAppLoginScreen.readVerificationCode();
        hooksManager.stepsScreenshots();


    }

    @And("^User Enters received verification code$")

    public void user_enters_received_verification_code() throws InterruptedException, IOException {

        openKeyMobileAndroidAppLoginScreen.verifyEnterVerificationCodeAction();

        hooksManager.stepsScreenshots();


    }

    @Then("^Verification Code should be validated successfully$")

    public void verification_code_should_be_validated_successfully() throws IOException {

        System.out.println("VERIFICATION CODE is entered successfully");
        hooksManager.stepsScreenshots();
        System.out.println(HooksManager.stepName);

    }


}
