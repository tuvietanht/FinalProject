package Tests;

import Common.SetupBrowser;
import DataProviders.AddNewProductData;
import helpers.ExcelHelpers;
import org.testng.annotations.Test;
import utils.driver.WebUI;

import java.util.Hashtable;

public class CreateNewProductByAdminTest extends SetupBrowser {
    @Test(dataProvider = "Data_ProductIn4", dataProviderClass = AddNewProductData.class)
    public void testCreateNewProductByAdmin(Hashtable<String, String> data) throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        adminDashBoardPage = getSignInPage().LoginSuccessAdminPage(excel.getCellData("email", 4), excel.getCellData("password", 4));

        addNewProductsPage = adminDashBoardPage.SignInAddNewProductPage();
        addNewProductsPage.AddProductIn4(data.get("Name"), data.get("Category"), data.get("Brand"), data.get("Unit"), data.get("Weight"), data.get("KeywordTag"));
        addNewProductsPage.AddProductImage(data.get("Img600"), data.get("Img300"));
        addNewProductsPage.AddProductVideos(data.get("DemoType"), data.get("UrlVideo"));
        addNewProductsPage.ProductPriceStock(data.get("UnitProduct"), data.get("DiscountDay"), data.get("Discount"), data.get("UnitDiscount"), data.get("Quantity"), data.get("SKU"));
        addNewProductsPage.ProductDescription(data.get("Description"));
        addNewProductsPage.SaveSubmit();
    }

    @Test(priority = 1, dataProvider = "Data_ProductIn4", dataProviderClass = AddNewProductData.class)
    public void VerifyNewProduct(Hashtable<String, String> data) throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
        orderPage = userDashBoardPage.SearchNewproducts("ak47");
        WebUI.Sleep(3);
        orderPage.VerifyIn4NewProduct(data.get("Name"), data.get("UnitProduct"), data.get("Unit"), data.get("Quantity"), data.get("Description"));
    }


}