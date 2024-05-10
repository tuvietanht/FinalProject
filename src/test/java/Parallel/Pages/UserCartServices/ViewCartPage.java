package Parallel.Pages.UserCartServices;

import Parallel.TestCase.WebUI;
import org.openqa.selenium.By;

public class ViewCartPage {
    private By ProductName = By.xpath("//span[@class='fs-14 opacity-60']");
    private By Price = By.xpath("//span[normalize-space()='Price']/following-sibling::span");
    private By TotalQuantity = By.xpath("//button[@data-type='minus']/following-sibling::input");
    private By TotalMoney = By.xpath("//span[@class='fw-600 fs-17']");

    public String TakeProductName(){return WebUI.GetText(ProductName);}
    public String TakePrice(){return WebUI.GetText(Price);}
    public String TakeTotalMoney() {return WebUI.GetText(TotalMoney);}
    public String TakeTotalQuantity(){return WebUI.GetAttributeValue(TotalQuantity , "value");}
}
