package com.epam.testexternalpart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.epam.testexternalpart.core.BaseTest;
import com.epam.testexternalpart.core.TestReporter;

public class AddCandidateTest extends BaseTest {	

   @Test(dataProvider="testData")
   public void addCandidateManually(String candData){
	TestReporter.writeToReportHeader("Check the possibility of adding �andidate manually");
	   pageDepartment.clickElement(pageDepartment.getTableEl(1,1), "First stream on first dep");
	   pageDepartment.clickElement( pageStream.addCandidateButton, "addCandidateBut");
	   pageCreateProfile.createNewCandidate(candData);
				//bug
				WebElement element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", element);
				//bug
	   pageStream.checkCandExisting(candData,true);
	   pageStream.deleteAddedCand();

	   //pageStream.checkCandExisting(candData,false);
   }
   
   
   @Test(dataProvider="testData")
   public void addCandidate(String candData){
	TestReporter.writeToReportHeader("Check the possibility of adding �andidate manually");
	   pageDepartment.clickElement(pageDepartment.getTableEl(1,1), "First stream on first dep");
	   pageDepartment.clickElement( pageStream.addCandidateButton, "addCandidateBut");
	   pageCreateProfile.createNewCandidateWithout(candData);

   }
  
   	@Test(dataProvider="testData")
	public void deleteAllCandidates(String mess){
		TestReporter.writeToReportHeader("Delete all Candidates");
		pageDepartment.clickElement(pageDepartment.getTableEl(1,1), "First stream on first dep");
		pageStream.clickElement(pageStream.checkboxForAll,"CheckboxForAll");
		pageStream.deleteAllCand(mess);
		   
}
  
   	@Test(dataProvider="testData")
	public void changeStatus(String candData,String status1,String status2,String status3,String status4,String candData2) throws InterruptedException{
		TestReporter.writeToReportHeader("Change  Candidat's status manually");
		TestReporter.writeToReportStep("1-Create candidate");
		pageDepartment.clickElement(pageDepartment.getTableEl(1,1), "First stream on first dep");
		Thread.sleep(4000);
		pageDepartment.clickElement( pageStream.addCandidateButton, "addCandidateBut");
		pageCreateProfile.createNewCandidate(candData);
		//bug
	   	Thread.sleep(4000);
	   	WebElement element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
	   	JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		//bug

		 pageStream.checkCandExisting(candData,true);

		
		TestReporter.writeToReportStep("2-Check candidat's displaying on \"Not tested\" tab");
		pageStream.clickElement(pageStream.notTestedCandidate,"notTestedCandidate");
		//bug
	   	Thread.sleep(4000);
	   	 element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
		 js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		 element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
		 js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		//bug
				
		pageStream.checkCandExisting(candData,true);

		TestReporter.writeToReportStep("4-Check candidat's displaying on \"confirmed\" tab");
		pageStream.clickViewCand();
		pageViewProfile.clickEditButton();
		pageEditProfile.changeStatus(status1);
		pageStream.clickElement(pageStream.confirmedTab,"notTestedCandidate");
		//bug
	   	Thread.sleep(4000);
	   	 element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
		 js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		//bug
				 pageStream.checkCandExisting(candData,true);
		
		
		TestReporter.writeToReportStep("4-Check candidat's displaying on \"test�completed\" tab");
		pageStream.clickViewCand();
		pageViewProfile.clickEditButton();
		pageEditProfile.changeStatus(status2);
		pageStream.clickElement(pageStream.testCompleteTab,"notTestedCandidate");
		//bug
	   	Thread.sleep(4000);
	   	 element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
		 js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		//bug
				 pageStream.checkCandExisting(candData,true);
		TestReporter.writeToReportStep("5-Check candidat's displaying on \"not actual\" tab");
		pageStream.clickViewCand();
		pageViewProfile.clickEditButton();
		pageEditProfile.changeStatus(status3);
		pageStream.clickElement(pageStream.notActualTab,"notTestedCandidate");
		//bug
	   	Thread.sleep(4000);
	   	 element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
		 js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		//bug
				 pageStream.checkCandExisting(candData,true);
		TestReporter.writeToReportStep("6-Check candidat's displaying on \"Banned\" tab");
		pageStream.clickViewCand();
		pageViewProfile.clickEditButton();
		pageEditProfile.changeStatus(status4);
		pageStream.clickElement(pageStream.bannedTab,"notTestedCandidate");
		//bug
	   	Thread.sleep(4000);
	   	 element = driver.findElement(By.xpath("//table[@id='table']/thead/tr/th/div[text()='Fill Date']"));
		 js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(4000);
		//bug
				pageStream.checkCandExisting(candData,true);
				pageStream.deleteAddedCand();

		   
}
  
}