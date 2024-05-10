package Parallel.Pages.In4List;

import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;

import java.awt.*;

public class ProfilePage {
    // User information
    private By Name = By.xpath("//input[@placeholder='Your name']");
    private By Phone = By.xpath("//input[@placeholder='Your Phone']");
    private By Password = By.xpath("//input[@placeholder='New Password']");
    private By RePassWord = By.xpath("//input[@placeholder='Confirm Password']");

    private By Photo = By.xpath("//div[@class='form-control file-amount']");
    private By UploadNewPhoto = By.xpath("//a[@class='nav-link font-weight-medium text-dark']");
    private By Browse = By.xpath("//button[normalize-space()='Browse']");
    private By Upload = By.xpath("//button[normalize-space()='Add Files']");
    private By FirstImg = By.xpath("//div[@id='aiz-select-file']//img[1]");


    // Change information user
    public void SetName(String name){WebUI.SendKeys(Name , name);}
    public void SetPhone(String phone){WebUI.SendKeys(Phone , phone);}
    public void SetPassword(String password){WebUI.SendKeys(Password , password);}
    public void SetRePassword(String password) {WebUI.SendKeys(RePassWord , password);}
    public void SetPhoto(String urlPath) throws AWTException {
        WebUI.ClickElementBy(Photo);

        WebUI.Sleep(5);
        WebUI.ClickElementBy(UploadNewPhoto);

        WebUI.Sleep(5);
        WebUI.ClickElementBy(Browse);

        WebUI.Sleep(5);
        WebUI.RobotPaste(urlPath);

        WebUI.Sleep(5);
        WebUI.ClickElementBy(Upload);

        WebUI.Sleep(4);
        WebUI.ClickElementBy(Photo);
        WebUI.Sleep(4);

        WebUI.ClickElementBy(FirstImg);
        WebUI.ClickElementBy(Upload);
    }
}

