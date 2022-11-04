@OpenKeyDoorLockReactNativeApp
Feature: Open Door Lock using issued mobile key in OpenKey React Native App in Android Device

  Scenario: Open Door Lock using OpenKey Mobile App in Android Device
  As a guest I should be able to open door lock using issued mobile key in OpenKey mobile app when I login to OpenKey app in Android Device
    When Guest clicks on Key Icon on My Key Screen
    Then Permissions should be allowed
    And Guest door lock should open
    And message this key unlocks your room should display

Scenario: open lock multiple times in a series
  As a guest I am opening the door multiple times at regular interval
  When I click the main key for given number of times
  Then it should open the lock successfully each time
