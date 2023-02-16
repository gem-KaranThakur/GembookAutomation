package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class Login_Locators {

    public static By signInBtn = By.xpath("//button[text()='Sign in']");

    public static By credentials(String credentialType) {
        if (credentialType.contains("username")) {
            return By.xpath("//input[@type='email']");
        } else {
            return By.xpath("//input[@type='password']");
        }
    }

    public static By nextBtn = By.id("idSIButton9");
    public static By gembookLogo = By.xpath("//img[@class='gembook-logo']");
    public static By logoHeader = By.xpath("//span[@class='logo_headerTextHelper__1Z6Zl']");
    public static By profileImage = By.xpath("//img[@class='navbar_img__5LehJ']");


}
