package com.qa.gembook.GembookUtilities;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.Locators.SideBar_Locators;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OtherPortalsUtils extends DriverAction {
    @When("User navigates to tab {string}")
    public void navigateToScreen(String tabName) {
        if (isExist(SideBar_Locators.navigationTab(tabName))) {
            click(SideBar_Locators.navigationTab(tabName));
            GemTestReporter.addTestStep("Validate click on " + tabName + " is performed", "Clicked performed successfully", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate " + tabName + " is visible", tabName + " is not visible on UI", STATUS.FAIL, takeSnapShot());
        }
    }

    public void clickForcefully(By locator) {
        if (isExist(locator)) {
            JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
            executor.executeScript("arguments[0].click();", getElement(locator));
            GemTestReporter.addTestStep("Verify click on element is performed", "Click performed successfully", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify click on element is performed", "Click is not performed", STATUS.FAIL, takeSnapShot());
    }

    public static boolean isElementClickable(By locator, int duration) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(duration));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            GemTestReporter.addTestStep("Verify element is clickable", "Validation passed. Element is clickable", STATUS.PASS, takeSnapShot());
            return true;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Verify element is clickable", "Validation failed.", STATUS.FAIL, takeSnapShot());
            return false;
        }
    }
    public static boolean isElementVisible(By locator)
    {
        return isExist(locator) && DriverManager.getWebDriver().findElement(locator).isDisplayed();
    }

    public static void waitForElementToAppear(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean isElementDisplayed(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    @Then("Verify the tooltip of {string} having {string} as tooltip")
    public void verifyTheTooltipOfHavingAsTooltip(String subMenuItem, String menuItemToolTip) {
        String tooltip = DriverManager.getWebDriver().findElement(SideBar_Locators.navigationTab(subMenuItem, "/ancestor::a")).getAttribute("title");
        hoverOver(SideBar_Locators.navigationTab(subMenuItem));
//        String tooltip = getAttributeName(getElement(SideBar_Locators.navigationTab(subMenuItem)), "title")
        if (tooltip.equalsIgnoreCase(menuItemToolTip)) {
            GemTestReporter.addTestStep("Verify tooltip of " + subMenuItem + " sub menu item", "tooltip are same", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify tooltip of " + subMenuItem + " sub menu item", "Tooltip are different", STATUS.FAIL, takeSnapShot());
    }
    @Then("Verify if user is navigated to other tab")
    public void verifyUserIsNavigated()
    {
        String currentHandle = DriverManager.getWebDriver().getWindowHandle();

        for (String handle : DriverManager.getWebDriver().getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                DriverManager.getWebDriver().switchTo().window(handle);
                break;
            }
        }
        DriverManager.getWebDriver().switchTo().window(currentHandle);
    }
}
