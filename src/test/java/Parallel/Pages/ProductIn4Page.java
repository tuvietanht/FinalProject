package Parallel.Pages;

import Parallel.Pages.UserCartServices.CheckoutPage;
import Parallel.Pages.UserCartServices.ViewCartPage;
import Parallel.Pages.UserList.HomePage;
import Parallel.TestCase.WebUI;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;

public class ProductIn4Page {
    // User
    private By HomePageButton = By.xpath("//a[contains(text(),'Home')]");
    // ProductIn4
    private By ProductName = By.xpath("(//div[@class='text-left'])[2]//h1");
    private By NumRatings = By.xpath("//span[@class='ml-1 opacity-50']");
    private By SoldBy = By.xpath("(//div[@class='col-auto'])[1]");
    private By OrgPrice = By.xpath("//strong[@id='chosen_price']");
    private By FinalPrice = By.xpath("//strong[@class='h2 fw-600 text-primary']");
    private By StockQuantity = By.xpath("//div[@class='avialable-amount opacity-60']");
    // SetQuantity
    private By TotalValBuying = By.xpath("//button[@class='btn col-auto btn-icon btn-sm btn-circle btn-light']/following-sibling::input");
    private By AddStock = By.xpath("//i[@class='las la-plus']");
    private By SubStock = By.xpath("//i[@class='las la-minus']");
    // Buying Process
    private By AddToCart = By.xpath("//span[@class='d-none d-md-inline-block']");
    private By BacktoPage = By.xpath("//button[normalize-space()='Back to shopping']");
    // UserCartServices
    private By CartButton = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    private By ViewCartButton = By.xpath("//a[normalize-space()='View cart']");
    private By CheckoutButton = By.xpath("//a[normalize-space()='Checkout']");


    // Login SignInPage
    public HomePage SignInHomePage(){
        WebUI.ClickElementBy(HomePageButton);
        return new HomePage();
    }

    // Login CheckOutPage
    public CheckoutPage SignInCheckoutPage(){
        WebUI.ClickElementBy(CartButton);
        WebUI.ClickElementBy(CheckoutButton);
        return new CheckoutPage();
    }

    // Login ViewCartPage
    public ViewCartPage SignInViewCartPage(){
        WebUI.ClickElementBy(CartButton);
        WebUI.ClickElementBy(ViewCartButton);
        return new ViewCartPage();
    }

    // Get Information Product
    public String GetProductName(){return WebUI.GetText(ProductName);}
    public String GetNumRatings(){return WebUI.GetText(NumRatings);}
    public String GetOrgPrice(){return WebUI.GetText(OrgPrice);}
    public String GetFinalPrice(){return WebUI.GetText(FinalPrice);}
    public String GetStockQuantity(){return WebUI.GetText(StockQuantity);}
    public String GetSoldBy(){
        String text = WebUI.GetText(SoldBy).trim();
        String cleanedText = text.replaceAll("Sold by:\\s*", "");
        return  cleanedText;
    }

    // Add Product to Cart
    public void AddToCart(){
        WebUI.ClickElementBy(AddToCart);
        WebUI.Sleep(3);
        WebUI.ClickElementBy(BacktoPage);
    }

    // SaveValTaking after add to cart
    public void SaveValTaking() throws Exception {
        String TotalVal = WebUI.GetAttributeValue(TotalValBuying , "value");

        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("./ValWhenTaking.xlsx", "Sheet1");

        excel.setCellData(GetProductName(), 1, 0);
        excel.setCellData(GetOrgPrice(), 1, 1);
        excel.setCellData(TotalVal, 1, 2);
        excel.setCellData(GetFinalPrice(), 1, 3);
    }

    // Add and remove Stock
    public void AddMoreStock(int num) {for (int i = 0; i < num; i++) {WebUI.ClickElementBy(AddStock);}}
    public void RemoveStock(int num) {for (int i = 0; i < num; i++) {WebUI.ClickElementBy(SubStock);}}
}
