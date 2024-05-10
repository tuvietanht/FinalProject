package Parallel.Pages.UserList;

import Parallel.Pages.ProductIn4Page;
import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;

public class HomePage {
    // Search tools
    By SearchBar = By.xpath("//input[@id='search']");
    By SearchButton = By.xpath("//i[@class='la la-search la-flip-horizontal fs-18']");
    // Choose New and Best Seller product
    By FirstNewProduct = By.xpath("(//div[@class='position-relative'])[5]");
    By BestSellingList = By.xpath("//span[normalize-space()='Best Selling']");
    By FirstBestSelling = By.xpath("//div[@id='section_best_selling']//div[@class='slick-slide slick-current slick-active']");


    // Login CategoriePage with the products
    public CategoriesPage SignInSearchPage(String productQuery){
        WebUI.SendKeys(SearchBar,productQuery);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SearchButton);
        WebUI.Sleep(10);
        return new CategoriesPage();
    }

    // Select Newest product
    public ProductIn4Page SelectFirstNewProduct(){
        WebUI.moveToElement(FirstNewProduct);
        WebUI.ClickElementBy(FirstNewProduct);
        return new ProductIn4Page();
    }

    // Select Best Seller Product
    public ProductIn4Page SelectFistBestSelling(){
        WebUI.moveToElement(BestSellingList);
        WebUI.ClickElementBy(FirstBestSelling);
        return new ProductIn4Page();
    }
}
