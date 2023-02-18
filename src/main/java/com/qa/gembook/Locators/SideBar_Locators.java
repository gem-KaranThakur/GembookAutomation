package com.qa.gembook.Locators;

import org.openqa.selenium.By;

public class SideBar_Locators {
    public static By navigationTab(String tabName) {
        return By.xpath("//span[contains(text(),'" + tabName + "')]");
    }
    public static By navigationTab(String tabName,String dynamicParam) {
        return By.xpath("//span[contains(text(),'" + tabName + "')]"+ dynamicParam);
    }
    public static By otherPortalsIcon=By.xpath("//div[@class='expansion-icon']//img[contains(@class,'rotate-90')]");
    public static By arrowIcon=By.xpath("//div[@class='expansion-icon']//img[contains(@class,'rotate-90')]");
    public static By subItemJenkins=By.xpath("//span[text()='Jenkins']");
    public static By sidebarList=By.xpath("//ul[contains(@class,'sidebar_menu')]//span");
    public static By sideBarIcon=By.xpath("//span[contains(@class,'logo_header') and contains(text(),'Gembook')]");
    public static By otherPortals=By.xpath("//span[contains(text(),'Other Portals')]");

    public static By iconPath(String param)
    {
        return By.xpath("//div[@class='expansion-icon']//img[contains(@class, "+ param+ ")]");
    }
}

