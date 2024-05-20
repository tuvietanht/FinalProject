package Tests;

import Common.SetupBrowser;
import helpers.ExcelHelpers;
import helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class Add2ProductInToCartTest extends SetupBrowser {
    @Test(priority = 0)
    public void Add2Product() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));

        // Add First Product
        orderPage = userDashBoardPage.SearchProduct1(PropertiesHelper.getValue("product_1"));
        orderPage.AddProductInCart();

        // Add Second Product
        orderPage = userDashBoardPage.SearchProduct2(PropertiesHelper.getValue("product_2"));
        orderPage.AddProductInCart();


    }

}
