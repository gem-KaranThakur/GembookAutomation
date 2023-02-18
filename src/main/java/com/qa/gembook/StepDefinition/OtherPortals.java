package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gembook.Locators.SideBar_Locators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OtherPortals extends DriverAction {

    @Given("Check if side bar is present")
    public void isSidebarPresent() {
        if (isExist(SideBar_Locators.sideBarIcon)) {
            GemTestReporter.addTestStep("Verify presence of SideBar", "SideBar is Present", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify presence of SideBar", "SideBar is not Present", STATUS.FAIL, takeSnapShot());
    }

    @Then("Check if Other Portals is present")
    public void isOtherPortalsPresent() {
        if (isExist(SideBar_Locators.otherPortals) && OtherPortalsUtils.isElementVisible(SideBar_Locators.otherPortals) && isExist(SideBar_Locators.otherPortalsIcon)) {
            GemTestReporter.addTestStep("Verify presence of Other Portals", "Other Portals is Present in side bar", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify presence of Other Portals", "Other Portals is not Present in side bar", STATUS.FAIL, takeSnapShot());
        waitSec(3);
    }

    @Then("User clicks on the Other Portals menu item")
    public void clickOnOtherPortals() {
        //we should make it interface
        waitUntilElementAppear(SideBar_Locators.otherPortals, 4);
        if (OtherPortalsUtils.isElementClickable(SideBar_Locators.otherPortals, 5)) {
            click(SideBar_Locators.otherPortals);
//            GemTestReporter.addTestStep("Click on Other Portals", "Click action performed successfully", STATUS.PASS, takeSnapShot());

        } else
            GemTestReporter.addTestStep("Click on Other Portals", "Click action is not performed", STATUS.FAIL, takeSnapShot());
    }

    @Then("Verify the position of other Portals icon")
    public void PositionOfOtherPortalsIcon() {
        if (SideBar_Locators.arrowIcon.toString().contains("rotate-90") && isExist(SideBar_Locators.subItemJenkins)) {
            GemTestReporter.addTestStep("Position of Other Portals icon", "Other Portals icon is pointed down", STATUS.PASS, takeSnapShot());
        } else if (!SideBar_Locators.arrowIcon.toString().contains("rotate-90") && !isExist(SideBar_Locators.subItemJenkins)) {
            GemTestReporter.addTestStep("Click on Other Portals", "Other Portals icon is pointed right", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Position of Other Portals icon", "Position of icon is incorrect", STATUS.FAIL, takeSnapShot());
        }

    }

    @Then("Check if all sub items are present")
    public void checkIfAllSubItemsArePresent() {
        List<String> subItems = Arrays.asList("Service Desk", "Jenkins", "Azure", "Contripoint", "MIS", "GreytHR", "Github", "Gem Wiki", "Athena", "LMS");
        List<WebElement> subItemsUI = getElements(SideBar_Locators.sidebarList);
        int startOfsubItem = 0;
        int start = 0;
        for (WebElement item : subItemsUI) {
            if (item.getText().equalsIgnoreCase("Other Portals")) {
                startOfsubItem = start;
                break;
            }
            start++;
        }
        List<String> subItemsVal = new ArrayList<>();
        for (int i = startOfsubItem + 1; i < subItemsUI.size(); i++) {
            subItemsVal.add(subItemsUI.get(i).getText());
        }
        if (subItemsVal.equals(subItems)) {
            GemTestReporter.addTestStep("Check for the elements of Other Portals", "All elements are present. List of elements present on ui contains " + subItemsVal + " items.", STATUS.PASS, takeSnapShot());

        } else
            GemTestReporter.addTestStep("Check for the elements of Other Portals", "All elements are not present", STATUS.FAIL, takeSnapShot());
    }

    @Then("check if sub menu items are gone")
    public void checkIfSubMenuItemsAreGone() {
        if (!isExist(SideBar_Locators.subItemJenkins)) {
            GemTestReporter.addTestStep("Check for the elements of Other Portals", "All elements are Hidden", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Check for the elements of Other Portals", "All elements are not Hidden", STATUS.FAIL, takeSnapShot());
    }


    @Then("Verify icon position")
    public void verifyIconPosition() {
        if(!isExist(SideBar_Locators.arrowIcon))
        {
            GemTestReporter.addTestStep("Check Position of Other Portals icon", "Other Portals icon is pointed right", STATUS.PASS, takeSnapShot());
        }
        else
            GemTestReporter.addTestStep("Check Position of Other Portals icon", "Other Portals icon is pointed ", STATUS.FAIL, takeSnapShot());

    }
}
