package Parallel.Pages.In4List;

import Parallel.Pages.UserCartServices.CheckoutPage;
import Parallel.Pages.UserList.HomePage;
import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;

public class DashBoardPage {
    // Menu Search
    private By ManagerProfile = By.xpath("//div[@class='d-flex align-items-start']//span[normalize-space()='Manage Profile']");
    private By HomePageButton = By.xpath("//a[contains(text(),'Home')]");
    // UserCartServices
    private By CartButton = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    private By ViewCartButton = By.xpath("//a[normalize-space()='View cart']");
    private By CheckoutButton = By.xpath("//a[normalize-space()='Checkout']");


    // Login ProfilePage
    public ProfilePage ProfileSignInPage() {
        WebUI.Move(ManagerProfile);
        WebUI.ClickElementBy(ManagerProfile);
        return new ProfilePage();
    }

    // Login HomePage
    public HomePage HomePageSignIn() {
        WebUI.ClickElementBy(HomePageButton);
        return new HomePage();
    }

    // Login CheckoutPage
    public CheckoutPage SignInCheckoutPage(){
        WebUI.ClickElementBy(CartButton);
        WebUI.ClickElementBy(CheckoutButton);
        return new CheckoutPage();
    }
}


