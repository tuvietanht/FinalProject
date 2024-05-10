package Parallel.dataprovider;

import helpers.ExcelHelpers;
import helpers.Helpers;
import org.testng.annotations.DataProvider;

public class DataProviderManager {
    @DataProvider(name = "data_provider_login", parallel = true)
    public Object[][] dataLoginHRM() {
        return new Object[][]{{"frances.burns", "frances.burns"}};
    }


    @DataProvider(name = "data_provider_login_from_excel", parallel = true)
    public Object[][] dataLoginHRMFromExcel() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getExcelData(Helpers.getCurrentDir() + "src/test/resources/Login.xlsx", "Sheet1");
        return data;
    }

    @DataProvider(name = "data_provider_login_from_excel_by_row")
    public Object[][] dataLoginHRMFromExcelByRow() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getExcelDataHashTable(Helpers.getCurrentDir() + "src/test/resources/Login.xlsx", "Sheet1", 1, 1);
        return data;
    }

}

