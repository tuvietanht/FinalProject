package Pages.AdminPages;

import Pages.AdminPages.ProductPages.AddNewProductsPage;
import org.openqa.selenium.By;
import utils.driver.WebUI;

public class AdminDashBoardPage {
    // Product Pages
    private By ProductButton = By.xpath("//span[normalize-space()='Products']");
    private By AddNewProductsButton = By.xpath("//span[normalize-space()='Add New Product']");
    private By CheckLoginAddNewProductPage = By.xpath("//h5[normalize-space()='Add New Product']");


    // Product Pages
    // Sign in Add New Product Page
    public AddNewProductsPage SignInAddNewProductPage() {
        WebUI.verifyAssertTrueIsDisplayed(ProductButton, "Products button is not displayed");
        WebUI.ClickElementBy(ProductButton);
        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(AddNewProductsButton, "Add New Products button is not displayed");
        WebUI.ClickElementBy(AddNewProductsButton);
        WebUI.verifyAssertTrueIsDisplayed(CheckLoginAddNewProductPage, "Login Add New Products page failed");
        return new AddNewProductsPage();
    }


}
