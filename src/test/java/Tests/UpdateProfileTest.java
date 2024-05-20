package Tests;

import Common.SetupBrowser;
import helpers.ExcelHelpers;
import helpers.PropertiesHelper;
import org.testng.annotations.Test;

import java.io.File;


public class UpdateProfileTest extends SetupBrowser {
    @Test()
    public void UpdateProfile() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
        profilePage = userDashBoardPage.ProfileSignInPage();

        profilePage.SetName(PropertiesHelper.getValue("user_name"));
        profilePage.SetPhone(PropertiesHelper.getValue("user_phone"));

        String UrlImage = new File(System.getProperty("user.dir") + "/DataTest/Avatar.jpg").getAbsolutePath();
        profilePage.SetPhoto(UrlImage);

        profilePage.SetPassword(PropertiesHelper.getValue("user_password"));
        profilePage.SetRePassword(PropertiesHelper.getValue("user_repassword"));

        profilePage.ClickUpdateProfile();
    }

    @Test(priority = 1)
    public void UpdateAddress() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
        profilePage = userDashBoardPage.ProfileSignInPage();

        profilePage.AddNewAdd(
                PropertiesHelper.getValue("user_add"),
                PropertiesHelper.getValue("user_state"),
                PropertiesHelper.getValue("user_city"),
                PropertiesHelper.getValue("user_postal_code"),
                PropertiesHelper.getValue("user_phone_shipping")
        );
    }

    @Test(priority = 2)
    public void UpdateEmail() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
        profilePage = userDashBoardPage.ProfileSignInPage();

        profilePage.updateEmail(excel.getCellData("email", 1));
    }
}
