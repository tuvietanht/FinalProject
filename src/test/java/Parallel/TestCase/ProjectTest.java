package Parallel.TestCase;

import Parallel.Pages.In4List.DashBoardPage;
import Parallel.Pages.In4List.ProfilePage;
import Parallel.Pages.ProductIn4Page;
import Parallel.Pages.SignInPage;
import Parallel.Pages.UserCartServices.CheckoutPage;
import Parallel.Pages.UserCartServices.ViewCartPage;
import Parallel.Pages.UserList.CategoriesPage;
import Parallel.Pages.UserList.HomePage;
import helpers.ExcelHelpers;
import helpers.PropertiesHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class ProjectTest extends Login {
    private SignInPage signInPage;
    private ProductIn4Page productIn4Page;
    // UserCartService
    private CheckoutPage checkoutPage;
    private ViewCartPage viewCartPage;
    //In4List
    private DashBoardPage dashBoardPage;
    private ProfilePage profilePage;
    //UserList
    private HomePage homePage;
    private CategoriesPage categoriesPage;


    @BeforeMethod
    public void setupTest() {signInPage = new SignInPage();}


//  AdminPage
    @Test(priority = 0) public void LogInWrongName() {dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("invalid_username"), PropertiesHelper.getValue("valid_password"));}
    @Test(priority = 1) public void LogInWrongPass() {dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("invalid_password"));}
    @Test(priority = 2) public void LogInDashBoardPage() {dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));}


//  ProfilePage
    @Test(priority = 3)
    public void UpdateProfile() throws AWTException {
        dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));
        profilePage = dashBoardPage.ProfileSignInPage();
        profilePage.SetName(PropertiesHelper.getValue("user_name"));
        profilePage.SetPhone(PropertiesHelper.getValue("user_phone"));
        profilePage.SetPhoto(PropertiesHelper.getValue("user_photo"));
        profilePage.SetPassword(PropertiesHelper.getValue("user_password"));
        profilePage.SetRePassword(PropertiesHelper.getValue("user_repassword"));
    }

//  ProductIn4Page
    @Test(priority = 4)
    public void CheckIn4Product() throws Exception {
        dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));
        homePage = dashBoardPage.HomePageSignIn();
        categoriesPage = homePage.SignInSearchPage("Cosy");
        productIn4Page = categoriesPage.SignInProductIn4Page();

        String name = productIn4Page.GetProductName();
        String numRate = productIn4Page.GetNumRatings();
        String owner = productIn4Page.GetSoldBy();
        String orgPrice = productIn4Page.GetOrgPrice();
        String finalPrice = productIn4Page.GetFinalPrice();
        String stock = productIn4Page.GetStockQuantity();

        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("./ProductIn4.xlsx", "Sheet1");
        excel.setCellData(name, 1, 0);
        excel.setCellData(numRate, 1, 1);
        excel.setCellData(orgPrice, 1, 2);
        excel.setCellData(finalPrice, 1, 3);
        excel.setCellData(stock, 1, 4);
        excel.setCellData(owner, 1, 5);
    }

//  Add Product to Cart
    @Test(priority = 5)
    public void Add2Product(){
        dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));
        homePage = dashBoardPage.HomePageSignIn();

        // Add First Product
        productIn4Page = homePage.SelectFistBestSelling();
        productIn4Page.AddToCart();

        // Add Second Product
        homePage = productIn4Page.SignInHomePage();
        productIn4Page = homePage.SelectFirstNewProduct();
        productIn4Page.AddToCart();
    }

//  CheckoutPage
    @Test(priority = 6)
    public void CheckoutProcess(){
    dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));
    checkoutPage = dashBoardPage.SignInCheckoutPage();


    checkoutPage.AddNewAdd(
      PropertiesHelper.getValue("user_add"),
      PropertiesHelper.getValue("user_state"),
      PropertiesHelper.getValue("user_city"),
      PropertiesHelper.getValue("user_postal_code"),
      PropertiesHelper.getValue("user_phone_shipping")
    );
    checkoutPage.ChangeToDelivery();
    WebUI.Sleep(3);
    checkoutPage.ChangeToPayment();
    String result = checkoutPage.ConfirmOrder("Yeu Thay An");
    PropertiesHelper.setValue("user_code_order" , result);
    }

//  Full Step
    @Test(priority = 7)
    public void CheckFullStep(){
        dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));
        homePage = dashBoardPage.HomePageSignIn();

        // Add Product
        productIn4Page = homePage.SelectFistBestSelling();
        productIn4Page.AddToCart();

        checkoutPage = productIn4Page.SignInCheckoutPage();
        checkoutPage.AddNewAdd(
                  PropertiesHelper.getValue("user_add"),
                  PropertiesHelper.getValue("user_state"),
                  PropertiesHelper.getValue("user_city"),
                  PropertiesHelper.getValue("user_postal_code"),
                  PropertiesHelper.getValue("user_phone_shipping")
          );
          checkoutPage.ChangeToDelivery();
          WebUI.Sleep(3);
          checkoutPage.ChangeToPayment();
          String result = checkoutPage.ConfirmOrder("Yeu Thay An");
          PropertiesHelper.setValue("user_code_order" , result);
    }

//  CheckCartAfterAdd
    @Test(priority = 8)
    public void CheckAddProduct() throws Exception {
        dashBoardPage = signInPage.SignInDashBoardPage(PropertiesHelper.getValue("valid_username"), PropertiesHelper.getValue("valid_password"));
        homePage = dashBoardPage.HomePageSignIn();
        productIn4Page = homePage.SelectFistBestSelling();
        productIn4Page.AddMoreStock(5);
        productIn4Page.RemoveStock(2);

        WebUI.Sleep(3);
        productIn4Page.SaveValTaking();
        productIn4Page.AddToCart();

        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("./ValWhenTaking.xlsx", "Sheet1");
        viewCartPage = productIn4Page.SignInViewCartPage();
        WebUI.verifyEquals(viewCartPage.TakeProductName() , excel.getCellData(1,0) , "Name in before and after taking");
        WebUI.verifyEquals(viewCartPage.TakeTotalMoney()  , excel.getCellData(1,1) , "Price in before and after taking");
        WebUI.verifyEquals(viewCartPage.TakeTotalQuantity() , excel.getCellData(1,2) , "TotalQuantity in before and after taking");
        WebUI.verifyEquals(viewCartPage.TakePrice(), excel.getCellData(1,3) , "TotalMoney in before and after taking");
    }

}
