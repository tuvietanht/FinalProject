package DataProviders;

import helpers.ExcelHelpers;
import helpers.Helpers;
import org.testng.annotations.DataProvider;

public class AddNewProductData {
    @DataProvider(name = "Data_ProductIn4")
    public Object[][] dataLoginHRM() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getExcelDataHashTable(Helpers.getCurrentDir() + "datatest/AddNewProductAka47.xlsx", "ProductIn4", 1, 1);
        return data;
    }

}

