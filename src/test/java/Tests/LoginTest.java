package Tests;

import Common.SetupBrowser;
import helpers.ExcelHelpers;
import org.testng.annotations.Test;

public class LoginTest extends SetupBrowser {
    @Test()
    public void loginFailWithNullEmail() {
        getSignInPage().LoginFailWithNullEmail("", "123456");
    }

    @Test(priority = 1)
    public void loginFailWithNullPassword() {
        getSignInPage().LoginFailWithNullPassword("customer@example.com", "");
    }

    @Test(priority = 2)
    public void loginFailWithNullEmailPassword() {
        getSignInPage().LoginFailWithNullEmailPassword();
    }

    @Test(priority = 3)
    public void loginFailWithNotExistEmail() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        getSignInPage().LoginFailWithNotExistEmail(excel.getCellData("email", 1), excel.getCellData("password", 1));
    }

    @Test(priority = 4)
    public void loginFailWithInvaldPassword() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        getSignInPage().LoginFailWithWrongPassword(excel.getCellData("email", 2), excel.getCellData("password", 2));
    }

    @Test(priority = 5)
    public void loginSuccessWithCustomerAccount() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
    }

}