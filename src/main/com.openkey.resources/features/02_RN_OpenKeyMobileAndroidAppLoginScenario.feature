@AppLoginRNAndroidApp
Feature: Verify app login after creating session for a Guest through Openkey Host portal

  Scenario: As a guest I should be able to login into the RN Snowball app

    Given User is at Push Notifications Screen
    When User clicks 'NEXT' over Push Notifications Screen
    Then User should be presented with Find My Reservation Screen

    When User enters registered mobile number and clicks 'SUBMIT'
    Then  VERIFICATION Screen should be displayed

    When User receives the verification code
    And  User Enters received verification code
    Then Verification Code should be validated successfully

