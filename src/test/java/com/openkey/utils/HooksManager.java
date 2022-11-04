package com.openkey.utils;

import com.openkey.steps.RN_OpenKeyAppLaunchSteps;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;


import static com.openkey.setups.CapabilitiesManager.driver;

public class HooksManager implements ConcurrentEventListener {

    public static Scenario scenario;
    public static String stepName;


    @Before

    public void setUpScenario(Scenario scenario) {
        HooksManager.scenario = scenario;
    }


    public HooksManager() throws IOException {
    }


    public void stepsScreenshots() {

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", HooksManager.stepName + ":" +scenario.getStatus());
    }

    public EventHandler<TestStepStarted> stepHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            handleTestStepStarted(event);
        }

    };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepHandler);
    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep)event.getTestStep();
            stepName = testStep.getStep().getText();

        }


    }

    private void handleTestStepFinished( TestStepFinished event1) {
        if (event1.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep)event1.getTestStep();
            //step1Name = testStep.getStep().getText();

        }


    }






    }





