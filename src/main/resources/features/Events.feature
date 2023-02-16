Feature: Gembook -> News Feed -> Events Section

  Background: User Login into the Gembook Application
    When User clicks on signIn Button
    Then User enters the "username"
    Then User enters the "password"
    And User logins into the application

  Scenario: Verify if user is logged into the application
    Then Verify user is logged into the application or not
