package com.qa.gembook.StepDefinition;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.qa.gembook.Locators.Login_Locators;
import com.qa.gembook.Locators.SideBar_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GembookUtils extends DriverAction {

    @When("User clicks on signIn Button")
    public void signIn() {
        if (!isExist(Login_Locators.signInBtn)) {
            refresh();
            waitSec(5);
        }
        waitUntilElementAppear(Login_Locators.signInBtn, 10);
        click(Login_Locators.signInBtn, "Click on Sign In Button", "Clicked Sign In Button"); // Click on Sign in Button
    }

    // @Rahul Tagra, @Deeksha Singh
    @Then("User enters the {string}")
    public void enterCredentials(String credentialType) throws IOException {
        List<String> browserWindows = new ArrayList<>(getWindowHandles()); // Get all browser windows
        switchToWindow(browserWindows.get(1)); // Switch focus to 2nd browser window
        waitUntilElementAppear(Login_Locators.credentials(credentialType), 10);
        if (credentialType.contains("username")) {
            typeText(Login_Locators.credentials(credentialType), readProperties(credentialType)); // Enter Username
        } else {
            typeText(Login_Locators.credentials(credentialType), "User enters the password", "Password Entered Successfully", readProperties(credentialType));
        }
        waitUntilElementAppear(Login_Locators.nextBtn, 10);
        click(Login_Locators.nextBtn,"Next button");
        waitSec(5);
    }

    @And("User logins into the application")
    public void login() {
        waitUntilElementAppear(Login_Locators.nextBtn, 10);
        getElement(Login_Locators.nextBtn).click(); // User clicks on yes Button
        List<String> browserWindows = new ArrayList<>(getWindowHandles());
        switchToWindow(browserWindows.get(0)); // Switch focus back to 1st window
        waitSec(5);
    }

    public String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader("src/main/resources/config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    // @Rahul Tagra, @Deeksha Singh
    @Then("Verify user is logged into the application or not")
    public void verifyLogin() {
        String expectedUrl = "https://gembook.geminisolutions.com/#/dashboard";
        if (getCurrentURL().contains(expectedUrl) && isExist(Login_Locators.gembookLogo) && isExist(Login_Locators.logoHeader) && isExist(Login_Locators.profileImage)) {
            GemTestReporter.addTestStep("Verify if User is logged into the application", "User logins into the Gembook application", STATUS.PASS, takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Verify if User is logged into the application", "User is unable to login into the Gembook application", STATUS.FAIL, takeSnapShot());
        }
    }

    //   @Karan @Katikay
    public void clickForcefully(By locator) {
        if (isExist(locator)) {
            JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
            executor.executeScript("arguments[0].click();", getElement(locator));
            GemTestReporter.addTestStep("Verify click on element is performed", "Click performed successfully", STATUS.PASS, takeSnapShot());
        } else
            GemTestReporter.addTestStep("Verify click on element is performed", "Click is not performed", STATUS.FAIL, takeSnapShot());
    }

    // @Karan @Kartikay
    public static boolean isElementClickable(By locator,int duration) {
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

    //    @Karan @Kartikay
//    @When("User navigates to tab {string}")
//    public void navigateToScreen(String tabName) {
//        if (isExist(SideBar_Locators.navigationTab(tabName))) {
//            click(SideBar_Locators.navigationTab(tabName));
//            GemTestReporter.addTestStep("Validate click on " + tabName + " is performed", "Clicked performed successfully", STATUS.PASS, takeSnapShot());
//        } else {
//            GemTestReporter.addTestStep("Validate " + tabName + " is visible", tabName + " is not visible on UI", STATUS.FAIL, takeSnapShot());
//        }
//    }

}
