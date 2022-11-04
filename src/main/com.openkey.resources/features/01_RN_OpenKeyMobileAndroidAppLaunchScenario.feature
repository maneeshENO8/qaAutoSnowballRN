@AppLaunchRNAndroidApp
Feature: Verify RN Snowball app launch

  Scenario: As a guest I should be able to launch the RN Snowball app in Android Device

    Given RN Snowball App is installed in the device
    When App is launched
    Then Splash screen should appear

