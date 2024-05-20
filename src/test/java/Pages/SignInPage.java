package Pages;

import Pages.AdminPages.AdminDashBoardPage;
import Pages.UserPages.UserDashBoardPage;
import helpers.PropertiesHelper;
import org.openqa.selenium.By;
import utils.driver.WebUI;

public class SignInPage {
    // Information to login
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonSignin = By.xpath("//button[normalize-space()='Login']");
    private By TitleLoginPage = By.xpath("//h1[normalize-space() = 'Login to your account.']");
    private By iframe = By.xpath("//i[@class='la la-close fs-20']");
    private By loginLogo = By.xpath("//a[@class='text-reset d-inline-block opacity-60 py-2'][normalize-space()='Login']");
    // Ad
    private By PopUp = By.xpath("//div[@class='p-3 bg-dark rounded']/button");
    // Message Error
    private By NullEmail = By.xpath("//strong[contains(text(),'The email field is required when phone is not present.')]");
    private By Invalid = By.xpath("//span[@data-notify='message']");
    private By NullPassword = By.xpath("//input[@class='form-control  is-invalid']");
    private By TitleLoginAdminPage = By.xpath("//img[@alt='Active eCommerce CMS']");
    private By DashBoardPageCheck = By.xpath("//h1[normalize-space()='Dashboard']");


    public void OpenSignInPage() {
        WebUI.OpenURL(PropertiesHelper.getValue("url_test_page"));
        WebUI.Sleep(2);
        WebUI.ClickElementBy(iframe);
        WebUI.ClickElementBy(loginLogo);
        WebUI.ClickElementBy(PopUp);
        WebUI.CheckElementExist(TitleLoginPage);
    }

    public void LoginFailWithNullEmail(String email, String password) {
        OpenSignInPage();

        WebUI.SendKeys(inputEmail, email);
        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.verifyAssertTrueIsDisplayed(NullEmail, "Email is incorrect but valid is NOT displayed.");
    }

    public void LoginFailWithNullPassword(String email, String password) {
        OpenSignInPage();

        WebUI.SendKeys(inputEmail, email);
        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.verifyAssertTrueIsDisplayed(NullPassword, "Password is NULL but it is NOT displayed.");
    }

    public void LoginFailWithNullEmailPassword() {
        OpenSignInPage();

        WebUI.ClickElementBy(buttonSignin);

        WebUI.verifyAssertTrueIsDisplayed(NullEmail, "Email is incorrect but valid is NOT displayed.");
        WebUI.verifyAssertTrueIsDisplayed(NullPassword, "Password is NULL but it is NOT displayed.");
    }

    public void LoginFailWithNotExistEmail(String email, String password) {
        OpenSignInPage();

        WebUI.SendKeys(inputEmail, email);
        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.verifyAssertTrueIsDisplayed(Invalid, "Invalid login credentials but is it not displayed");
    }

    public void LoginFailWithWrongPassword(String email, String password) {
        OpenSignInPage();

        WebUI.SendKeys(inputEmail, email);
        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.verifyAssertTrueIsDisplayed(Invalid, "Invalid login credentials but is it not displayed");
    }


    public UserDashBoardPage LoginSuccessWithCustomerAccount(String email, String password) {
        OpenSignInPage();

        WebUI.SendKeys(inputEmail, email);
        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.WaitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(DashBoardPageCheck, "Login success but Dashboard page is NOT displayed.");
        return new UserDashBoardPage();
    }

    public AdminDashBoardPage LoginSuccessAdminPage(String email, String password) {
        OpenSignInPage();

        WebUI.SendKeys(inputEmail, email);
        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.verifyAssertTrueIsDisplayed(TitleLoginAdminPage, "Login Admin page failed");
        return new AdminDashBoardPage();
    }


}

