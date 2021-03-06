package com.techproed.JSExecutor;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.JSUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Day15_JSExecutorTest {
    JavascriptExecutor je=(JavascriptExecutor) Driver.getDriver();
    @Test
    public void scrollIntoViewTest() throws InterruptedException {
        /*
         * Given user is on the application url
         * Then verify the text "Recent Blog" is on the page
         * */
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        Thread.sleep(3000);
        WebElement recentBlog=Driver.getDriver().findElement(By.xpath("//*[.='Recent Blog']"));
//        arguments[0].scrollIntoView(true); => THIS IS USED A LOT
        je.executeScript("arguments[0].scrollIntoView(true);",recentBlog);
        Thread.sleep(3000);
        Assert.assertEquals(recentBlog.getText(),"Recent Blog");
    }
    @Test
    public void scrollIntoViewUtils() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        WebElement recentBlog=Driver.getDriver().findElement(By.xpath("//*[.='Recent Blog']"));
        JSUtils.scrollIntoVIewJS(recentBlog);
        Thread.sleep(3000);
        Assert.assertEquals(recentBlog.getText(),"Recent Blog");
    }
    @Test
    public void flashingButton(){
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        WebElement checkAvailabilityButton=Driver.getDriver().findElement(By.xpath("//input[@type='submit']"));
//        Changing background color
        JSUtils.changeColor("red",checkAvailabilityButton);
        JSUtils.flash(checkAvailabilityButton);
    }
    @Test
    public void scrollWithCoordinates() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        WebElement recentBlog=Driver.getDriver().findElement(By.xpath("//*[.='Recent Blog']"));
//        Getting the coordinates of the element
        Point coordinates=recentBlog.getLocation();// (933, 3986); x=933(right/left), y= 3986(up/down)
        System.out.println(coordinates.toString());
//                             window.scrollBy(x coordinate, y coordinate)
        je.executeScript("window.scrollBy(0,3986);");//WORKS FINE BUT WE CAN GIVE THE EXACT COORDINATE OF THE ELEMENT
        je.executeScript("window.scrollBy(0,"+coordinates+");");//coordinates.y = Changing the y coordinate
        Thread.sleep(3000);
        Assert.assertEquals(recentBlog.getText(),"Recent Blog");
    }
    @Test
    public void clickWithJS(){
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        WebElement check=Driver.getDriver().findElement(By.xpath("//input[@type='submit']"));
        JSUtils.clickElementByJS(check);
    }
}