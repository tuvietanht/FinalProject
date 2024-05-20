package Pages.UserPages.ShoppingCart;

import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import utils.driver.WebUI;

import java.util.Random;

public class OrderPage {
    // Search tools
    By SearchBar = By.xpath("//input[@id='search']");
    By SearchButton = By.xpath("//i[@class='la la-search la-flip-horizontal fs-18']");
    // ProductIn4
    private By ProductName = By.xpath("(//div[@class='text-left'])[2]//h1");
    private By NumRatings = By.xpath("//span[@class='ml-1 opacity-50']");
    private By SoldBy = By.xpath("(//div[@class='col-auto'])[1]");
    private By Unit = By.xpath("//span[@class='opacity-70']");
    private By OrgPrice = By.xpath("//strong[@class='h2 fw-600 text-primary']");
    private By StockQuantity = By.xpath("//span[@id='available-quantity']");
    private By Description = By.xpath("//div[@id='tab_default_1']/descendant::p");
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
    // Check
    private By AddToCartSuccess = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    private By CountValCartExternal = By.xpath("//span[@class='badge badge-primary badge-inline badge-pill cart-count']");
    private By CountValInCartInternal = By.xpath("//span[@class='fw-600 mb-1 text-truncate-2']");

    // Search Product
    public void SearchProduct(String productQuery) {
        WebUI.SendKeys(SearchBar, productQuery);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SearchButton);
        WebUI.Sleep(10);
    }

    // Get Information Product
    public String GetProductName() {
        return WebUI.GetText(ProductName);
    }

    public String GetNumRatings() {
        return WebUI.GetText(NumRatings);
    }

    public String GetOrgPrice() {
        return WebUI.GetText(OrgPrice);
    }

    public String GetUnit() {
        return (WebUI.GetText(Unit).substring(1));
    }

    public String GetDescription() {
        return WebUI.GetText(Description);
    }

    public String GetStockQuantity() {
        return WebUI.GetText(StockQuantity);
    }

    public String GetSoldBy() {
        String text = WebUI.GetText(SoldBy).trim();
        String cleanedText = text.replaceAll("Sold by:\\s*", "");
        return cleanedText;
    }

    // Add Product to Cart
    public void AddProductInCart() {
        int Add = new Random().nextInt(2) + 2;
        AddMoreStock(Add);
        WebUI.Sleep(4);
        int Sub = new Random().nextInt(2) + 1;
        RemoveStock(Sub);
        WebUI.ClickElementBy(AddToCart);
        WebUI.Sleep(3);
        WebUI.verifyAssertTrueIsDisplayed(AddToCartSuccess, "Add to cart success isn't announced");
        WebUI.ClickElementBy(BacktoPage);

        int CurValIncart = Integer.parseInt(WebUI.GetText(CountValCartExternal));
        boolean AddSuccess = CurValIncart > 0;
        WebUI.verifyEquals(AddSuccess, true, "Add to cart failed or num in cart error outside");

        WebUI.Sleep(3);
        WebUI.ClickElementBy(CartButton);
        WebUI.verifyAssertTrueIsDisplayed(CountValInCartInternal, "Add to cart failed or num in cart error inside");
        WebUI.Sleep(1);
        WebUI.ClickElementBy(CartButton);
    }

    public void TakeIn4Product() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/TakeProductIn4.xlsx", "Sheet1");
        excel.setCellData(GetProductName(), 1, 0);
        excel.setCellData(GetNumRatings(), 1, 1);
        excel.setCellData(GetOrgPrice(), 1, 2);
        excel.setCellData(GetUnit(), 1, 3);
        excel.setCellData(GetStockQuantity(), 1, 4);
        excel.setCellData(GetSoldBy(), 1, 5);
        excel.setCellData(GetDescription(), 1, 6);
    }

    public void VerifyIn4NewProduct(String DataName, String DataUnitProduct, String Unit, String DataQuantity, String Description) {
        String Name = GetProductName();
        String BaseName = Name.substring(0, Name.indexOf(" ") != -1 ? Name.indexOf(" ") : Name.length());
        WebUI.verifyEquals(BaseName, DataName, "Name is not equal to product");


        String OrgPrice = (GetOrgPrice()).replace("$", "").replace(",", "");
        WebUI.verifyEquals(OrgPrice, (DataUnitProduct + ".00"), "Money is not equal to product");

        String BaseUnit = GetUnit();
        WebUI.verifyEquals(BaseUnit, Unit, "Unit is not equal to product");

        String BaseStockQuantity = GetStockQuantity();
        WebUI.verifyEquals(BaseStockQuantity, DataQuantity, "StockQuantity is not equal to product");

        String BaseDescription = GetDescription();
        WebUI.verifyEquals(BaseDescription, Description, "Description is not equal to product");


    }


    // Add and remove Stock
    public void AddMoreStock(int num) {
        for (int i = 0; i < num; i++) {
            WebUI.ClickElementBy(AddStock);
        }
    }

    public void RemoveStock(int num) {
        for (int i = 0; i < num; i++) {
            WebUI.ClickElementBy(SubStock);
        }
    }

    // Login CheckoutPage
    public CheckoutPage SignInCheckoutPage() {
        WebUI.ClickElementBy(CartButton);
        WebUI.ClickElementBy(ViewCartButton);
        return new CheckoutPage();
    }
}
