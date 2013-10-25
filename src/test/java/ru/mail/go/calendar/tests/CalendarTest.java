package ru.mail.go.calendar.tests;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.mail.go.calendar.ResultPage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.MalformedURLException;
import java.net.URL;

public class CalendarTest {
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "hub", "url"})
    public void setUp(String browser, String hub, String url) throws MalformedURLException {
        if (browser.toLowerCase().equals("chrome"))
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.chrome());
        else if (browser.toLowerCase().equals("firefox"))
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.firefox());
        else
            throw new NotImplementedException();
        this.driver.manage().window().maximize();
        this.driver.get(url);
    }
/*//    @Parameters({"browser", "url"})
    public void setUp() throws MalformedURLException {
        this.driver = new FirefoxDriver();
        this.driver.get("http://go.mail.ru/search?q=%D0%BA%D0%B0%D0%BB%D0%B5%D0%BD%D0%B4%D0%B0%D1%80%D1%8C&fr=main");
    }*/

    @Test
    public void CalendarTestTitle(){
        ResultPage page = new ResultPage(this.driver);
        Assert.assertEquals(page.getTitle(),"Календарь России на 2013");
    }

   @Test
    public void CalendarTestCurrentYear(){
        String calendarCurrentYear = new ResultPage(this.driver).getCurrentYear();
        Assert.assertEquals(calendarCurrentYear, "2013");
    }
    @Test
    public void CalendarTestCurrentDay(){
        String currentDayColor = new ResultPage(this.driver).backgroundColor(".calendar__currentday .calendar__day-content");
        Assert.assertEquals(currentDayColor, "rgba(184, 234, 154, 1)");
    }


    @Test
    public void CalendarTestHolyday(){
        String holydayColor = new ResultPage(this.driver).backgroundColor(".calendar__holyday .calendar__day-content");
        Assert.assertEquals(holydayColor, "rgba(255, 204, 200, 1)");
    }

    @Test
    public void CalendarTestShortday(){
        String currentDayColor = new ResultPage(this.driver).backgroundColor(".calendar__shortday .calendar__day-content");
        Assert.assertEquals(currentDayColor, "rgba(255, 229, 197, 1)");
    }


    @Test
    public void CalendarTestExpand(){
        ResultPage page = new ResultPage(this.driver);
        page.clickOnCalendarButtonExpand();
        Assert.assertEquals(page.getCalendarCollapseFlag(),"calendar__block calendar__block_inline calendar__block_opened");
    }

    @Test
    public void CalendarTestCollapse(){
        ResultPage page = new ResultPage(this.driver);
        page.clickOnCalendarButtonExpand();
        page.clickOnCalendarButtonCollapse();
        Assert.assertEquals(page.getCalendarCollapseFlag(),"calendar__block calendar__block_inline");
    }

    @Test
    public void CalendarTestOtherYear(){
        String calendarOtherYear = new ResultPage(this.driver).getOtherYear();
        Assert.assertEquals(calendarOtherYear, "2014");
    }


    @AfterMethod
    public void Down(){
        this.driver.close();
    }
}
