package Pages.UserPages;

import Pages.UserPages.ShoppingCart.CheckoutPage;
import Pages.UserPages.ShoppingCart.OrderPage;
import Pages.UserPages.UserControlPanel.ProfilePage;
import org.openqa.selenium.By;
import utils.driver.WebUI;

public class UserDashBoardPage {
    private By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
    // Manager Profile button
    private By ManagerProfile = By.xpath("//div[@class='d-flex align-items-start']//span[normalize-space()='Manage Profile']");
    // Search tool
    public static By SearchBar = By.xpath("//input[@id='search']");
    private static By SearchButton = By.xpath("//i[@class='la la-search la-flip-horizontal fs-18']");
    // Select Product 1 and 2
    private By SelectProduct1 = By.xpath("//div[normalize-space()='Cosy Thuy Dung UMTSHEGI']");
    private By SelectProduct2 = By.xpath("//a[contains(@href,'https://cms.anhtester.com/product/gio-qua-tet-mxztw')]//div[contains(@class,'d-flex search-product align-items-center')]//div[contains(@class,'mr-3')]//img[contains(@class,'size-40px img-fit rounded')]");
    // Select Product New
    private By SelectNewProduct = By.xpath("(//div[@class='col'])[1]");
    // Check Page
    private By ExampleProduct = By.xpath("//ul[@class='list-group list-group-raw']");
    // UserCartServices
    private By CartButton = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    private By ViewCartButton = By.xpath("//a[normalize-space()='View cart']");
    private By CheckoutButton = By.xpath("//a[normalize-space()='Checkout']");


    // Login ProfilePage
    public ProfilePage ProfileSignInPage() {
        WebUI.verifyAssertTrueIsDisplayed(ManagerProfile, "ManagerProfile is not displayed");
        WebUI.Move(ManagerProfile);
        WebUI.ClickElementBy(ManagerProfile);
        return new ProfilePage();
    }

    // Change to OrderPage with Product
    public OrderPage SearchProduct1(String productQuery) {
        WebUI.SendKeys(SearchBar, productQuery);
        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(ExampleProduct, "Product example is not displayed in search bar");
        WebUI.ClickElementBy(SelectProduct1);
        WebUI.Sleep(2);
        return new OrderPage();
    }

    public OrderPage SearchProduct2(String productQuery) {
        WebUI.SendKeys(SearchBar, productQuery);
        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(ExampleProduct, "Product example is not displayed in search bar");
        WebUI.ClickElementBy(SelectProduct2);
        WebUI.Sleep(2);
        return new OrderPage();
    }

    public OrderPage SearchNewproducts(String productQuery) {
        WebUI.SendKeysEnter(SearchBar, productQuery);
        WebUI.Sleep(3);
        WebUI.verifyAssertTrueIsDisplayed(SelectNewProduct, "New Product is not displayed");
        WebUI.Sleep(3);
        WebUI.ClickElementBy(SelectNewProduct);
        WebUI.Sleep(3);
        return new OrderPage();
    }


    // Login CheckoutPage
    public CheckoutPage SignInCheckoutPage() {
        WebUI.ClickElementBy(CartButton);
        WebUI.ClickElementBy(ViewCartButton);
        return new CheckoutPage();
    }
}


