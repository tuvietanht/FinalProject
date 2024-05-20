package Tests;

import Common.SetupBrowser;
import helpers.ExcelHelpers;
import helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class TakeIn4ProductTest extends SetupBrowser {
    @Test()
    public void TakeIn4() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
        orderPage = userDashBoardPage.SearchProduct1(PropertiesHelper.getValue("product_1"));

        orderPage.TakeIn4Product();
    }
}