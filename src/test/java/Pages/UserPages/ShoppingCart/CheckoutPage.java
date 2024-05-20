package Pages.UserPages.ShoppingCart;

import org.openqa.selenium.By;
import utils.driver.WebUI;

public class CheckoutPage {
    // 1.My cart Page
    private By CheckInMyCartPage = By.xpath("//div[@class='text-center text-primary']//descendant::h3");
    private By FinishMyCartPage = By.xpath("(//div[@class='text-center text-success'])[1]");
    private By EachProductMoney = By.xpath("//section[@id='cart-summary']//li[?]//span[normalize-space() = 'Price']/following-sibling::span");
    private By EachProductVal = By.xpath("//section[@id='cart-summary']//li[?]//button[@class='btn col-auto btn-icon btn-sm btn-circle btn-light']/following-sibling::input");
    private By EachTotalProductMoney = By.xpath("//section[@id='cart-summary']//li[?]//span[@class='fw-600 fs-16 text-primary']");
    private By EachProductTax = By.xpath("//section[@id='cart-summary']//li[?]//span[normalize-space() = 'Tax']/following-sibling::span");
    private By TotalMoney = By.xpath("//span[@class='fw-600 fs-17']");

    private By ChangeToShippingPage = By.xpath("//a[normalize-space()='Continue to Shipping']");
    // 2.Shipping Info Page
    private By CheckInShippingPage = By.xpath("//div[@class='text-center text-primary']//descendant::h3");
    private By FinishShippingPage = By.xpath("(//div[@class='text-center text-success'])[2]");

    private By CreateNewAdd = By.xpath("//div[@class='alpha-7']");
    private By ClickAdd = By.xpath("//textarea[@placeholder='Your Address']");

    private By SelectCountry = By.xpath("//div[contains(text(),'Select your country')]");
    private By VN = By.xpath("//span[@class='text'][normalize-space()='Vietnam']");

    private By SelectState = By.xpath("//div[contains(text(),'Select State')]");
    private By WriteState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By SelectCity = By.xpath("//div[contains(text(),'Select City')]");
    private By WriteCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By PortCode = By.xpath("//input[@placeholder='Your Postal Code']");

    private By PhoneAdd = By.xpath("//input[@placeholder='+880']");

    private By SaveAdd = By.xpath("//button[normalize-space()='Save']");

    private By AddAfterCreating = By.xpath("//span[normalize-space()='?PHONE']/ancestor::span[1]");

    private By SignAddressForm = By.xpath("//form[@role='form']//div[@class='modal-body']");

    private By ChangeToDeliveryPage = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    // 3.Delivery Info Page
    private By CheckInDeliveryPage = By.xpath("//div[@class='text-center text-primary']//descendant::h3");
    private By CheckInDeliveryPage2 = By.xpath("//h5[normalize-space()='Active eCommerce CMS Products']");

    private By FinishDeliveryPage = By.xpath("(//div[@class='text-center text-success'])[3]");
    private By CheckDeliveryType = By.xpath("//h6[normalize-space()='Choose Delivery Type']");
    private By HomeDeliveryButton = By.xpath("(//span[contains(text(),'Home Delivery')])");
    private By ListProduct = By.xpath("//li/ancestor::div[@class='card-body']//ul[@class='list-group list-group-flush']");

    private By ChangeToPaymentPage = By.xpath("//button[normalize-space()='Continue to Payment']");
    // 4.Payment Page
    private By CheckInPaymentPage = By.xpath("//div[@class='text-primary text-center']//descendant::h3");
    private By CheckInPayMentPage2 = By.xpath("//h3[normalize-space()='Select a payment option']");

    private By CheckPaymentOptions = By.xpath("//div[@class='card-body text-center']//label[1]");
    private By NoteAddition = By.xpath("//textarea[@placeholder='Type your text']");
    private By CurMoneyItem = By.xpath("//tbody/tr[?]//span");
    private By TotalItemsInSummary = By.xpath("//div[@class='card-header']//span");
    private By Tax = By.xpath("(//th[normalize-space()='Tax']/parent::tr)//span");
    private By ShippingFees = By.xpath("(//th[normalize-space()='Total Shipping']/parent::tr)//span");
    private By SubTotal = By.xpath("(//th[normalize-space()='Subtotal']/parent::tr)//span");
    private By Total = By.xpath("(//th[normalize-space()='Total']/parent::tr)//strong//span");
    private By CodeField = By.xpath("//input[@placeholder='Have coupon code? Enter here']");

