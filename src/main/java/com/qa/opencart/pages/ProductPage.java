package com.qa.opencart.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class ProductPage extends TestBase{
	
	private ElementUtil elementUtil;
	
	@FindBy(name = "option[218]")
	WebElement availableOptionsRadio;	
	 
	@FindBy(css = "input[value='10']")
	WebElement availableOptionsCheckbox;	
	
	@FindBy(id = "input-option208")
	WebElement availableOptionsText;
	
	@FindBy(id = "input-option217")
	WebElement availableOptionsSelect;	

	@FindBy(id = "input-option209")
	WebElement availableOptionsTextarea;	
	
	@FindBy(id = "button-upload222")
	WebElement availableOptionsFileUpload;

	@FindBy(xpath = "//input[@name='option[219]']/following-sibling::span/button")
	WebElement selectDataButton;
	
	@FindBy(xpath = "//input[@name='option[221]']/following-sibling::span/button")
	WebElement selectTimeButton;
	
	@FindBy(xpath = "//input[@name='option[220]']/following-sibling::span/button")
	WebElement selectDataAndTimeButton;
	
	
	@FindBy(name = "quantity")
	WebElement quantityText;

	@FindBy(id = "button-cart")
	WebElement addCartButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-success')]")
	WebElement successMessage;
	
	private By addToCartBtn_radio = By.name( "option[218]");
	
	private By successMessage_xpath = By.xpath( "//div[contains(@class,'alert-success')]");
	private By cardtableProductName = By.xpath( "//div[@id='content']/form/div/table/tbody/tr/td[2]/a");
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillFormForShoppingCartu(){
		elementUtil = new ElementUtil(driver);
		elementUtil.waitForElementPresent(addToCartBtn_radio, 30);
		
		availableOptionsRadio.click();
		availableOptionsCheckbox.click();
		availableOptionsText.clear();
		availableOptionsText.sendKeys("Test 123");
		
		Select select=new Select(availableOptionsSelect);
		select.selectByIndex(1);
		availableOptionsTextarea.sendKeys("Test 1234");
		availableOptionsFileUpload.click();
		File filePath=new File(System.getProperty("user.dir") + "/src/main/resources/Test.txt");
		String tempFilePath = filePath.getAbsolutePath();
		uploadFileWithRobot(tempFilePath);
		
		Alert alert = elementUtil.waitForAlertPresent(60);
		alert.accept();
		quantityText.clear();
		quantityText.sendKeys("1");
		addCartButton.click();
		elementUtil.waitForElementPresent(successMessage_xpath, 60);
		
	}
	
	
	public String getSuccessMessage(){
		
		
		return successMessage.getText();
	}
	
    public void uploadFileWithRobot (String imagePath) {
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    
    
	public String getProductNameByIndex(String indexVal){
		return driver.findElement(By.xpath("(//div[@id='content']/form/div/table/tbody/tr/td[2]/a)["+indexVal+"]")).getText();
	}
	
	public List<String> getProductNames(){
		elementUtil.waitForElementPresent(cardtableProductName, 60);
		
		List<String> ProductNameList = new ArrayList();
	
		List<WebElement> forLength=  driver.findElements(By.xpath("//div[@id='content']/form/div/table/tbody/tr/td[2]/a"));
	
		for(int i=1; i<= forLength.size();i++)
			ProductNameList.add(getProductNameByIndex(""+i));
			
				
		return ProductNameList;
	}

}
