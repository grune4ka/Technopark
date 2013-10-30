package ru.mail.go.calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ResultPage{
    private WebDriver driver;

    private static final String currentYear = "calendar__year-current";
    private static final String backgroundColor = "background-color";
    private static final String expandButton = "calExpand";
    private static final String collapseButton = "calCollapse";
    private static final String otherYear ="calendar__year_other";
    private static final String title = "calendar__title";

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentYear(){

 //       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver.findElement(By.className(currentYear)).getText();
    }

    public String backgroundColor (String itemDay) {
        WebElement element = driver.findElement(By.cssSelector(itemDay));
        return element.getCssValue(backgroundColor);
    }
    public void clickOnCalendarButtonExpand(){
        driver.findElement(By.id(expandButton)).click();
    }

    public void clickOnCalendarButtonCollapse(){
        driver.findElement(By.id(collapseButton)).click();
    }

    public boolean getCalendarExpandButtonCondition(String statusChangeButton){
        return driver.findElement(By.cssSelector(statusChangeButton)).isDisplayed();

    }

    public String getOtherYear(){
        driver.findElement(By.className(currentYear)).click();
        return driver.findElement(By.className(otherYear)).getText();
    }

    public String getTitle() {
        return driver.findElement(By.className(title)).getText();
    }



}