    private By AgreeTerm = By.xpath("//label[@class='aiz-checkbox']");
    private By CompleteOrderButton = By.xpath("//button[normalize-space()='Complete Order']");
    private By SuccessAnnouncement = By.xpath("//span[@data-notify='message']");
    private By Confirmation = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");


    private By CheckCode = By.xpath("//h2[@class='h5']//span");

    // Check CountVal by CartExternal
    private By CountValCartExternal = By.xpath("//span[@class='badge badge-primary badge-inline badge-pill cart-count']");


    // Check My cart Page
    public void CheckMyCartStep() {
        WebUI.verifyEquals(WebUI.GetText(CheckInMyCartPage), "1. My Cart", "Currently not in MyCart Page");

        int totalVal = Integer.parseInt(WebUI.GetText(CountValCartExternal));
        int CheckTotalMoney = 0;
        for (int i = 1; i <= totalVal; i++) {
            By TakeCurProductMoney = By.xpath("//section[@id='cart-summary']//li[" + i + "]//span[normalize-space() = 'Price']/following-sibling::span");
            By TakeCurProductTax = By.xpath("//section[@id='cart-summary']//li[" + i + "]//span[normalize-space() = 'Tax']/following-sibling::span");
            By TakeCurProductVal = By.xpath("//section[@id='cart-summary']//li[" + i + "]//button[@class='btn col-auto btn-icon btn-sm btn-circle btn-light']/following-sibling::input");
            By TakeTotalCurMoneyProduct = By.xpath("//section[@id='cart-summary']//li[" + i + "]//span[@class='fw-600 fs-16 text-primary']");

            int CurProductTax = Integer.parseInt(WebUI.GetText(TakeCurProductTax).replace("$", "").replace(",", "").replace(".", ""));
            int CurProductMoney = Integer.parseInt(WebUI.GetText(TakeCurProductMoney).replace("$", "").replace(",", "").replace(".", ""));
            int CurProductVal = Integer.parseInt(WebUI.GetAttributeValue(TakeCurProductVal, "value").replace("$", "").replace(",", "").replace(".", ""));
            int TotalCurMoneyProduct = Integer.parseInt(WebUI.GetText(TakeTotalCurMoneyProduct).replace("$", "").replace(",", "").replace(".", ""));

            WebUI.verifyEquals(((CurProductMoney + CurProductTax) * CurProductVal), TotalCurMoneyProduct, "An error occurred while verifying the total cost for item");

            CheckTotalMoney += TotalCurMoneyProduct;
        }
        int ActualSubTotal = Integer.parseInt(WebUI.GetText(TotalMoney).replace("$", "").replace(",", "").replace(".", ""));
        WebUI.verifyEquals(CheckTotalMoney, ActualSubTotal, "An error occurred while verifying the total cost for item about checkTotalMoney and ActualSubTotal");
    }


    // Move to ShippingPage
    public void ChangeToShippingStep() {
        WebUI.ClickElementBy(ChangeToShippingPage);
        WebUI.Sleep(2);
        WebUI.verifyEquals(WebUI.GetText(CheckInShippingPage), "2. Shipping info", "Currently not in Shipping Info Page");

        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(FinishMyCartPage, "My Cart Page is not finished yet");
    }

