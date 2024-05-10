package Parallel.Pages.UserList;

import Parallel.Pages.ProductIn4Page;
import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;

public class CategoriesPage {
    // Category Page
    By FirstProduct = By.xpath("(//div[@class='position-relative'])[1]");
    By NextPageButton = By.xpath("//a[contains(text(),'›')]");

    // Login ProductInfoPage
    public ProductIn4Page SignInProductIn4Page(){
        WebUI.ClickElementBy(FirstProduct);
        WebUI.Sleep(10);
        return new ProductIn4Page();
    }

    // Next to page
    public void ClickNextPage(){
        WebUI.ClickElementBy(NextPageButton);
    }
}
