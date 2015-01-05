package com.epam.testexternalpart.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.epam.testexternalpart.screen.AllCandidatesPage;
import com.epam.testexternalpart.screen.Menu;
import com.epam.testexternalpart.screen.departments.AddDepartmentPage;
import com.epam.testexternalpart.screen.departments.Departments;
import com.epam.testexternalpart.screen.profile.CreateProfilePage;
import com.epam.testexternalpart.screen.profile.EditProfilePage;
import com.epam.testexternalpart.screen.profile.ViewProfilePage;
import com.epam.testexternalpart.screen.stream.AddStreamPage;
import com.epam.testexternalpart.screen.stream.EditStreamPage;
import com.epam.testexternalpart.screen.stream.StreamPage;
import com.epam.testexternalpart.screen.LoginPage;

public abstract class BaseTest {
	protected WebDriver driver;
	private final String START_TEST="http://epuakhaw0162:8080/KhExternalPreProdPortal";
	private final String START_DEPARTMENT="http://epuakhaw0162:8080/KhExternalPreProdPortal/departments";
	protected Menu menuComp;
	protected Departments pageDepartment;
	protected CreateProfilePage pageCreateProfile;
	protected ViewProfilePage pageViewProfile;
	protected EditProfilePage pageEditProfile;
	protected AddDepartmentPage pageAddDepartment;
	protected StreamPage pageStream;
	protected AddStreamPage pageAddStream;
	protected EditStreamPage pageEditStream;
	protected LoginPage LoginPage;
	protected AllCandidatesPage pageAllCandidate;
		
	
	@BeforeClass
	public void init() {
		driver = WebDriverFactory.initDriver("");//new FirefoxDriver();
		driver.get(START_TEST);
		LoginPage=new LoginPage(driver);
		LoginPage.signIn("admin","admin");	
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void startPage() {
		driver.get(START_DEPARTMENT);
		pageDepartment = new Departments(driver);
		pageAddDepartment =new AddDepartmentPage(driver);
		pageAddStream = new AddStreamPage(driver);
		pageStream = new StreamPage(driver);
		pageEditStream = new EditStreamPage(driver);
		pageCreateProfile = new CreateProfilePage(driver);
		pageViewProfile = new ViewProfilePage(driver);
		menuComp = new Menu(driver);
		pageAllCandidate = new AllCandidatesPage(driver);
		pageEditProfile = new EditProfilePage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	    driver.quit();
	
	}

	@DataProvider(name = "testData")
	public Object[][] getTestData(Method testMethod) {
		Object[][] testData = null;
		String a = testMethod.getName();
		String b = testMethod.getDeclaringClass().getSimpleName();
		int numberOfParameters = testMethod.getParameterTypes().length;
		String path = "C:/Users/Olesia_Kotsiuba/Desktop/TestExternalPart/data/" + b + ".xlsx";

		try {
			FileInputStream file = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(a);
			testData = fillData(sheet, 1, numberOfParameters);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return testData;
	}

	private static String[][] fillData(Sheet sheet, int startColumn,
			int endColumn) {
		if (startColumn == 0)
			throw new RuntimeException("startColumn value should begin from 1");
		if (endColumn < startColumn)
			throw new RuntimeException(
					"endColumn number cannot be less than startColumn numbere");
		int rows = sheet.getPhysicalNumberOfRows();
		int numberOfParameters = endColumn - startColumn + 1;
		String[][] returnValue = new String[rows - 1][numberOfParameters];
		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			int jj = 0;
			for (int j = startColumn - 1; j < endColumn; j++) {
				int cellType;
				try {
					cellType = row.getCell(j).getCellType();
				} catch (NullPointerException ex) {
					returnValue[i - 1][jj] = "";
					continue;
				}
				String str;
				switch (cellType) {
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(row.getCell(j))) {
						returnValue[i - 1][jj] = String.valueOf(row.getCell(j)
								.getDateCellValue());
					} else {
						returnValue[i - 1][jj] = String.valueOf(row.getCell(j)
								.getNumericCellValue());
					}
					break;

				case Cell.CELL_TYPE_STRING:
					str = row.getCell(j).getStringCellValue().trim();
					returnValue[i - 1][jj] = str;
					break;

				case Cell.CELL_TYPE_BOOLEAN:
					returnValue[i - 1][jj] = Boolean.toString(row.getCell(j)
							.getBooleanCellValue());
					break;

				default:
					returnValue[i - 1][jj] = "";
				}
				jj++;
			}
		}
		return returnValue;
	}

}