package Selenium.Java_Assignment;

import org.testng.annotations.Test;

import org.testng.Assert;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.DataFormatter;

import TestComponents.BaseTest;
import pageObjects.LanguageCurrencyPage;

public class VerifyPlan extends BaseTest {
	DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "testData")
	public void verifyPlan(String country, String Data, String valid, String planType, String price)
			throws IOException {

		homepage.clickOnsearchLanguageCurrencyCursorPointer();
		languageCurrencyPage = new LanguageCurrencyPage(driver);
		languageCurrencyPage.waitForcurrencyPointerElement();
		languageCurrencyPage.clickOnCurrencyPointer();
		languageCurrencyPage.clickOnEuro();
		homepage.enterInputToSearchDestination("Thailand");

		ArrayList<String> expecteddetails = new ArrayList<String>();
		expecteddetails.add(country);
		expecteddetails.add(Data);
		expecteddetails.add(valid);
		expecteddetails.add(planType);
		expecteddetails.add(price);

		for (String planDetail : expecteddetails) {
			boolean ispresent = homepage.verifyPlanDetails(planDetail);

			Assert.assertTrue(ispresent);

		}

	}

	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Resources//TestingData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(1);
		int colcount = row.getLastCellNum();

		Object data[][] = new Object[rowcount - 1][colcount];
		for (int i = 0; i < rowcount - 1; i++) {

			row = sheet.getRow(i + 1);
			for (int j = 0; j < colcount; j++) {
				XSSFCell cell = row.getCell(j);

				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		wb.close();
		return data;
	}

}
