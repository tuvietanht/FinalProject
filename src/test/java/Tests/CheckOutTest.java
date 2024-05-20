package Tests;

import Common.SetupBrowser;
import helpers.ExcelHelpers;
import helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class CheckOutTest extends SetupBrowser {
    @Test
    public void CheckOutProcess() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("DataTest/AccountLogin.xlsx", "Login");
        userDashBoardPage = getSignInPage().LoginSuccessWithCustomerAccount(excel.getCellData("email", 3), excel.getCellData("password", 3));
        checkoutPage = userDashBoardPage.SignInCheckoutPage();

        checkoutPage.CheckMyCartStep();

        checkoutPage.ChangeToShippingStep();
        checkoutPage.CheckShippingStep(
                PropertiesHelper.getValue("user_add"),
                PropertiesHelper.getValue("user_state"),
                PropertiesHelper.getValue("user_city"),
                PropertiesHelper.getValue("user_postal_code"),
                PropertiesHelper.getValue("user_phone_shipping")
        );

        checkoutPage.ChangeToDeliveryStep();
        checkoutPage.CheckDeliveryStep();

        checkoutPage.ChangeToPaymentStep();
        checkoutPage.CheckPaymentPage();
        PropertiesHelper.setValue("user_code_order", checkoutPage.ConfirmOrderCode());
    }

}