    public void CheckShippingStep(String add, String state, String city, String postalCode, String phone) {
        WebUI.Move(CreateNewAdd);
        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(CreateNewAdd, "Add NewAddress button is not displayed");
        WebUI.ClickElementBy(CreateNewAdd);
        WebUI.SendKeys(ClickAdd, add);
        WebUI.verifyAssertTrueIsDisplayed(SignAddressForm, "Address form is not displayed");
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectCountry);
        WebUI.ClickElementBy(VN);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectState);
        WebUI.Sleep(2);
        WebUI.SendKeysEnter(WriteState, state);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectCity);
        WebUI.Sleep(3);
        WebUI.SendKeysEnter(WriteCity, city);
        WebUI.Sleep(2);

        WebUI.SendKeys(PortCode, postalCode);
        WebUI.SendKeys(PhoneAdd, phone);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SaveAdd);

        By FieldAfterCreating = By.xpath("//span[normalize-space()='" + phone + "']/ancestor::span[1]");
        WebUI.verifyAssertTrueIsDisplayed(FieldAfterCreating, "Address After Creating not yet implemented");
        WebUI.ClickElementBy(FieldAfterCreating);
    }


    // Move to Delivery Info Page
    public void ChangeToDeliveryStep() {
        WebUI.ClickElementBy(ChangeToDeliveryPage);
        WebUI.Sleep(2);
        WebUI.verifyEquals(WebUI.GetText(CheckInDeliveryPage), "3. Delivery info", "Currently not in Delivery Info Page");
        WebUI.verifyAssertTrueIsDisplayed(CheckInDeliveryPage2, "Currently not in Delivery Info Page");

        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(FinishMyCartPage, "My Cart Page is not finished yet");
        WebUI.verifyAssertTrueIsDisplayed(FinishShippingPage, "Shipping Page is not finished yet");
    }

    public void CheckDeliveryStep() {
        WebUI.verifyAssertTrueIsDisplayed(CheckDeliveryType, "Check Delivery Type is not displayed");
        WebUI.verifyAssertTrueIsDisplayed(HomeDeliveryButton, "Delivery to Home is not displayed");
        WebUI.verifyAssertTrueIsDisplayed(ListProduct, "List Products is not displayed");
    }


    // Move to Payment Page
    public void ChangeToPaymentStep() {
        WebUI.ClickElementBy(ChangeToPaymentPage);
        WebUI.Sleep(2);
        WebUI.verifyEquals(WebUI.GetText(CheckInPaymentPage), "4. Payment", "Currently not in Payment Page");
        WebUI.verifyAssertTrueIsDisplayed(CheckInPayMentPage2, "Currently not in Payment Page");
    }

    public void CheckPaymentPage() {
        WebUI.verifyAssertTrueIsDisplayed(NoteAddition, "Additional Information does not appear");
        WebUI.SendKeys(NoteAddition, "Shipping at the Weekend");
        WebUI.Sleep(2);
        WebUI.verifyAssertTrueIsDisplayed(CheckPaymentOptions, "Don't have any Payment options");

        int TotalItems = Integer.parseInt(WebUI.GetText(TotalItemsInSummary).replaceAll("[^0-9]", ""));
        int TotalMoneyProduct = 0;
        for (int i = 1; i <= TotalItems; i++) {
            By curItem = By.xpath("//tbody/tr[" + i + "]//span");
            WebUI.verifyAssertTrueIsDisplayed(curItem, "Item" + i + "is not displayed");
            int curItemMoney = Integer.parseInt(WebUI.GetText(curItem).replace("$", "").replace(",", "").replace(".", ""));
            TotalMoneyProduct += curItemMoney;
        }
        int TotalSubtotal = Integer.parseInt(WebUI.GetText(SubTotal).replace("$", "").replace(",", "").replace(".", ""));
        WebUI.verifyEquals(TotalMoneyProduct, TotalSubtotal, "Subtotal calculation wrong");

        int TotalTax = Integer.parseInt(WebUI.GetText(Tax).replace("$", "").replace(",", "").replace(".", ""));
        int TotalShippingFees = Integer.parseInt(WebUI.GetText(ShippingFees).replace("$", "").replace(",", "").replace(".", ""));
        int FinalPrice = Integer.parseInt(WebUI.GetText(Total).replace("$", "").replace(",", "").replace(".", ""));

        WebUI.verifyEquals((TotalTax + TotalShippingFees + TotalSubtotal), FinalPrice, "Total Price not true after sum all fees");
        WebUI.verifyAssertTrueIsDisplayed(CodeField, "Discount Code sign field is not displayed");
        WebUI.Move(AgreeTerm);
        WebUI.Sleep(1);
        WebUI.verifyAssertTrueIsDisplayed(AgreeTerm, "Agree term is not displayed");
        WebUI.ClickElementBy(AgreeTerm);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(CompleteOrderButton);
        WebUI.verifyAssertTrueIsDisplayed(SuccessAnnouncement, "Success announcement is not displayed");
        WebUI.Sleep(1);
        WebUI.verifyAssertTrueIsDisplayed(Confirmation, "Confirmation Page is not displayed after CompletePaymentPage");
    }

    // Confirm
    public String ConfirmOrderCode() {
        return WebUI.GetText(CheckCode);
    }
}
