package Pages.AdminPages.ProductPages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import utils.driver.WebUI;

import java.awt.*;
import java.io.File;

public class AddNewProductsPage {
    // Product Form
    private By ProductName = By.xpath("//input[@placeholder='Product Name']");
    private By SelectCategory = By.xpath("//button[@data-id= 'category_id']");
    private By InputCategory = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By SelectBrand = By.xpath("//div[contains(text(),'Select Brand')]");
    private By InputBrand = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By InputUnit = By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']");
    private By InputWeight = By.xpath("//input[@name='weight']");
    private By KeywordTag = By.xpath("//span[@role='textbox']");

    // Product Image
    private By SelectGalleryImg = By.xpath("((//small[normalize-space()='(600x600)'])/parent::label)/following-sibling::div/div[1]");
    private By SelectThumbnailImg = By.xpath("((//small[normalize-space()='(300x300)'])/parent::label)/following-sibling::div/div[1]");

    private By UploadNewImg = By.xpath("//a[normalize-space()='Upload New']");
    private By UpFileImg = By.xpath("//button[normalize-space()='Browse']");
    private By AddFileImg = By.xpath("//button[normalize-space()='Add Files']");
    private By SelectFirstImg = By.xpath("//div[@class='aiz-uploader-all clearfix c-scrollbar-light']//div[1]//div[1]//div[1]//div[1]");

    // Product Videos
    private By VideoProductButton = By.xpath("//button[@title='Youtube']");
    private By SelectYoutube = By.xpath("//span[normalize-space()='Youtube']");
    private By SelectDailyMotion = By.xpath("//span[normalize-space()='Dailymotion']");
    private By SelectVimeo = By.xpath("//span[normalize-space()='Vimeo']");

    private By InputVideoLink = By.xpath("//input[@placeholder='Video Link']");

    // Product Price + Stock
    private By InputUnitProduct = By.xpath("//input[@placeholder='Unit price']");
    private By InputDiscountDay = By.xpath("//input[@placeholder='Select Date']");
    private By ConfirmDiscountDay = By.xpath("//button[@class='applyBtn btn btn-sm btn-primary']");
    private By InputDiscount = By.xpath("//input[@placeholder='Discount']");
    private By SelectUnitDiscount = By.xpath("(//div[@class='filter-option-inner-inner'][normalize-space()='Flat'])[1]");
    private By SelectUnitDiscountPercent = By.xpath("//span[normalize-space()='Percent']");
    private By InputQuantity = By.xpath("//input[@placeholder='Quantity']");
    private By InputSKU = By.xpath("//input[@placeholder='SKU']");

    // Description
    private By InputDescription = By.xpath("//div[@role='textbox']//p");

    // Save and Submit
    private By SaveSubmitButton = By.xpath("//button[normalize-space()='Save & Publish']");
    // Check
    private By CheckProductIn4Form = By.xpath("//h5[normalize-space()='Product Information']");

    private By CheckProductImageForm = By.xpath("//h5[normalize-space()='Product Images']");
    private By CheckUploadImgForm = By.xpath("//a[normalize-space()='Select File']");
    private By CheckUpLoadFileImg = By.xpath("//div[@class='uppy-StatusBar is-complete']");

    private By CheckProductVideoForm = By.xpath("//h5[normalize-space()='Product Images']");

    private By CheckProductPriceStockForm = By.xpath("//h5[normalize-space()='Product price + stock']");

    private By CheckProductDescriptionForm = By.xpath("//h5[normalize-space()='Product Description']");

    private By CheckAddProductSuccess = By.xpath("//span[@data-notify='message']");


