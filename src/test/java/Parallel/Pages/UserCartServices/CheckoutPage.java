package Parallel.Pages.UserCartServices;

import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;

public class CheckoutPage {
    // Shipping address
    private By CreateNewAdd = By.xpath("//div[@class='alpha-7']");
    private By ClickAdd = By.xpath("//textarea[@placeholder='Your Address']");

    private By SelectCountry = By.xpath("//div[contains(text(),'Select your country')]");
    private By VN = By.xpath("//span[@class='text'][normalize-space()='Vietnam']");

    private By SelectState = By.xpath("//div[contains(text(),'Select State')]");
    private By WriteState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By SelectCity = By.xpath("//div[contains(text(),'Select City')]");
    private By WriteCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By PortCode = By.xpath("//input[@placeholder='Your Postal Code']");

    private By Phone = By.xpath("//input[@placeholder='+880']");

    private By SaveAdd = By.xpath("//button[normalize-space()='Save']");

    private By SelectAdd = By.xpath("//span[normalize-space()='0908222333']/ancestor::span[1]");
    // DeliveryStep
    private By DeliveryStep = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    // PaymentStep
    private By PaymentStep = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By Note = By.xpath("//textarea[@placeholder='Type your text']");
    private By AgreeTerm = By.xpath("//label[@class='aiz-checkbox']");
    private By CompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private By CheckCode = By.xpath("//h2[@class='h5']");


    // Add address for shipping
    public void AddNewAdd(String add , String state , String city , String postalCode , String phone ){
        WebUI.ClickElementBy(CreateNewAdd);
        WebUI.SendKeys(ClickAdd , add);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectCountry);
        WebUI.ClickElementBy(VN);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectState);
        WebUI.Sleep(2);
        WebUI.SendKeysEnter(WriteState , state);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SelectCity);
        WebUI.Sleep(3);
        WebUI.SendKeysEnter(WriteCity , city);
        WebUI.Sleep(2);

        WebUI.SendKeys(PortCode , postalCode);
        WebUI.SendKeys(Phone , phone);
        WebUI.Sleep(2);

        WebUI.ClickElementBy(SaveAdd);

        WebUI.ClickElementBy(SelectAdd);
    }

    // Change to DeliveryStep
    public void ChangeToDelivery(){WebUI.ClickElementBy(DeliveryStep);}

    // Change to PaymentStep
    public void ChangeToPayment(){WebUI.ClickElementBy(PaymentStep);}

    // Confirm
    public String ConfirmOrder(String note){
        WebUI.SendKeys(Note, note);
        WebUI.ClickElementBy(AgreeTerm);
        WebUI.ClickElementBy(CompleteOrder);
        WebUI.Sleep(3);
        return WebUI.GetText(CheckCode);
    }
}
