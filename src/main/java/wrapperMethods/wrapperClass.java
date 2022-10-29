package wrapperMethods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class wrapperClass {

    Actions action;

    public wrapperClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void moveToElement(WebDriver driver, WebElement Element)
    {
        action = new Actions(driver);
        try
        {
            action.moveToElement((Element)).build().perform();
        }
        catch(Exception e)
        {
            System.out.println("Element is not visible");
        }

    }

    public void doubleClickAndDelete(WebDriver driver, WebElement Element)
    {
        action = new Actions(driver);
        try
        {
            action.moveToElement(Element).build().perform();
            action.doubleClick(Element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).build().perform();
        }
        catch(Exception e)
        {
            System.out.println("Element is not visible");
        }

    }



}
