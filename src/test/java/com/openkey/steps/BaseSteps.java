package com.openkey.steps;

import com.openkey.screens.*;
import com.openkey.setups.CapabilitiesManager;
import io.appium.java_client.android.AndroidDriver;

public class BaseSteps extends CapabilitiesManager {
    protected RN_OpenKeyMobileAndroidAppLoginScreen openKeyMobileAndroidAppLoginScreen;
    protected RN_OpenKeyMobileAndroidAppLaunchScreen openKeyMobileAndroidAppLaunchScreen;

    public void setupScreens(AndroidDriver driver) {

        openKeyMobileAndroidAppLaunchScreen = new RN_OpenKeyMobileAndroidAppLaunchScreen(driver);
        openKeyMobileAndroidAppLoginScreen = new RN_OpenKeyMobileAndroidAppLoginScreen(driver);


    }

}