    public void AddProductIn4(String name, String category, String brand, String unit, String weight, String keywords) {
        WebUI.verifyAssertTrueIsDisplayed(CheckProductIn4Form, "Product Information Form is not displayed");

        WebUI.SendKeys(ProductName, name + " " + RandomStringUtils.randomAlphabetic(5));
        WebUI.ClickElementBy(SelectCategory);
        WebUI.Sleep(2);
        WebUI.SendKeysEnter(InputCategory, category);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SelectBrand);
        WebUI.Sleep(2);
        WebUI.SendKeysEnter(InputBrand, brand);
        WebUI.Sleep(2);
        WebUI.SendKeys(InputUnit, unit);
        WebUI.Sleep(1);
        WebUI.CleanAdd(InputWeight, weight);
        WebUI.Sleep(1);
        WebUI.SendKeys(KeywordTag, keywords);
    }

    public void AddProductImage(String Url600, String Url300) throws AWTException {
        WebUI.verifyAssertTrueIsDisplayed(CheckProductImageForm, "Product image form is not displayed");

        // Gallery img
        WebUI.ClickElementBy(SelectGalleryImg);
        WebUI.verifyAssertTrueIsDisplayed(CheckUploadImgForm, "Gallery img form is not displayed");
        WebUI.ClickElementBy(UploadNewImg);
        WebUI.Sleep(3);
        WebUI.ClickElementBy(UpFileImg);
        String UrlGalleryImage = new File(System.getProperty("user.dir") + "/DataTest/" + Url600).getAbsolutePath();
        WebUI.RobotPaste(UrlGalleryImage);
        WebUI.verifyAssertTrueIsDisplayed(CheckUpLoadFileImg, "Up file gallery img fail");
        WebUI.Sleep(3);
        WebUI.ClickElementBy(AddFileImg);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SelectGalleryImg);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SelectFirstImg);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(AddFileImg);
        WebUI.Sleep(2);

        // Thumbnail Image
        WebUI.ClickElementBy(SelectThumbnailImg);
        WebUI.verifyAssertTrueIsDisplayed(CheckUploadImgForm, "Thumbnail img form is not displayed");
        WebUI.ClickElementBy(UploadNewImg);
        WebUI.Sleep(3);
        WebUI.ClickElementBy(UpFileImg);
        String UrlThumbnailImage = new File(System.getProperty("user.dir") + "/DataTest/" + Url300).getAbsolutePath();
        WebUI.RobotPaste(UrlThumbnailImage);
        WebUI.verifyAssertTrueIsDisplayed(CheckUpLoadFileImg, "Up file gallery img fail");
        WebUI.Sleep(3);
        WebUI.ClickElementBy(AddFileImg);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SelectThumbnailImg);
        WebUI.Sleep(2);
        WebUI.ClickElementBy(SelectFirstImg);
        WebUI.Sleep(3);
        WebUI.ClickElementBy(AddFileImg);
        WebUI.Sleep(2);
    }

    public void AddProductVideos(String type, String UrlVideo) {
        WebUI.verifyAssertTrueIsDisplayed(CheckProductVideoForm, "Product videos field is not displayed");

        WebUI.ClickElementBy(VideoProductButton);
        WebUI.Sleep(2);

        String videoDemo = type.toLowerCase();
        if ("youtube".equals(videoDemo)) {
            WebUI.ClickElementBy(SelectYoutube);
        } else if ("vimeo".equals(videoDemo)) {
            WebUI.ClickElementBy(SelectVimeo);
        } else {
            WebUI.ClickElementBy(SelectDailyMotion);
        }

        WebUI.Sleep(2);
        WebUI.SendKeys(InputVideoLink, UrlVideo);
    }

    public void ProductPriceStock(String UnitProduct, String DiscountDay, String Discount, String UnitDiscountType, String Quantity, String SKU) {
        WebUI.verifyAssertTrueIsDisplayed(CheckProductPriceStockForm, "Product prices and stocks field is not displayed");

        WebUI.CleanAdd(InputUnitProduct, UnitProduct);
        WebUI.Sleep(3);
        WebUI.SendKeys(InputDiscountDay, DiscountDay);
        WebUI.ClickElementBy(ConfirmDiscountDay);
        WebUI.Sleep(3);
        WebUI.CleanAdd(InputDiscount, Discount);
        WebUI.Sleep(3);

        String tmp = UnitDiscountType.toLowerCase();
        if (tmp.equals("percent")) {
            WebUI.ClickElementBy(SelectUnitDiscount);
            WebUI.Sleep(3);
            WebUI.ClickElementBy(SelectUnitDiscountPercent);
        }
        WebUI.Sleep(3);
        WebUI.CleanAdd(InputQuantity, Quantity);
        WebUI.Sleep(3);
        WebUI.SendKeys(InputSKU, SKU);
        WebUI.Sleep(3);

    }

    public void ProductDescription(String description) {
        WebUI.verifyAssertTrueIsDisplayed(CheckProductDescriptionForm, "Product Description field is not displayed");
        WebUI.SendKeys(InputDescription, description);
    }

    public void SaveSubmit() {
        WebUI.ClickElementBy(SaveSubmitButton);
        WebUI.verifyAssertTrueIsDisplayed(CheckAddProductSuccess, "Submit new product failed");
    }

}
