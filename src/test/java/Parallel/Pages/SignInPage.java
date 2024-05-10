package Parallel.Pages;

import Parallel.Pages.In4List.DashBoardPage;
import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;
import utils.driver.DriverManager;

public class SignInPage {
    // Information to login
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonSignin = By.xpath("//button[normalize-space()='Login']");
    private By iframe = By.xpath("//i[@class='la la-close fs-20']");
    private By loginLogo = By.xpath("//a[@class='text-reset d-inline-block opacity-60 py-2'][normalize-space()='Login']");
    // Ad
    private By PopUp = By.xpath("//div[@class='p-3 bg-dark rounded']/button");


    // Login first page
    public void signIn() {
        WebUI.OpenURL("https://cms.anhtester.com/");
        WebUI.ClickElementBy(iframe);
        WebUI.ClickElementBy(loginLogo);
    }

    // Login DashBoardPage
    public DashBoardPage SignInDashBoardPage(String email, String password) {
        signIn();
        WebUI.WaitForElementClickable(inputEmail, 10);
        WebUI.ClickElementBy(PopUp);
        WebUI.SendKeys(inputEmail, email);

        WebUI.SendKeys(inputPassword, password);
        WebUI.ClickElementBy(buttonSignin);

        WebUI.WaitForPageLoaded();
        WebUI.verifyEquals(DriverManager.getDriver().getCurrentUrl(), "https://cms.anhtester.com/dashboard");
        return new DashBoardPage();
    }
}

