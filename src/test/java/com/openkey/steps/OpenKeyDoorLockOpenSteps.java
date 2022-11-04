package com.openkey.steps;

import com.openkey.setups.CapabilitiesManager;
import com.openkey.setups.YamlConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

//import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class OpenKeyDoorLockOpenSteps extends BaseSteps {


    @Before

    public void setups() throws IOException {

        setupScreens(driver);

    }






    @When("^Guest clicks on Key Icon on My Key Screen$")
    public void guestClicksOnKeyIconOnMyKeyScreen() throws InterruptedException {
        openDoorLockScreen.clickMyKey();
    }

    @Then("Permissions should be allowed")
    public void permissionsShouldBeAllowed() throws InterruptedException {
        openDoorLockScreen.accessGrantedCheck();
    }

    @And("^Guest door lock should open$")
    public void verifyDoorOpens() throws InterruptedException {
        openDoorLockScreen.verifyDoorOpen();

    }

    @And("message this key unlocks your room should display")
    public void messageThisKeyUnlocksYourRoomShouldDisplay() {
        String message = openDoorLockScreen.verifyMessage();
        Assert.assertEquals(message, "This key unlocks your room and common areas.");
    }
    @When("I click the main key for given number of times")
    public void iClickTheMainKeyForGivenNumberOfTimes() throws InterruptedException {
        openDoorLockScreen.myKeyClickMultipleTimes(CapabilitiesManager.lockCounter);
    }

    @Then("it should open the lock successfully each time")
    public void itShouldOpenTheLockSuccessfullyEachTime() throws InterruptedException {

    }
}
