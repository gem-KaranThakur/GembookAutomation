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

  @OtherPortals
   Scenario: Gembook_OtherPortals_OtherPortalsPresence
     Given Check if side bar is present
     Then Check if Other Portals is present

  @OtherPortals
   Scenario: Gembook_OtherPortals_OtherPortalsItemsVerification
     Given Check if side bar is present
     When Check if all sub items are present
     Then User clicks on the Other Portals menu item
     Then check if sub menu items are gone

  @OtherPortals
   Scenario: Gembook_OtherPortals_ArrowIcon
     Given Check if side bar is present
     Then Check if Other Portals is present
     When Check if all sub items are present
     Then Verify the position of other Portals icon
     Then User clicks on the Other Portals menu item
     Then Verify icon position

  @OtherPortals
  Scenario Outline: Gembook_OtherPortals_TooltipVerification
    Given Check if side bar is present
    Then Check if Other Portals is present
    When Check if all sub items are present
    Then Verify the tooltip of "<desiredItem>" having "<tooltip>" as tooltip
    Examples:
    |desiredItem| | tooltip|
    |Azure|    | Task tracker tool for multiple projects in Gemini|
    |Jenkins|  | Gemini internal build tool                       |
    |Service Desk|  |Ticketing tool raise IT/Admin/Accounts/HR issues, requests|







