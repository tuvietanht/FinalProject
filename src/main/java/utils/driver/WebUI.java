package utils.driver;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import reports.ExtentTestManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebUI {
    private final static int TIMEOUT = 7;
    private final static double STEP_TIME = 1;
    private final static int PAGE_LOAD_TIMEOUT = 20;

    public static void Sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CleanAdd(By xpath, String NewText) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        DriverManager.getDriver().findElement(xpath).clear();
        DriverManager.getDriver().findElement(xpath).sendKeys(NewText);

        Log.info("Clean  : " + xpath + " And add : " + NewText);
        ExtentTestManager.logMessage(Status.PASS, "Clean : " + xpath + " And add : " + NewText);

    }

    public static void ClickElementBy(By xpath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((xpath)).click();
        Log.info("Click Element : " + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Click Element : " + xpath);
    }

    public static void ClickNSendKeyElement(By xpath, String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((xpath)).click();
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((xpath)).sendKeys(text);
        Log.info("Click Element and SendKeys : " + xpath);
        ExtentTestManager.logMessage(Status.PASS, "Click and Senkeys on : " + xpath + " on with " + text);
    }

    public static void SendKeysEnter(By byXpath, String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((byXpath)).sendKeys(text);
        DriverManager.getDriver().findElement((byXpath)).sendKeys(Keys.ENTER);
//        DriverManager.getDriver().findElement((byXpath)).submit();
        Log.info("Set text on element : " + byXpath + " on with " + text);
        ExtentTestManager.logMessage(Status.PASS, "Set text on element and Enter : " + byXpath + " on with " + text);
    }

    public static void SendKeys(By byXpath, String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((byXpath)).sendKeys(text);
        Log.info("Set text on element : " + byXpath + " on with " + text);
        ExtentTestManager.logMessage(Status.PASS, "Set text on element : " + byXpath + " on with " + text);

    }

    public static String GetText(By byXpath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
        Log.info("Get text of element: " + byXpath);
        return DriverManager.getDriver().findElement(byXpath).getText();
    }

    public static String GetAttributeValue(By byXpath, String attributeName) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
        Log.info("Get attribute '" + attributeName + "' of element: " + byXpath);
        return DriverManager.getDriver().findElement(byXpath).getAttribute(attributeName);
    }

    public static void OpenURL(String url) {
        DriverManager.getDriver().get(url);
        ExtentTestManager.logMessage(Status.PASS, " Open URL :" + url);
        Log.info("Open :" + url);
    }

    public static Boolean CheckElementExist(By by) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
            Log.info("Element " + by + " existing.");
            return true;
        } else {
            Log.info("Element " + by + " Not existing.");
            return false;
        }
    }

    public static Boolean CheckElementExist(String xpath) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    /**
     * Js
     */
    public static void DomainURL() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String domainName = js.executeScript("return document.domain;").toString();
        System.out.println(domainName);
        String url = js.executeScript("return document.URL;").toString();
        System.out.println(url);
    }

    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", DriverManager.getDriver().findElement(by));
            Sleep(1);
        }
        return DriverManager.getDriver().findElement(by);
    }

    /**
     * Action selenium
     */
    public static void ActionCopy(WebElement copy, WebElement paste) throws AWTException {
        Actions act = new Actions(DriverManager.getDriver());
        act.click(copy).doubleClick(copy).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).click(paste).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }

    public static void RobotPaste(String Url) throws AWTException {
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        StringSelection str = new StringSelection(Url);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        WebUI.Sleep(3);
        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.Sleep(3);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void RobotPasteXpath(String Url) throws AWTException {
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        StringSelection str = new StringSelection(Url);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        WebUI.Sleep(3);
        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.Sleep(3);
    }

    public static void Move(By det) {
        Actions act = new Actions(DriverManager.getDriver());
        act.moveToElement(DriverManager.getDriver().findElement(det));
    }

    public static void MoveJS(By det) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        WebElement element = DriverManager.getDriver().findElement(By.id("id_of_element"));
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(det));
    }

    public static void DrapDrop(By src, By det) {
        Actions act = new Actions(DriverManager.getDriver());
        act.dragAndDrop(DriverManager.getDriver().findElement(src), DriverManager.getDriver().findElement(det));
    }

    // rê chuột nhưng k làm gì cả
    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(DriverManager.getDriver().findElement(toElement)).release(DriverManager.getDriver().findElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Log.error(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(DriverManager.getDriver().findElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Action robot
     */
    public static void ScreenCap() throws InterruptedException, AWTException, IOException {

        Robot robot = new Robot();
        //Get size screen browser
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);
        //Khởi tạo kích thước khung hình với kích cỡ trên
        Rectangle screenRectangle = new Rectangle(screenSize);
        //Tạo hình chụp với độ lớn khung đã tạo trên
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //Lưu hình vào dạng file với dạng png
        File file = new File("TestImageRobot.png");
        ImageIO.write(image, "png", file);

        Thread.sleep(1000);
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void verifyEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected, "Fail, NOT match" + actual.toString() + "not equals" + expected.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify result" + expected + " is correct");
        Log.info("Verify result" + expected + " is correct");
    }

    public static void verifyEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ExtentTestManager.logMessage(Status.PASS, "Verification successful: Result actual : " + actual + " and Result expected : " + expected + " is correct");
            Log.info("Result actual : " + actual + " and  Result expected : " + expected + " is correct");
        } catch (AssertionError e) {
            Log.error("Verification failed: " + message);
            Log.error("Result actual : " + actual + " and  Result expected : " + expected);
            ExtentTestManager.logMessage(Status.FAIL, "Verification failed: " + message);
            throw e;
        }
    }


    public static void verifyAssertTrueIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Assert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
            ExtentTestManager.logMessage(Status.PASS, "Verification successful: " + by + " is displayed");
            Log.info("Verification successful: " + by + " is displayed");
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.FAIL, "Verification failed: " + by + " is not displayed. " + message);
            Log.error("Verification failed: " + by + " is not displayed. " + message);
            throw e;
        }
    }

    public static void verifyAssertTrueEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        Log.info("Verify " + verifyText + " is display correct on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display correct on " + by.toString());
    }


    /**
     * Alert
     */
    public static void ADismiss() {
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    public static void AAccept() {
        DriverManager.getDriver().switchTo().alert().accept();
    }

    public static void AGettext() {
        DriverManager.getDriver().switchTo().alert().getText();
    }

    public static void ASenkey(String text) {
        DriverManager.getDriver().switchTo().alert().sendKeys(text);
    }

    /**
     * Tab mini(popup) or new tab
     */
    public void PopUp(int numTab) throws InterruptedException {
        // Get all new opened tab Window.
        ArrayList<String> tab = new ArrayList(DriverManager.getDriver().getWindowHandles());
        // start with Main tab with 0
        DriverManager.getDriver().switchTo().window(tab.get(0));
        // Closing the Child Window.
        DriverManager.getDriver().close();
        // Switching Main tab
        DriverManager.getDriver().switchTo().window(tab.get(0));
        Thread.sleep(2000);
    }

    /**
     * Iframe
     */
    public void Iframe01() throws InterruptedException {
        DriverManager.getDriver().navigate().to("https://anhtester.com/contact");
        Thread.sleep(9000);
        System.out.println("iframe total: " + DriverManager.getDriver().findElements(By.tagName("iframe")).size());
        DriverManager.getDriver().switchTo().frame(0);
        Thread.sleep(1000);
        System.out.println(DriverManager.getDriver().findElement(By.tagName("strong")).getText());
        //1. Switch to Parent WindowHandle
        DriverManager.getDriver().switchTo().parentFrame();
        //2. Switch to iframe icon of Messenger
        DriverManager.getDriver().switchTo().frame(1);
        DriverManager.getDriver().findElement(By.tagName("svg")).click(); //Nhấn icon để ẩn messenger chat đi
        DriverManager.getDriver().switchTo().parentFrame();
        Thread.sleep(2000);
    }

    /**
     * Assert
     */
    public static void HAssert(String a, String b) {
        Assert.assertEquals(a, b);
    }

    public static void SAssert(String a, String b) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(a, b);
        // must add  softassert.assertAll()
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */

    //Wait for Element
    public static void WaitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void WaitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void WaitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            Log.info(by.toString() + "Exist");
        } catch (Throwable error) {
            Log.error(by.toString() + "Not exist");
            Assert.fail(by.toString() + "Not exist");
        }
    }

    public static void WaitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void WaitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(DriverManager.getDriver().findElement(by)));
            Log.info("Click" + by.toString());
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void WaitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(DriverManager.getDriver().findElement(by)));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    // click into network check
    public static void WaitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready , go to console and print document.readyState in the table for checking
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            Log.error("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

}
