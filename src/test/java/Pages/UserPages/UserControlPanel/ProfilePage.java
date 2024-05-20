package Pages.UserPages.UserControlPanel;

import org.openqa.selenium.By;
import utils.driver.WebUI;

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

    private By UpdateButton = By.xpath("//button[normalize-space()='Update Profile']");

    // User address
    private By CreateNewAdd = By.xpath("//div[@class='alpha-7']");
    private By ClickAdd = By.xpath("//textarea[@placeholder='Your Address']");

    private By SelectCountry = By.xpath("//div[contains(text(),'Select your country')]");
    private By VN = By.xpath("//span[@class='text'][normalize-space()='Vietnam']");

    private By SelectState = By.xpath("//div[contains(text(),'Select State')]");
    private By WriteState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By SelectCity = By.xpath("//div[contains(text(),'Select City')]");
    private By WriteCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By PortCode = By.xpath("//input[@placeholder='Your Postal Code']");

    private By PhoneAdd = By.xpath("//input[@placeholder='+880']");

    private By SaveAdd = By.xpath("//button[normalize-space()='Save']");
    private By AddAfterCreating = By.xpath("(//span[contains(text(),'?Phone')])[1]");


    // User Email
    private By titleChangeEmail = By.xpath("//h5[normalize-space()='Change your email']");
    private By inputEmail = By.xpath("//input[@placeholder='Your Email']");
    private By buttonVerifyEmail = By.xpath("//button[@class='btn btn-outline-secondary new-email-verification']");
    private By buttonUpdateEmail = By.xpath("//button[normalize-space()='Update Email']");

    // Check Page and attributes
    private By titleManageProfile = By.xpath("//h1[normalize-space()='Manage Profile']");
    private By PopUpAfterUpdate = By.xpath("//span[@data-notify='message']");
    private By tittleAddress = By.xpath(("//h5[normalize-space()='Address']"));
    private By SignAddressForm = By.xpath("//form[@role='form']//div[@class='modal-body']");
    private By messageUpdateEmailSuccess = By.xpath("//span[@data-notify='message']");


    // Setting Profiles
    public void SetName(String name) {
        WebUI.verifyAssertTrueIsDisplayed(Name, "Name text is not displayed");
        WebUI.CleanAdd(Name, name);
    }

    public void SetPhone(String phone) {
        WebUI.verifyAssertTrueIsDisplayed(Phone, "Phone text is not displayed");
        WebUI.CleanAdd(Phone, phone);
    }

    public void SetPassword(String password) {
        WebUI.verifyAssertTrueIsDisplayed(Password, "Password text is not displayed");
        WebUI.SendKeys(Password, password);
    }

    public void SetRePassword(String password) {
        WebUI.verifyAssertTrueIsDisplayed(RePassWord, "RePassWord text is not displayed");
        WebUI.SendKeys(RePassWord, password);
    }

    public void SetPhoto(String UrlImage) throws AWTException {
        WebUI.verifyAssertTrueIsDisplayed(Photo, "Can't interact with Photo");
        WebUI.ClickElementBy(Photo);

        WebUI.Sleep(5);
        WebUI.verifyAssertTrueIsDisplayed(Photo, "Can't interact with Upload New Photo ");
        WebUI.ClickElementBy(UploadNewPhoto);

        WebUI.Sleep(5);
        WebUI.verifyAssertTrueIsDisplayed(Browse, "Upload New Photo Button didn't exist ");
        WebUI.ClickElementBy(Browse);

        WebUI.Sleep(5);
        WebUI.RobotPaste(UrlImage);
        WebUI.Sleep(7);
        WebUI.verifyAssertTrueIsDisplayed(Upload, "Upload button didn't exist ");
        WebUI.ClickElementBy(Upload);
        WebUI.Sleep(4);
        WebUI.ClickElementBy(Photo);
        WebUI.Sleep(4);
        WebUI.ClickElementBy(FirstImg);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(Upload);
    }

    public void ClickUpdateProfile() {
        WebUI.WaitForElementClickable(UpdateButton);
        WebUI.ClickElementBy(UpdateButton);
        WebUI.verifyAssertTrueIsDisplayed(PopUpAfterUpdate, "The message about update profile success isn't displayed");
    }


    // Setting address
    public void AddNewAdd(String add, String state, String city, String postalCode, String phone) {
        WebUI.Move(tittleAddress);
        WebUI.verifyAssertTrueIsDisplayed(tittleAddress, "Address title is not displayed");

        WebUI.verifyAssertTrueIsDisplayed(CreateNewAdd, "Add NewAddress button is not displayed");
        WebUI.ClickElementBy(CreateNewAdd);
        WebUI.SendKeys(ClickAdd, add);
        WebUI.verifyAssertTrueIsDisplayed(SignAddressForm, "Address form is not displayed");
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectCountry);
        WebUI.ClickElementBy(VN);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectState);
        WebUI.Sleep(2);
        WebUI.SendKeysEnter(WriteState, state);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectCity);
        WebUI.Sleep(3);
        WebUI.SendKeysEnter(WriteCity, city);
        WebUI.Sleep(2);

        WebUI.SendKeys(PortCode, postalCode);
        WebUI.SendKeys(PhoneAdd, phone);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SaveAdd);

        By FieldAfterCreating = By.xpath("(//span[contains(text(),'" + phone + "')])[1]");
        WebUI.verifyAssertTrueIsDisplayed(FieldAfterCreating, "Address After Creating not yet implemented");
    }

    // Setting Email
    public void updateEmail(String email) {
        WebUI.Move(titleChangeEmail);
        WebUI.verifyAssertTrueIsDisplayed(titleChangeEmail, "Title Change your email is NOT displayed");
        WebUI.CleanAdd(inputEmail, "yueying2806@gmail.com");
        WebUI.Sleep(2);
        WebUI.ClickElementBy(buttonUpdateEmail);
        WebUI.verifyAssertTrueIsDisplayed(messageUpdateEmailSuccess, "Update email is failed");
    }

}

