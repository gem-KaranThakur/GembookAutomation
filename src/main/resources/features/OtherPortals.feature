Feature: Gembook -> OtherPortals

  Background: User Login into the Gembook Application
    When User clicks on signIn Button
    Then User enters the "username"
    Then User enters the "password"
    And User logins into the application
    Then Verify user is logged into the application or not

  Scenario Outline: User navigates to the <tabName>
    And User navigates to tab "<tabName>"
    Examples:
      | tabName    |
      | Clubs      |
      | News Feeds |

   Scenario: Verify the presence of Other Portals
     Given Check if Side bar is present
     Then Verify if Other Portals is present

   Scenario: Verify the functionality of Other Portals dropdown
     Given Check if Side bar is present
     When Check if all sub items are present
     Then User clicks on the Other Portals menu item
     Then check if sub menu items are gone

   Scenario: Verify the Other Portals arrow icon functionality
     Given Check if Side bar is present
     Then Verify if Other Portals is present
     When Check if all sub items are present
     Then Verify the position of other Portals icon
     Then User clicks on the Other Portals menu item
     Then Verify the position of other Portals icon
 @Test
  Scenario Outline: Verify the tooltip of Other Portals sub menu items
    Given Check if Side bar is present
    Then Verify if Other Portals is present
    When Check if all sub items are present
    Then Verify the tooltip of "<desiredItem>" having "<tooltip>" as tooltip
    Examples:
    |desiredItem| | tooltip|
    |Azure|    | Task tracker tool for multiple projects in Gemini|








